package idv.chatea.transitiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TransitionManagerActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ViewGroup sceneRootView;

    private RadioGroup radioGroup;
    private RadioButton scene1Button, scene2Button, scene3Button;

    private Scene[] scenes = new Scene[3];
    private TransitionManager scene3TransitionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_manager);

        radioGroup = (RadioGroup) findViewById(R.id.select_scene);
        radioGroup.setOnCheckedChangeListener(this);
        scene1Button = (RadioButton) findViewById(R.id.select_scene_1);
        scene2Button = (RadioButton) findViewById(R.id.select_scene_2);
        scene3Button = (RadioButton) findViewById(R.id.select_scene_3);

        sceneRootView = (ViewGroup) findViewById(R.id.scene_root);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            scenes[0] = new Scene(sceneRootView, findViewById(R.id.container));
        } else {
            scenes[0] = new Scene(sceneRootView, (ViewGroup) findViewById(R.id.container));
        }

        scenes[1] = Scene.getSceneForLayout(sceneRootView, R.layout.activity_transition_manager_scene2, this);

        scenes[2] = Scene.getSceneForLayout(sceneRootView, R.layout.activity_transition_manager_scene3, this);
        scene3TransitionManager = TransitionInflater.from(this).inflateTransitionManager(R.transition.transition_manager_scene3, sceneRootView);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch (checkId) {
            case R.id.select_scene_1:
                changeScene(0);
                break;
            case R.id.select_scene_2:
                changeScene(1);
                break;
            case R.id.select_scene_3:
                changeScene(2);
                break;
        }
    }

    private void changeScene(int scene) {
        switch (scene) {
            case 2:
                scene3TransitionManager.transitionTo(scenes[2]);
                break;
            default:
                TransitionManager.go(scenes[scene]);
                break;
        }
    }

}
