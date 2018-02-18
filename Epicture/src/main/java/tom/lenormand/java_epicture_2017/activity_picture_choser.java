package tom.lenormand.java_epicture_2017;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by tomle on 07/02/2018.
 */

/**
 * activity allowing the user to pick a photo from his phone and upload it
 */
public class activity_picture_choser extends Activity
{
    ImageView       selectedImageView;
    private boolean permission_granted = false;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int SELECT_PICTURE = 1;
    private static String selectedImagePath = "";
    String access_token = "";

    /**
     * initialize the activity
     * @param savedInstanceState state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_choser);

        Intent i = this.getIntent();
        access_token = i.getStringExtra("access_token");
    }

    /**
     * triggered when the user have selected a picture in his gallery
     * @param requestCode
     * @param resultCode
     * @param data intent of the picture
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                if (imageUri != null)
                {
                    requestRead();
                    if (permission_granted)
                    {
                        selectedImagePath = getPath(imageUri);
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        selectedImageView = (ImageView) findViewById(R.id.ativity_chosen_picture_chosen_picture);
                        selectedImageView.setImageBitmap(selectedImage);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(activity_picture_choser.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(activity_picture_choser.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    /**
     *  Request access to mobile data at runtime
     */
    public void requestRead() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        else
            permission_granted = true;
    }

    /**
     * Result of granted permissions or not
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // Permission Denied
                Toast.makeText(this, "Permission Denied to read storage", Toast.LENGTH_LONG).show();
            }
            else
                permission_granted = true;

            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * search a picture inside the user's mobile gallery
     * @param view view of the button
     */
    public void onClickButtonSearchPicture(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    /**
     * get full path of the user's picture
     * @param uri uri of the picture the user chosed
     * @return the full path to the user's picture
     */
    public String getPath(Uri uri) {
        if (uri != null) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            else if (isMediaDocument(uri))
            {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");

                Uri contentUri = null;
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {split[1]};

                return getDataColumn(this, contentUri, selection, selectionArgs);
            }
        }
        return ("");
    }

    /**
     * get the collum inside the mobile storage where the picture is hold
     * @param context context
     * @param uri uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * send the picture to a social media
     * @param view view of the button
     */
    public void onClickButtonSend(View view)
    {
        if (selectedImagePath.equals(""))
            Toast.makeText(activity_picture_choser.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        else
        {
            // Create the new Intent using the 'Send' action.
            Intent share = new Intent(Intent.ACTION_SEND);

            // Set the MIME type
            share.setType("image/*");

            // Create the URI from the media
            File media = new File(selectedImagePath);
            Uri uri = Uri.fromFile(media);

            // Add the URI to the Intent.
            share.putExtra(Intent.EXTRA_STREAM, uri);

            // Broadcast the Intent.
            startActivity(Intent.createChooser(share, "Share to"));
        }
    }

    /**
     * check if the picture chosen is in an sd card.
     * @param uri
     * @return
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * check if the picture chosen is in the mobile storage
     * @param uri
     * @return
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
