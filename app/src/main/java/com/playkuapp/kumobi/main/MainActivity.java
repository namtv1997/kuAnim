package com.playkuapp.kumobi.main;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.playkuapp.kumobi.R;
import com.playkuapp.kumobi.base.BaseActivity;
import com.playkuapp.kumobi.webview.WebViewActivity;
import com.skyfishjy.library.RippleBackground;

public class MainActivity extends BaseActivity {
    int type = 0;
    boolean isOnline;
    ImageView imgSale, imgLogo, imgSupport;
    Animation.AnimationListener listener;
    Button btnLogin;
    RippleBackground rippleBackground, rippleBackground1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        btnLogin = findViewById(R.id.btnLogin);
        imgSale = findViewById(R.id.imgSale);
        imgSupport = findViewById(R.id.imgSupport);
        imgLogo = findViewById(R.id.imgLogo);

        imgSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline){
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("home", "https://kumobi.app/");
                    transitionTo(intent);
                }else {
                    openDialog(MainActivity.this);
                }
            }
        });
        imgSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline){
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("home", "https://kumobi.app/");
                    transitionTo(intent);
                }else {
                    openDialog(MainActivity.this);
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline){
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("home", "https://kumobi.app/");
                    transitionTo(intent);
                }else {
                    openDialog(MainActivity.this);
                }
            }
        });
        rippleBackground = findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        rippleBackground1 = findViewById(R.id.content1);
        rippleBackground1.startRippleAnimation();

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAnimations();
    }


    @Override
    protected void haveConnect() {
        isOnline = true;
    }

    @Override
    protected void dontHaveConnect() {
        isOnline = false;
    }

    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
        }
        getWindow().setEnterTransition(transition);
    }

    void loadAnimations() {
        new AnimationUtils();
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        rotation.setAnimationListener(listener);
        imgLogo.startAnimation(rotation);
    }

    private Visibility buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(500);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
