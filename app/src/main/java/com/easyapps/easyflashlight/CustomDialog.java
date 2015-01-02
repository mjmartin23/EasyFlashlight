package com.easyapps.easyflashlight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
Created by Matt on 12/31/2014.

 Creates a custom dialog for the theme choice.
 */

public class CustomDialog extends Dialog {

    public Activity c;
    public Button blue, red, green, purple, orange,yellow,orangered,limegreeen;
    public int THEME;
    public int THEME_COLOR;
    public int THEME_COLOR_PRESSED;
    public int THEME_COLOR_RIPPLE;
    public String PREFS_NAME = "MyPrefsFile";


    public CustomDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        blue = (Button)findViewById(R.id.royal_blue_btn);
        red = (Button)findViewById(R.id.tomato_btn);
        green = (Button)findViewById(R.id.green_btn);
        purple = (Button)findViewById(R.id.purple_btn);
        orange = (Button)findViewById(R.id.orange_btn);
        yellow = (Button)findViewById(R.id.gold_btn);
        orangered = (Button)findViewById(R.id.orangered_btn);
        limegreeen = (Button)findViewById(R.id.limegreen_btn);

        blue.setOnClickListener(listener);
        red.setOnClickListener(listener);
        green.setOnClickListener(listener);
        purple.setOnClickListener(listener);
        orange.setOnClickListener(listener);
        yellow.setOnClickListener(listener);
        orangered.setOnClickListener(listener);
        limegreeen.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.royal_blue_btn:
                    THEME = R.style.AppTheme;
                    THEME_COLOR = getContext().getResources().getColor(R.color.RoyalBlue);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.SkyBlue);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.DeepSkyBlue);
                    break;
                case R.id.tomato_btn:
                    THEME = R.style.AppTheme_Red;
                    THEME_COLOR = getContext().getResources().getColor(R.color.Tomato);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.LightSalmon);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.Coral);
                    break;
                case R.id.green_btn:
                    THEME = R.style.AppTheme_Green;
                    THEME_COLOR = getContext().getResources().getColor(R.color.green);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.LightGreen);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.PaleGreen);
                    break;
                case R.id.purple_btn:
                    THEME = R.style.AppTheme_Purple;
                    THEME_COLOR = getContext().getResources().getColor(R.color.Purple);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.MediumPurple);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.MediumOrchid);
                    break;
                case R.id.orange_btn:
                    THEME = R.style.AppTheme_Orange;
                    THEME_COLOR = getContext().getResources().getColor(R.color.Orange);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.Wheat);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.SandyBrown);
                    break;
                case R.id.gold_btn:
                    THEME = R.style.AppTheme_Yellow;
                    THEME_COLOR = getContext().getResources().getColor(R.color.Gold);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.Khaki);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.PaleGoldenrod);
                    break;
                case R.id.orangered_btn:
                    THEME = R.style.AppTheme_OrangeRed;
                    THEME_COLOR = getContext().getResources().getColor(R.color.OrangeRed);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.Salmon);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.DarkSalmon);
                    break;
                case R.id.limegreen_btn:
                    THEME = R.style.AppTheme_Lime;
                    THEME_COLOR = getContext().getResources().getColor(R.color.LimeGreen);
                    THEME_COLOR_PRESSED = getContext().getResources().getColor(R.color.OliveDrab);
                    THEME_COLOR_RIPPLE = getContext().getResources().getColor(R.color.DarkOliveGreen);
                    break;

            }

            //Put theme changes into shared preferences
            SharedPreferences prefs = getContext().getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("THEME",THEME);
            editor.putInt("THEME_COLOR",THEME_COLOR);
            editor.putInt("THEME_COLOR_PRESSED",THEME_COLOR_PRESSED);
            editor.putInt("THEME_COLOR_RIPPLE",THEME_COLOR_RIPPLE);

            editor.apply();

            dismiss();

            //Restart activity to apply changes
            Context context = getContext();
            Intent i = new Intent(getContext(), MainActivity.class);
            context.startActivity(i);
        }

    };


}
