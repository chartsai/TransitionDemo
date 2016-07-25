package idv.chatea.transitiondemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    public static final String VIEW_NAME_IMAGE = "image";
    public static final String KEY_IMAGE_ID = "imageId";
    public static final String KEY_DESCRIPTION = "description";

    private ImageView imageView;
    private TextView textView;

    private int imageId = 0;
    private String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
        }

        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);

        ViewCompat.setTransitionName(imageView, VIEW_NAME_IMAGE);

        Intent intent = getIntent();
        if (intent != null) {
            imageId = intent.getIntExtra(KEY_IMAGE_ID, 0);
            description = intent.getStringExtra(KEY_DESCRIPTION);
        }
        imageView.setImageResource(imageId);
        textView.setText(description);

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
