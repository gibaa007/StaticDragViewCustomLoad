package com.g7.gibaa007.nector;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * This class provides a custom view for the heterogenous layout used through
 * the application. It has an icon, a text and a custom background. Text and
 * image places can be changed two ways. </p> 1- An image at the top text at the
 * bottom </p> 2- An image on the left text on the right </p>
 *
 * @author candemir.doger
 */
public class CustomFrameView_copy extends FrameLayout {

    private SimpleDraweeView imageView;
    private TextView textView, title;
    final Animation in = new AlphaAnimation(0.0f, 1.0f);

    final Animation out = new AlphaAnimation(1.0f, 0.0f);
    public CustomFrameView_copy(Context context, TileModel tileModel) {
        super(context);
        imageView = new SimpleDraweeView(context);
        textView = new TextView(context);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        textView.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        textView.setText(tileModel.getTitle());
        textView.setPadding(5, 0, 0, 0);
        this.setPadding(20, 20, 20, 20);
        this.setClickable(true);
        this.addView(imageView);
        this.addView(textView);
        this.setIcon(tileModel.getImage());
//        this.setId(tileModel.getId());
        this.setBackgroundColor(Color.parseColor(tileModel.getColor()));
//        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(tileModel.getWidth(), tileModel.getHeight());
//        params.setMargins(tileModel.getMarginLeft(), tileModel.getMarginTop(), tileModel.getMarginRight(), tileModel.getMarginBottom());
//        this.setLayoutParams(params);
    }

    public CustomFrameView_copy(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        imageView = new SimpleDraweeView(context);
        title = new TextView(context);
        textView = new TextView(context);
        out.setDuration(3000);
        in.setDuration(3000);
        title.setTextColor(getResources().getColor(android.R.color.white));
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        title.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        params.leftMargin = 40;
        params.bottomMargin = 40;
        textView.setLayoutParams(params);
        textView.setAlpha(0.0f);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setAlpha(0.0f);
        title.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        title.setPadding(10, 5, 0, 0);
        title.setTypeface(null, Typeface.BOLD);
        textView.setPadding(10, 0, 30, 0);
        this.setClickable(true);
        this.addView(imageView);
        this.addView(textView);
        this.addView(title);
    }


    public String getText() {
        return textView.getText().toString();
    }

    public void setText(CharSequence text, CharSequence content, int resid) {
        title.setText(" " + text);
//        textView.setBackgroundColor(Color.parseColor("#FF0000"));
        textView.setText(content);
        title.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(resid), null, null, null);
    }

    public void setBackgroundColorHex(String text) {
        this.setBackgroundColor(Color.parseColor(text));
    }

//    public void setIcon(int resid) {
//        imageView.setImageDrawable(getResources().getDrawable(resid));
//    }

    public void setIcon(String resid) {
        imageView.setImageURI(Uri.parse(resid));
    }


    public int getCFVWidth() {
        return this.getMeasuredWidth();
    }

    public int getCFVHeight() {
        return this.getMeasuredHeight();
    }

    public void setViewData(TileModel tile) {
        this.setBackgroundColorHex(tile.getColor());
        this.setText(tile.getTitle(),tile.getContent().get(0), tile.getdLeft());
        this.setIcon(tile.getImage());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getCFVWidth() > 3 * getCFVHeight()) {
                    Log.e("View TAG 2", "width : " + getCFVWidth() + " height : " + getCFVHeight());

                    LayoutParams params = new LayoutParams(100, 100);
                    params.gravity = Gravity.CENTER_VERTICAL;
                    params.leftMargin = 40;
                    imageView.setAlpha(0.0f);
                    imageView.setLayoutParams(params);

                    out.setDuration(3000);
                    in.setDuration(3000);
                    LayoutParams paramst = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    paramst.gravity = Gravity.BOTTOM;
                    imageView.setAlpha(1.0f);
                    paramst.leftMargin = 100;
                    textView.setAlpha(1.0f);
                    textView.setMaxLines(4);
                    textView.setLayoutParams(paramst);
                    textView.setPadding(60, 0, 10, 30);

                }else if (getCFVWidth() > getCFVHeight()) {
                    Log.e("View TAG 1", "width : " + getCFVWidth() + " height : " + getCFVHeight());
                    LayoutParams params = new LayoutParams(100, 100);
                    params.gravity = Gravity.CENTER_VERTICAL;
                    params.leftMargin = 40;
                    imageView.setAlpha(0.0f);
                    out.setDuration(2000);
                    in.setDuration(2000);
                    textView.setAlpha(1.0f);
                    imageView.setLayoutParams(params);
                    textView.setMaxLines(3);

                }  else {
                    Log.e("View TAG 3", "width : " + getCFVWidth() + " height : " + getCFVHeight());
                    LayoutParams params = new LayoutParams(300, 200);
                    params.gravity = Gravity.CENTER_HORIZONTAL;
                    params.topMargin = 90;
                    imageView.setAlpha(1.0f);
                    textView.setAlpha(1.0f);
                    out.setDuration(1000);
                    in.setDuration(1000);
                    imageView.setLayoutParams(params);
                    textView.setMaxLines(6);
                }
            }
        }, 1000);

    }




}
