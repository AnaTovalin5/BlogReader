package android.tovalina.blogreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BlogPostAdapter extends ArrayAdapter<BlogPost> {
    public BlogPostAdapter(Context context, ArrayList<BlogPost> posts) {
        super(context, 0, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BlogPost post = getItem(position); //stores blogpost

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.blog_item, parent, false);
        }

        ImageView thumbnail = (ImageView)convertView.findViewById(R.id.thumbnail);
        new DownloadImageTask(thumbnail).execute(post.thumbnail);

        TextView title = (TextView)convertView.findViewById(R.id.title);
        title.setText(post.title);

        TextView author = (TextView)convertView.findViewById(R.id.author);
        author.setText(post.author);

        return convertView;
    }
}