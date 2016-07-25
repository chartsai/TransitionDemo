package idv.chatea.transitiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SceneActivity extends AppCompatActivity {

    private Button[] stepButtons;
    private Scene[] scenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        setupViews();
    }

    private void setupViews() {
        stepButtons = new Button[4];
        scenes = new Scene[4];

        ViewGroup root = (ViewGroup) findViewById(R.id.scene_container);

        stepButtons[0] = (Button) findViewById(R.id.step1);
        stepButtons[1] = (Button) findViewById(R.id.step2);
        stepButtons[2] = (Button) findViewById(R.id.step3);
        stepButtons[3] = (Button) findViewById(R.id.step4);

        for (int i = 0; i < stepButtons.length; i++) {
            Button button = stepButtons[i];
            final int sceneNumber = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    transitionTo(sceneNumber);
                }
            });
        }

        scenes[0] = Scene.getSceneForLayout(root, R.layout.activity_scene_scene1, this);
        scenes[1] = Scene.getSceneForLayout(root, R.layout.activity_scene_scene2, this);
        scenes[2] = Scene.getSceneForLayout(root, R.layout.activity_scene_scene3, this);
        scenes[3] = Scene.getSceneForLayout(root, R.layout.activity_scene_scene4, this);
    }

    private void transitionTo(int sceneNumber) {
        TransitionManager.go(scenes[sceneNumber]);
        setupViews();
    }
}
