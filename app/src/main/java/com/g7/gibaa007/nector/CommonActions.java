package com.g7.gibaa007.nector;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CommonActions {
    Context con;

    public CommonActions(Context con) {
        // TODO Auto-generated constructor stub
        this.con = con;

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }



    /**
     * Converts number of bytes into proper scale.
     *
     * @param bytes number of bytes to be converted.
     * @return A string that represents the bytes in a proper scale.
     */
    public static String getBytesString(long bytes) {
        String[] quantifiers = new String[]{
                "KB", "MB", "GB", "TB"
        };
        double speedNum = bytes;
        for (int i = 0; ; i++) {
            if (i >= quantifiers.length) {
                return "";
            }
            speedNum /= 1024;
            if (speedNum < 512) {
                return String.format("%.2f", speedNum) + " " + quantifiers[i];
            }
        }
    }

    /**
     * Copies the data from the passed in Uri, to a new file for use with the
     * Transfer Service
     *
     * @param context
     * @param uri
     * @return
     * @throws IOException
     */
    public static File copyContentUriToFile(Context context, Uri uri) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(uri);
        File copiedData = new File(context.getDir("SampleImagesDir", Context.MODE_PRIVATE), UUID
                .randomUUID().toString());
        copiedData.createNewFile();

        FileOutputStream fos = new FileOutputStream(copiedData);
        byte[] buf = new byte[2046];
        int read = -1;
        while ((read = is.read(buf)) != -1) {
            fos.write(buf, 0, read);
        }

        fos.flush();
        fos.close();

        return copiedData;
    }


    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
//
//    public static void customAlertDialog(String message, final Context activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
//        ;
//
//        alertDialogBuilder.setMessage(message);
//        alertDialogBuilder.setCancelable(false)
//                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                }).show();
//    }
//
//    public static boolean customAlertDialogreturn(String message, final Context activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
//        ;
//
//        alertDialogBuilder.setMessage(message);
//        alertDialogBuilder.setCancelable(false)
//                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                }).show();
//        return true;
//    }

    public static boolean checkForAlpahbet(String str1) {
        if (!Pattern.matches(".*[a-zA-Z]+.*", str1))
            return true;
        else return false;
    }

    public static boolean isLollipop() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }

    public static String timeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
        String timeZone = new SimpleDateFormat("Z").format(calendar.getTime());
        return timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5);
    }

    public static int timeZoneOffsetinSeconds() {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        return tz.getOffset(System.currentTimeMillis()) / 1000;
    }

    public static String getBuildVersion(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            long time = ze.getTime();
            String s = SimpleDateFormat.getInstance().format(new java.util.Date(time));
            zf.close();
            return s;
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * To hide keyboard
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    public static HashMap<String, String> getQueryString(String url) {
        Uri uri = Uri.parse(url);

        HashMap<String, String> map = new HashMap<>();
        for (String paramName : uri.getQueryParameterNames()) {
            if (paramName != null) {
                String paramValue = uri.getQueryParameter(paramName);
                if (paramValue != null) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

//    public String GetCountryZipCode(Activity activity) {
//        String CountryID = "";
//        String CountryZipCode = "";
//
//        TelephonyManager manager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
//        //getNetworkCountryIso
//        CountryID = manager.getSimCountryIso().toUpperCase();
//        String[] rl = activity.getResources().getStringArray(R.array.CountryCodes);
//        for (int i = 0; i < rl.length; i++) {
//            String[] g = rl[i].split(",");
//            if (g[1].trim().equals(CountryID.trim())) {
//                CountryZipCode = g[0];
//                break;
//            }
//        }
//        return CountryZipCode;
//    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

//    public void checkWriteExternalPermission(Activity mActivity) {
//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(mActivity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(mActivity,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        AppConfig.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The CALL_BACK method gets the
//                // result of the request.
//            }
//        } else {
//
//            Toast.makeText(mActivity, "Permission Granted", Toast.LENGTH_SHORT).show();
//        }
//    }


//    private void customPopUp(String title, String content, final JSONObject resposnseJSON, String accept) {
//        final Dialog dialog = new Dialog(FanSignUpActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View view = getLayoutInflater().inflate(R.layout.dialog_custom_popup, null);
//
//        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
//        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
//        TextView tv_accept = (TextView) view.findViewById(R.id.tv_accept);
//        TextView tv_reject = (TextView) view.findViewById(R.id.tv_reject);
//        tv_title.setText(title);
//        tv_content.setText(content);
//        tv_accept.setText(accept);
//        tv_reject.setText("cancel");
//        view.findViewById(R.id.tv_accept).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                JSONObject mergeData = null;
//                try {
//                    mergeData = resposnseJSON.getJSONObject("merge_data");
//                    JSONObject userData = resposnseJSON.getJSONObject("user_data");
//                    WebServices.mergeLogin(FanSignUpActivity.this, asyncCallBack, AppConfig.MERGE_ACCOUNTS, userData.getString("member_id"), mergeData.getString("media"), mergeData.getString("media_id"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        view.findViewById(R.id.tv_reject).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setContentView(view);
//        dialog.setCancelable(false);
//        dialog.show();
//    }

    /**
     * Method to show snack message
     *
     * @param view
     * @param message
     */
    public void showSuccessSnackToast(View view, String message) {
        Snackbar snackbar = Snackbar
                .make(view, "☺ " + message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.BLACK);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#FDDE50"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(null, Typeface.NORMAL);
        textView.setTextSize(18);
        snackbar.show();

    }



    public boolean isTablet() {
        boolean xlarge = ((con.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((con.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    /**
     * Method to show snack message
     *
     * @param view
     * @param message
     */
    public void showFailureSnackToast(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, "\uD83D\uDE22 " + message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.parseColor("#EA4747"));
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#EA4747"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(18);
        textView.setTypeface(null, Typeface.NORMAL);
        snackbar.show();

    }

    public void setupUI(View view, final Activity activity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }

            });
        }

        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView, activity);
            }
        }
    }

    /**
     * Method for showing toast
     *
     * @param Message
     */
    public void showToast(String Message) {
        Toast.makeText(con, Message, Toast.LENGTH_LONG).show();
    }

    /**
     * Returns the extracted text from the edit text given
     *
     * @param editText
     * @return
     */
    public String getTextFrom(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * Method for checking valid email id
     *
     * @param target
     * @return
     */
    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                .matches();
    }

    /**
     * Returns true if Internet is available
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) con
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return (cm.getActiveNetworkInfo() != null);
    }

}
