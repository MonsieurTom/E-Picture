package tom.lenormand.java_epicture_2017;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tom.lenormand.java_epicture_2017.Dialog.InstagramDialog;
import tom.lenormand.java_epicture_2017.constant.Constants;
import tom.lenormand.java_epicture_2017.listener.AuthDialogListener;

/**
 * allow the user to connect to the instagram api
 */
public class MainActivity extends Activity implements AuthDialogListener
{
    InstagramDialog auth_dialog;

    private final String url = Constants.AUTH_URL
            + "oauth/authorize/?client_id="
            + Constants.CLIENT_ID
            + "&redirect_uri="
            + Constants.REDIRECT_URI
            + "&response_type=token"
            + "&display=touch&scope=public_content+comments";

    /**
     * initialize the activity
     * @param savedInstanceState state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * connect to instagram launching a webview to instagram's connect pages
     * @param view view of the button
     */
    public void onClickButtonInstaConnect(View view)
    {
        auth_dialog = new InstagramDialog(MainActivity.this, url, MainActivity.this);
        auth_dialog.setCancelable(true);
        auth_dialog.show();
    }

    /**
     * triggered on webview's error
     * @param error
     */
    @Override
    public void onError(String error)
    {
        Toast toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * triggered on webView success
     * @param code
     */
    @Override
    public void onSuccess(String code)
    {
        if (code == null) {
            auth_dialog.dismiss();
        }

        Intent i = new Intent(MainActivity.this, activity_instagram_main.class);
        i.putExtra("access_token", code);
        startActivity(i);
    }
}
