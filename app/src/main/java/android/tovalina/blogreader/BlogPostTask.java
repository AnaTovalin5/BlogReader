package android.tovalina.blogreader;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BlogPostTask extends AsyncTask<Activity, Void, JSONObject> {
    private Activity activity;

    @Override
    protected JSONObject doInBackground(Activity... activities) {
        activity = activities[0];
        JSONObject jsonObject = null;

        try {
            URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=10"); //checks for error

            HttpURLConnection connection = (HttpURLConnection)blogFeedUrl.openConnection(); //opens connection with URL
            connection.connect();
            int responseCode = connection.getResponseCode(); //gets response code

            if (responseCode == HttpURLConnection.HTTP_OK) {
                Log.i("BlogPostTask", "Successful Connection " + responseCode);
                jsonObject = BlogPostParser.get().parse(connection.getInputStream());
            }
        }
        catch(MalformedURLException error) { //catches error
            Log.e("BlogPostTask", "Malformed URL: " + error);
        }
        catch(IOException error) {
            Log.e("BlogPostTask", "IO Exception", error);
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) { //creates blog-posts
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, arrayStrings); //adapts string to show in listView
        listView.setAdapter(adapter); //use adapter from line above
    }
}
