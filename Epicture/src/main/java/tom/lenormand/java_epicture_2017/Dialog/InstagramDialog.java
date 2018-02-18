package tom.lenormand.java_epicture_2017.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import tom.lenormand.java_epicture_2017.R;
import tom.lenormand.java_epicture_2017.constant.Constants;
import tom.lenormand.java_epicture_2017.listener.AuthDialogListener;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * the instagram auth Webview
 */
public class InstagramDialog extends Dialog
{
    private String mUrl;
    private AuthDialogListener mListener;
    private WebView mWebView;

    /**
     * constructor
     * @param context context calling the webview
     * @param url url for the webView to go
     * @param listener listener to know if the webview return success or not
     */
    public InstagramDialog(Context context, String url, AuthDialogListener listener)
    {
        super(context);
        mUrl = url;
        mListener = listener;
    }

    /**
     * initalize the dialog webview
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.instagram_authentication_dialog);
        setUpWebView();
    }

    /**
     * setup the webview
     */
    private void setUpWebView()
    { 
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient() {

            boolean authComplete = false;
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            String access_token;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (url.contains("#access_token=") && !authComplete)
                {
                    Uri uri = Uri.parse(url);
                    access_token = uri.getEncodedFragment();
                    // get the whole token after the '=' sign
                    access_token = access_token.substring(access_token.lastIndexOf("=")+1);
                    Log.i("", "CODE : " + access_token);
                    authComplete = true;
                    mListener.onSuccess(access_token);
                    dismiss();
                }
                else if (url.contains("?error")) {
                    mListener.onError("An Error Occurred");
                    dismiss();
                }
            }
        });
    }
}
