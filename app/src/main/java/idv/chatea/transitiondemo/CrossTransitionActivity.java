package idv.chatea.transitiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CrossTransitionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final int[] IMAGE_IDS = {
            R.drawable.android_study_group,
            R.drawable.android_taipei,
            R.drawable.android_taipei_chocolabs,
            R.drawable.android_taipei_sudo,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_scene);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
        GridAdapter adapter = new GridAdapter();
        gridView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.KEY_IMAGE_ID, IMAGE_IDS[i]);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, new Pair<>(view, ImageActivity.VIEW_NAME_IMAGE));

        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGE_IDS.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new SquareImageView(CrossTransitionActivity.this);
            }

            ImageView iv = (SquareImageView) view;
            iv.setImageResource(IMAGE_IDS[position]);

            return view;
        }
    }
}
