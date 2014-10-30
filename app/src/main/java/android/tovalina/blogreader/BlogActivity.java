package android.tovalina.blogreader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;


public class BlogActivity extends Activity {

    protected ProgressBar progressBar;
    protected GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        gridView = (GridView)findViewById(R.id.gridView);

        gridView.setEmptyView(progressBar); //sets progress bar to be empty

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("BlogActivity", "Title " + BlogPostParser.get().posts.get(position).title);

                Intent intent = new Intent(getApplicationContext(), BlogWebActivity.class); //creates intent
                Uri blogUri = Uri.parse(BlogPostParser.get().posts.get(position).url); //creates Uri
                intent.setData(blogUri);

                startActivity(intent);
            }
        });

        new BlogPostTask().execute(this); //creates new object and executes
    }

}
