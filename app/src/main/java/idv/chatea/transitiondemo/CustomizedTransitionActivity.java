package idv.chatea.transitiondemo;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

public class CustomizedTransitionActivity extends AppCompatActivity implements View.OnClickListener {

    private Scene[] scenes;
    private Transition transition;

    private int selectedScene = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_transition);

        ViewGroup container = (ViewGroup) findViewById(R.id.container);

        scenes = new Scene[]{
                Scene.getSceneForLayout(container, R.layout.activity_customized_transition_scene1, this),
                Scene.getSceneForLayout(container, R.layout.activity_customized_transition_scene2, this),
        };

        container.setOnClickListener(this);

        transition = new CircleViewTransition();
    }

    @Override
    public void onClick(View view) {
        selectedScene++;
        changeScene(selectedScene % 2);
    }

    private void changeScene(int sceneNumber) {
        TransitionManager.go(scenes[sceneNumber], new CircleViewTransition());
    }

    private class CircleViewTransition extends Transition {

        private static final String COLOR = "color";

        @Override
        public void captureStartValues(TransitionValues transitionValues) {
            captureValues(transitionValues);
        }

        @Override
        public void captureEndValues(TransitionValues transitionValues) {
            captureValues(transitionValues);
        }

        private void captureValues(TransitionValues transitionValues) {
            View view = transitionValues.view.findViewById(R.id.view);
            transitionValues.values.put(COLOR, view.getBackground());
        }

        @Override
        public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
            if (null == startValues || null == endValues) {
                return null;
            }

            final View view = endValues.view.findViewById(R.id.view);
            final Drawable startDrawable = (Drawable) startValues.values.get(COLOR);
            final Drawable endDrawable  = (Drawable) endValues.values.get(COLOR);

            if (startDrawable instanceof ColorDrawable && endDrawable instanceof ColorDrawable) {
                ColorDrawable startColor = (ColorDrawable) startDrawable;
                ColorDrawable endColor = (ColorDrawable) endDrawable;
                if (startColor.getColor() != endColor.getColor()) {
                    ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(),
                            startColor.getColor(), endColor.getColor());
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Object value = animation.getAnimatedValue();
                            if (null != value) {
                                view.setBackgroundColor((Integer) value);
                            }
                        }
                    });
                    return animator;
                }
            }

            return null;
        }
    }
}
