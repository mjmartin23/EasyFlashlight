package com.easyapps.easyflashlight;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.easyapps.easyflashlight.CustomDialog;

import com.melnykov.fab.FloatingActionButton;


public class MainActivity extends Activity {

    public Camera camera = null;
    public int THEME = R.style.AppTheme;
    public int THEME_COLOR;
    public int THEME_COLOR_PRESSED;
    public int THEME_COLOR_RIPPLE;
    public static final String PREFS_NAME = "MyPrefsFile";
    public CustomDialog customDialog;

    //TODO upload to github

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        THEME_COLOR = getResources().getColor(R.color.RoyalBlue);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,0);
        if (prefs != null) {
            THEME = prefs.getInt("THEME", R.style.AppTheme);
            THEME_COLOR = prefs.getInt("THEME_COLOR", getResources().getColor(R.color.RoyalBlue));
            THEME_COLOR_PRESSED = prefs.getInt("THEME_COLOR_PRESSED",getResources().getColor(R.color.SkyBlue));
            THEME_COLOR_RIPPLE = prefs.getInt("THEME_COLOR_RIPPLE",getResources().getColor(R.color.DeepSkyBlue));
        }

        setTheme(THEME);
        setContentView(R.layout.activity_main);

        FloatingActionButton myfab = (FloatingActionButton)findViewById(R.id.fabbutton);
        myfab.setColorNormal(THEME_COLOR);
        myfab.setColorPressed(THEME_COLOR_PRESSED);
        myfab.setColorRipple(THEME_COLOR_RIPPLE);

        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the device does not support flash, do nothing
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                    Toast.makeText(MainActivity.this, "This device does not support flash", Toast.LENGTH_LONG).show();
                    return;
                }

                if (camera != null) {
                    // If the camera is on, turn the camera off and release it
                    camera.stopPreview();
                    camera.release();
                    camera = null;

                } else {
                    // If the camera is not on
                    // Open a camera instance
                    camera = Camera.open();
                    // Get the parameters
                    Parameters params = camera.getParameters();

                    // Turn flashlight on
                    params.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        // release the camera immediately on pause event
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_theme) {

            customDialog = new CustomDialog(MainActivity.this);
            customDialog.show();

        } else if (id == R.id.action_feedback) {

            ShareActionProvider shareAction = (ShareActionProvider) item.getActionProvider();

            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "easyflashlightdev@gmail.com", null));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Easy Flashlight Bug");
            intent.putExtra(Intent.EXTRA_TEXT, "Please include your phone model and Android version.  Thank you for the feedback.");

            shareAction.setShareIntent(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
