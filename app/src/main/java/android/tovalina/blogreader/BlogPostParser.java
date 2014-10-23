package android.tovalina.blogreader;

import android.text.Html;
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

public class BlogPostParser {
    private static BlogPostParser parser;
    public ArrayList<BlogPost> posts;

    private BlogPostParser() {
        posts = new ArrayList<BlogPost>();
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

                for(int index = 0; index < jsonPosts.length(); index++) {  //prints blog titles onto the screens
                JSONObject post = jsonPosts.getJSONObject(index);

                String title = Html.fromHtml(post.getString("title")).toString();
                String url = post.getString("url");

                BlogPost blogPost = new BlogPost(title, url);
                posts.add(blogPost); //storing objects into variable posts
            }
        }
        catch (JSONException error) {
            Log.e("BlogPostsParser", "JSONException " + error);
        }
    }
}
