package android.tovalina.blogreader;

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
}
