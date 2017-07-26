package com.g7.gibaa007.nector;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Random;

/**
 * This class provides a custom view for the heterogenous layout used through
 * the application. It has an icon, a text and a custom background. Text and
 * image places can be changed two ways. </p> 1- An image at the top text at the
 * bottom </p> 2- An image on the left text on the right </p>
 *
 * @author candemir.doger
 */
public class CustomFrameView extends FrameLayout {

    private Animation animMove;
    CommonActions commonActions;
    private SimpleDraweeView imageView;
    private TextView details, docdetails, title;
    private Handler mHandle = new Handler();
    private Random random = new Random();
    private TileModel mTile;

    //    public void setIcon(int resid) {
//        imageView.setImageDrawable(getResources().getDrawable(resid));
//    }
    Runnable timmer = new Runnable() {

        @Override
        public void run(){
            int pos = random.nextInt(mTile.getContent().size());
            docdetails.setAnimation(animMove);
            imageView.setAnimation(animMove);
            docdetails.setText(Html.fromHtml(String.valueOf(mTile.getContent().get(pos))));
            imageView.setImageURI(Uri.parse(mTile.getImage()));
            mHandle.removeCallbacks(timmer);
            mHandle.postDelayed(timmer, (mTile.getTag() + 1) * random.nextInt(4000 - 2000) + 2000);
        }
    };


    public CustomFrameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        commonActions = new CommonActions(context);
        imageView = new SimpleDraweeView(context);
        title = new TextView(context);
        details = new TextView(context);
        docdetails = new TextView(context);
        animMove = AnimationUtils.loadAnimation(context,
                R.anim.move);
        title.setTextColor(getResources().getColor(android.R.color.white));
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, commonActions.isTablet() ? 18 : 14);
        details.setTextColor(getResources().getColor(android.R.color.white));
        details.setTextSize(TypedValue.COMPLEX_UNIT_SP, commonActions.isTablet() ? 16 : 12);
        details.setGravity(Gravity.BOTTOM);
        docdetails.setTextColor(getResources().getColor(android.R.color.white));
        docdetails.setTextSize(TypedValue.COMPLEX_UNIT_SP, commonActions.isTablet() ? 14 : 10);
        docdetails.setGravity(Gravity.CENTER_VERTICAL);
        title.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LayoutParams params = new LayoutParams(90, 90);
        params.gravity = Gravity.BOTTOM;
        params.leftMargin = 15;
        params.bottomMargin = 15;
        imageView.setLayoutParams(params);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        imageView.getHierarchy().setRoundingParams(roundingParams);
        title.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        title.setPadding(10, 5, 0, 0);
        title.setTypeface(null, Typeface.BOLD);
        this.setClickable(true);
        this.addView(details);
        this.addView(docdetails);
        this.addView(imageView);
        this.addView(title);
    }

    public String getText() {
        return details.getText().toString();
    }

    public void setText(CharSequence text, CharSequence content, int resid, String color) {
        LayoutParams paramc = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paramc.gravity = Gravity.BOTTOM;
        paramc.leftMargin = 15;
        if (content.length() > 0) {
            imageView.setVisibility(VISIBLE);
            paramc.bottomMargin = 130;
            details.setLayoutParams(paramc);
            docdetails.setBackgroundColor(Color.parseColor("#55000000"));

        } else {
            imageView.setVisibility(INVISIBLE);
            paramc.bottomMargin = 15;
            details.setLayoutParams(paramc);
            docdetails.setBackgroundColor(Color.TRANSPARENT);
        }
        title.setText(" " + text);
        docdetails.setText(Html.fromHtml(String.valueOf(content)));
//        details.setText("You have 5 new messages");
        title.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(resid), null, null, null);
    }

    public void setBackgroundColorHex(String text) {
        this.setBackgroundColor(Color.parseColor(text));
    }

    public void setIcon(boolean type) {

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        docdetails.setPadding(type ? 15 : 125, 10, 10, 10);
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        docdetails.setAlpha(1.0f);
        docdetails.setMinHeight(125);


        if (getCFVWidth() > 3 * getCFVHeight()) {
            Log.e("View TAG 2", "width : " + getCFVWidth() + " height : " + getCFVHeight());


            docdetails.setMaxLines(2);

        } else if (getCFVWidth() > getCFVHeight()) {
            Log.e("View TAG 1", "width : " + getCFVWidth() + " height : " + getCFVHeight());

            docdetails.setMaxLines(2);

        } else {
            Log.e("View TAG 3", "width : " + getCFVWidth() + " height : " + getCFVHeight());

            docdetails.setMaxLines(8);
        }
        docdetails.setLayoutParams(params);
    }

    public int getCFVWidth() {
        return this.getMeasuredWidth();
    }

    public int getCFVHeight() {
        return this.getMeasuredHeight();
    }

    public void setViewData(TileModel tile) {
        this.setBackgroundColorHex(tile.getColor());
        this.setText(tile.getTitle(), tile.getContent().get(0), tile.getdLeft(), tile.getColor());
        this.setIcon(tile.getImage().equals(""));
        //randomDataOld(this, tile);
        mTile = tile;
        mHandle.postDelayed(timmer, (mTile.getTag() + 1) * random.nextInt(4000 - 2000) + 2000);
    }

    public void randomDataOld(final CustomFrameView view, final TileModel tile) {

        Random random = new Random();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int pos = random.nextInt(tile.getContent().size());
                docdetails.setText(Html.fromHtml(String.valueOf(tile.getContent().get(pos))));
                imageView.setImageURI(Uri.parse(tile.getImage()));
                randomDataOld(view, tile);
            }
        }, (tile.getTag() + 1) * random.nextInt(4000 - 2000) + 2000); // here 1000(1 second) interval to change from current  to next image

    }

}
