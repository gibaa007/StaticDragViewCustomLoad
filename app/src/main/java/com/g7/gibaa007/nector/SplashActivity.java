package com.g7.gibaa007.nector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by newagesmb on 11/7/17.
 */

public class SplashActivity extends Activity {

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.t4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).putExtra("count", 4));
            }
        });
        findViewById(R.id.t5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).putExtra("count", 5));
            }
        });
        findViewById(R.id.t6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).putExtra("count", 6));
            }
        });
        findViewById(R.id.t7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).putExtra("count", 7));
            }
        });
        findViewById(R.id.t8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).putExtra("count", 8));
            }
        });
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public SharedPreferences sharedPreferences;
//
//            /*
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//
//            @Override
//            public void run() {
////                sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                boolean firstTime = sharedPreferences.getBoolean("first", true);
////                if (signedIn) {
////                    editor.putBoolean("first", false);
////                    //For commit the changes, Use either editor.commit(); or  editor.apply();.
////                    editor.commit();
////                Toast.makeText(SplashActivity.this, sharedpreferences.getString(AppConfig.USER_ID, ""), Toast.LENGTH_SHORT).show();
//                if (sharedpreferences.getString(AppConfig.USER_ID, "").equals("")) {
//                    intent = new Intent(SplashActivity.this, WelcomeActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//
//                    pd.show();
//                    if (commonActions.isNetworkConnected())
//                        new WebServices(SplashActivity.this).login(SplashActivity.this, asyncCallBack, AppConfig.LOGIN, sharedpreferences.getString(AppConfig.USER_NAME, ""), sharedpreferences.getString(AppConfig.USER_PASSWORD, ""),
//                                sharedpreferences.getString(AppConfig.PROPERTY_REG_ID, ""), sharedpreferences.getString(AppConfig.USER_LAT, ""), sharedpreferences.getString(AppConfig.USER_LNG, ""));
//                    else
//                        commonActions.showFailureSnackToast(iv_eden_image, "Please connect to internet");
//
//                }
////                } else {
////
////                }
//            }
//        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onResume() {
//        if (fromPush) {
//            fromPush = false;
//            Intent i = new Intent(SplashActivity.this, HomeActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(i);
//        }
        super.onResume();

    }


//    private void openSpecificActivity(String tag, String id) {
//
//        Intent intent = null;
//        if (tag.equals("post_comment") || tag.equals("near_communitypost")) {
//            intent = new Intent(this, PostDetailActivity.class);
//            intent.putExtra("postId", id);
//        } else if (tag.equals("friend_request") || tag.equals("near_friend")) {
//            intent = new Intent(this, ProfileViewActivity.class);
//            intent.putExtra("userId", id);
//        } else if (tag.equals("new_question") || tag.equals("question_reply")) {
//            intent = new Intent(this, QuestionDetailsActivity.class);
//            intent.putExtra("questId", id);
//            intent.putExtra("title", "Question");
//        } else if (tag.equals("near_exclusive")) {
//            intent = new Intent(this, ExclusiveDetailActivity.class);
//            intent.putExtra("exclusiveId", Integer.parseInt(id));
//        } else if (tag.equals("accept_stamp")) {
//
//        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }

}
