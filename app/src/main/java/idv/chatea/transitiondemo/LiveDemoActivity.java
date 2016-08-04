package idv.chatea.transitiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LiveDemoActivity extends AppCompatActivity {

    private TextView title;
    private ImageView icon;
    private TextView description;

    private Scene[] scenes;
    private int sceneCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_demo);

        ViewGroup container = (ViewGroup) findViewById(R.id.container);

        scenes = new Scene[] {
                Scene.getSceneForLayout(container, R.layout.activity_live_scene1, this),
                Scene.getSceneForLayout(container, R.layout.activity_live_scene2, this),
        };

        setupViews();
    }

    private void setupViews() {
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        icon = (ImageView) findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LiveDemoActivity.this, LiveDemoDetailActivity.class);
                intent.putExtra(LiveDemoDetailActivity.KEY_TITLE, title.getText().toString());
                intent.putExtra(LiveDemoDetailActivity.KEY_ICON_ID, R.drawable.android_taipei);
                intent.putExtra(LiveDemoDetailActivity.KEY_DESCRIPTION, description.getText().toString());

                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        LiveDemoActivity.this,
                        new Pair<View, String>(title, LiveDemoDetailActivity.VIEW_NAME_TITLE),
                        new Pair<View, String>(icon, LiveDemoDetailActivity.VIEW_NAME_ICON),
                        new Pair<View, String>(description, LiveDemoDetailActivity.VIEW_NAME_DESCRIPTION));

                ActivityCompat.startActivity(LiveDemoActivity.this, intent, activityOptions.toBundle());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.live_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_switch:
                sceneCounter++;
                changeScene(sceneCounter % 2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeScene(int sceneNumber) {
        TransitionManager.go(scenes[sceneNumber]);
        setupViews();
    }
}
