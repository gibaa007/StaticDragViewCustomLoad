package com.g7.gibaa007.nector;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener, View.OnLongClickListener, View.OnDragListener {
    ArrayList<TileModel> tileList = new ArrayList<>();
    ArrayList<String> abc = new ArrayList<>();
    ArrayList<String> abcd = new ArrayList<>();
    boolean isDropSuccess = false;
    private int sourcePosition = 0, targetPosition = 0;
    private CustomFrameView sourceView;//, cfv1, cfv2, cfv3, cfv4, cfv5, cfv6, cfv7, cfv8, cfv9, cfv10;
    private int count = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

//        count = getIntent().getIntExtra("count", 0);
        count = 6;
        if (savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            abc.add("<b>Dr. Sandra Black</b><br />MD,MBBS,FRCP-neurology");
            abc.add("<b>Dr. Alexander Samuel Black</b><br />BDS-dentistry");
            abcd.add("A");
            abcd.add("B");
            abcd.add("C");
            abcd.add("D");
            abcd.add("E");
            abcd.add("F");
            abcd.add("G");
            abcd.add("H");
            tileList.add(new TileModel("#2080DA", "MY LISTS", abcd, 0, android.R.drawable.ic_menu_search, "https://cdn.pixabay.com/photo/2017/01/06/19/15/soap-bubble-1958650_960_720.jpg"));
            abc.add("MONDAY <b>20</b> JULY 2017 @ <b>10:30 AM</b>");
            abc.add("TUESDAY <b>16</b> APRIL 2017 @ <b>03:30 AM</b>");
            abc.add("WEDNESDAY <b>26</b> AUGUST 2017 @ <b>03:30 AM</b>");
            tileList.add(new TileModel("#de3b00", "CALENDER", abc, 1, android.R.drawable.ic_menu_edit, ""));
            tileList.add(new TileModel("#60B117", "PRESENTATIONS", abc, 2, android.R.drawable.ic_menu_report_image, ""));
            tileList.add(new TileModel("#DCB728", "MESSAGES", abc, 3, android.R.drawable.ic_menu_camera, "https://www.w3schools.com/css/img_fjords.jpg"));
            tileList.add(new TileModel("#8A1ABE", "HOME OFFICE UPDATES", abc, 4, android.R.drawable.ic_menu_send, ""));
            tileList.add(new TileModel("#32B0BF", "NEWSFEED", abc, 5, android.R.drawable.ic_menu_add, ""));
            tileList.add(new TileModel("#FF0000", "G", abc, 6, R.drawable.android, "https://www.w3schools.com/css/trolltunga.jpg"));
            tileList.add(new TileModel("#FF0000", "H", abc, 7, R.drawable.android, "https://www.audi.co.uk/content/dam/audi/production/RestOfSite/ExploreModels/Finance/1x1_finance_calculator.jpg"));
            tileList.add(new TileModel("#FF0000", "I", abc, 8, R.drawable.android, "http://www.audicentreperth.com.au/content/dam/ngw/au/drop_down_model_images/RS%206_drop_down2.png"));
            tileList.add(new TileModel("#FF0000", "J", abc, 9, R.drawable.android, "https://static.pexels.com/photos/33109/fall-autumn-red-season.jpg"));
        } else {
            tileList = savedInstanceState.getParcelableArrayList("key");
        }

        switch (count) {
            case 4:
                setContentView(R.layout.main_4);
                break;
            case 5:
                setContentView(R.layout.main_5);
                break;
            case 6:
                setContentView(R.layout.main_6);
                break;
            case 7:
                setContentView(R.layout.main_7);
                break;
            case 8:
                setContentView(R.layout.main_8);
                break;
        }
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG onResume", " Called");

        for (int i = 0; i < tileList.size(); i++) {
            int resourceId = this.getResources().getIdentifier("cfv" + (i + 1), "id", this.getPackageName());
            ((CustomFrameView) findViewById(resourceId)).setTag(tileList.get(i).getTag());
            ((CustomFrameView) findViewById(resourceId)).setViewData(tileList.get(i));

        }


        /*setData(cfv1, 0);
        setData(cfv2, 1);
        setData(cfv3, 2);
        setData(cfv4, 3);
        setData(cfv5, 4);
        setData(cfv6, 5);
        setData(cfv7, 6);
        setData(cfv8, 7);
        setData(cfv9, 8);
        setData(cfv10, 9);*/
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("TAG onRestore", "");
        tileList = savedInstanceState.getParcelableArrayList("key");
    }

    private void initView() {

        for (int i = 0; i < tileList.size(); i++) {


            int resourceId = this.getResources().getIdentifier("cfv" + (i + 1), "id", this.getPackageName());

            Log.e("for onResume", "id name:" + "cfv" + i + 1 + "||id:" + resourceId);

            CustomFrameView view = (CustomFrameView) findViewById(resourceId);

            view.setOnLongClickListener(this);
            view.setOnDragListener(this);

            view.setOnClickListener(this);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("TAG onSave", "");
//        Collections.swap(tileList, targetPosition, sourcePosition);
        outState.putParcelableArrayList("key", tileList);
        super.onSaveInstanceState(outState);
    }

   /* private void setData(CustomFrameView view, int i) {
        view.setOnLongClickListener(this);
        view.setOnDragListener(this);
        view.setOnClickListener(this);
        view.setTag(tileList.get(i).getTag());
        view.setViewData(tileList.get(i));
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 2131624113:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624114:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624115:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624116:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624117:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624118:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624119:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624120:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624121:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2131624122:
                Toast.makeText(this, "Pressed " + tileList.get((int) v.getTag()).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        sourceView = (CustomFrameView) view;
        sourcePosition = (int) view.getTag();
        ClipData.Item item = new ClipData.Item(view.getTag() + "");

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(((CustomFrameView) view).getText(), mimeTypes, item);
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, //data to be dragged
                shadowBuilder, //drag shadow
                view, //local data about the drag and drop operation
                0   //no needed flags
        );
        view.setAlpha(0.5f);

        return false;
    }

    @Override
    public boolean onDrag(View targetView, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                targetView.setAlpha(0.75f);
                break;
            case DragEvent.ACTION_DRAG_ENTERED:

                isDropSuccess = true;
                targetView.setAlpha(0.5f);
                break;
            case DragEvent.ACTION_DRAG_EXITED:

                isDropSuccess = false;
                targetView.setAlpha(0.75f);
                break;
            case DragEvent.ACTION_DROP:
                targetView.setAlpha(1.0f);
                if (isDropSuccess) {
                    ((CustomFrameView) targetView).setViewData(tileList.get(sourcePosition));
                    targetPosition = (int) targetView.getTag();
                    ((CustomFrameView) targetView).setTag(sourcePosition);
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                targetView.setAlpha(1.0f);
                CustomFrameView view = (CustomFrameView) event.getLocalState();
                if (isDropSuccess) {
                    view.setVisibility(View.VISIBLE);
                    sourceView.setViewData(tileList.get(targetPosition));
                    sourceView.setTag(targetPosition);

                    Log.e("TAG Source", sourcePosition + "");

                    Log.e("TAG Target", targetPosition + "");
//                    Collections.swap(tileList, targetPosition, sourcePosition);
                    isDropSuccess = false;
//                    onResume();
                }
            default:
                break;
        }
        return true;
    }


    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, endScale, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, endScale); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(1000);
        v.startAnimation(anim);
    }
}
