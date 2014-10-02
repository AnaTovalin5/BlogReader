package android.tovalina.blogreader;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BlogPostTask extends AsyncTask<Activity, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(Activity... activities) {
        try {
            URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=10"); //checks for error

            HttpURLConnection connection = (HttpURLConnection)blogFeedUrl.openConnection(); //opens connection with URL
            connection.connect();
            int responseCode = connection.getResponseCode(); //gets response code

            if (responseCode == HttpURLConnection.HTTP_OK) {
                Log.i("BlogPostTask", "Succesful Connection " + responseCode);
            }
        }
        catch(MalformedURLException error) { //catches error
            Log.e("BlogPostTask", "Malformed URL: " + error);
        }
        catch(IOException error) {
            Log.e("BlogPostTask", "IO Exception", error);
        }
        return null;
    }
}
