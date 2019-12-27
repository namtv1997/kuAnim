package com.playkuapp.kumobi.splash;


import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.playkuapp.kumobi.R;
import com.playkuapp.kumobi.base.BaseActivity;
import com.playkuapp.kumobi.main.MainActivity;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUpAnimation();
    }

    @Override
    protected void haveConnect() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                transitionTo(intent);
            }
        }, 3000);

    }

    @Override
    protected void dontHaveConnect() {

    }

    private void setUpAnimation(){
        final ImageView textAnimation =  findViewById(R.id.imgLogo);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 900f);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        // Nó sẽ tăng tốc độ ở lần đầu và sau đó giảm dần

        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                textAnimation.setTranslationY(progress);
            }
        });
        valueAnimator.start();
    }

}
