package idv.chatea.transitiondemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

public class LiveDemoDetailActivity extends AppCompatActivity {

    public static final String KEY_TITLE = "title";
    public static final String KEY_ICON_ID = "iconId";
    public static final String KEY_DESCRIPTION = "description";

    public static final String VIEW_NAME_TITLE = "titleView";
    public static final String VIEW_NAME_ICON = "iconView";
    public static final String VIEW_NAME_DESCRIPTION = "descriptionView";

    private TextView titleView;
    private ImageView iconView;
    private TextView descriptionView;

    private String title;
    private int iconId;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_demo_detail);

        titleView = (TextView) findViewById(R.id.title);
        iconView = (ImageView) findViewById(R.id.icon);
        descriptionView = (TextView) findViewById(R.id.description);

        ViewCompat.setTransitionName(titleView, VIEW_NAME_TITLE);
        ViewCompat.setTransitionName(iconView, VIEW_NAME_ICON);
        ViewCompat.setTransitionName(descriptionView, VIEW_NAME_DESCRIPTION);

        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra(KEY_TITLE);
            iconId = intent.getIntExtra(KEY_ICON_ID, 0);
            description = intent.getStringExtra(KEY_DESCRIPTION);
        }
        titleView.setText(title);
        iconView.setImageResource(iconId);
        descriptionView.setText(description);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addTransitionListener();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void addTransitionListener() {
        final Transition transition = getWindow().getSharedElementEnterTransition();

        if (transition != null) {
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionStart(Transition transition) {
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {
                }

                @Override
                public void onTransitionResume(Transition transition) {
                }
            });
        }
    }
}
