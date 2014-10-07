package android.tovalina.blogreader;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.jar.JarException;

/**
 * Created by Student on 10/2/2014.
 */
public class BlogPostParser {
    private static BlogPostParser parser;
    public ArrayList<BlogPost> posts;

    private BlogPostParser() {

    }

    public static BlogPostParser get() {
        if(parser == null) { //checks if parser exists
            parser = new BlogPostParser();
        }
        return parser;
    }

    public JSONObject parse (InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); //reader for inputStream
        StringBuilder builder = new StringBuilder(); //builds new string
        JSONObject jsonObject = null;

        String line;

        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONTokener jsonTokener = new JSONTokener(builder.toString()); //breaks down string into tokens
            jsonObject = new JSONObject(jsonTokener);
        }
        catch (IOException error) {
            Log.e("BlogPostParser", "IOException " + error); //captures and displays error
        }
        catch (JSONException error) {
            Log.e("BlogPostParser", "JSON Exception " + error);
        }

        return jsonObject;
    }

    public void readFeed (JSONObject jsonObject) {
        try {
            JSONArray jsonPosts = jsonObject.getJSONArray("posts");

            for(int index = 0; index < jsonPosts.length(); index++) {
                JSONObject post = jsonPosts.getJSONObject(index);

                String title = post.getString("title");
                String url = post.getString("url");

                BlogPost blogPost = new BlogPost(title, url);
            }
        }
        catch (JSONException error) {
            Log.e("BlogPostsParser", "JSONException " + error);
        }
    }
}
