package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_button, iv_arrow;
    TextView tv_points;
    ProgressBar progressBar;

    Handler handler;
    Runnable runnable;
    Random r;

    private final static int STATE_BLUE = 1;
    private final static int STATE_PINK = 2;
    private final static int STATE_PURPLE = 3;
    private final static int STATE_GREEN = 4;

    int buttonState = STATE_BLUE;
    int arrowState = STATE_BLUE;

    int currentTime = 5000;
    int startTime = 5000;
    int currentPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_button = findViewById(R.id.iv_button);
        iv_arrow = findViewById(R.id.iv_arrow);
        tv_points = findViewById(R.id.tv_points);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setMax(startTime);
        progressBar.setProgress(startTime);

        tv_points.setText("Points: " + currentPoints);

        r = new Random();
        arrowState = r.nextInt(4) + 1;
        setArrowImage(arrowState);

        iv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonImage(setButtonPosition(buttonState));
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentTime = currentTime - 100;
                progressBar.setProgress(currentTime);
                if (currentTime > 0) {
                    handler.postDelayed(runnable, 100);
                } else {
                    if (buttonState == arrowState) {
                        currentPoints = currentPoints + 1;
                        tv_points.setText("Points: " + currentPoints);

                    startTime = startTime - 100;
                    if (startTime < 1000) {
                        startTime = 2000;
                    }
                    progressBar.setMax(currentTime);
                    currentTime = startTime;
                    progressBar.setProgress(currentTime);

                    arrowState = r.nextInt(4) + 1;
                    setArrowImage(arrowState);
                    handler.postDelayed(runnable, 100);
                    } else {
                        iv_button.setEnabled(false);
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        handler.postDelayed(runnable, 100);
    }

    private void setArrowImage(int state) {
        switch (state) {
            case STATE_BLUE:
                iv_arrow.setImageResource(R.drawable.blue_triangle);
                arrowState = STATE_BLUE;
                break;
            case STATE_PINK:
                iv_arrow.setImageResource(R.drawable.pink_triangle);
                arrowState = STATE_PINK;
                break;
            case STATE_PURPLE:
                iv_arrow.setImageResource(R.drawable.purple_triangle);
                arrowState = STATE_PURPLE;
                break;
            case STATE_GREEN:
                iv_arrow.setImageResource(R.drawable.green_triangle);
                arrowState = STATE_GREEN;
                break;
        }
    }

    private void setRotation (final ImageView image, final int drawable) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 05.f);
        rotateAnimation.setDuration(100);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setImageResource(drawable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(rotateAnimation);
    }
    private int setButtonPosition (int position) {
        position = position + 1;
        if (position == 5) {
            position = 1;
        }
        return position;
    }

    private void setButtonImage(int state) {
        switch (state) {
            case STATE_BLUE:
                setRotation(iv_button, R.drawable.rot_2);
                buttonState = STATE_BLUE;
                break;
            case STATE_PINK:
                setRotation(iv_button, R.drawable.rot_1);
                buttonState = STATE_PINK;
                break;
            case STATE_PURPLE:
                setRotation(iv_button, R.drawable.rot_4);
                buttonState = STATE_PURPLE;
                break;
            case STATE_GREEN:
                setRotation(iv_button, R.drawable.rot_3);
                buttonState = STATE_GREEN;
                break;
        }
    }
}