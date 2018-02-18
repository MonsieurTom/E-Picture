package tom.lenormand.java_epicture_2017.listener;

/**
 * Created by tomle on 05/02/2018.
 */

/**
 * interface for the instagram auth webView
 */
public interface AuthDialogListener
{
    public void onSuccess(String code);
    public void onError(String error);
}
