package android.tovalina.blogreader;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Student on 10/2/2014.
 */
public class BlogPostParser {
    private static BlogPostParser parser;

    private BlogPostParser() {

    }

    public static BlogPostParser get() {
        if(parser == null) { //checks if parser exists
            parser = new BlogPostParser();
        }
        return parser;
    }

    public JSONObject parse(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); //reader for inputStream
        StringBuilder builder = new StringBuilder(); //builds new string
        JSONObject jsonObject = null;

        return jsonObject;
    }
}
