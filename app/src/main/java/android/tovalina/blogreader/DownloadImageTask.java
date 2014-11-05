package android.tovalina.blogreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap thumbnail = null;
            try {
                URL thumbnailURL = new URL(urldisplay); //checks for error

                HttpURLConnection connection = (HttpURLConnection)thumbnailURL.openConnection(); //opens connection with URL
                connection.connect();
                int responseCode = connection.getResponseCode(); //gets response code

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    thumbnail = BitmapFactory.decodeStream(connection.getInputStream());
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return thumbnail;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
}
