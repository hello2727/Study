package org.techtown.sliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button;

    boolean isPageOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingAnimationListener animListener = new SlidingAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPageOpen) {
                    page.startAnimation(translateRightAnim);
                } else {
                    page.setVisibility(View.VISIBLE); //숨겨졌던게 보여짐
                    page.startAnimation(translateLeftAnim);
                }
            }
        });

    }

    class SlidingAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            if (isPageOpen) { //페이지 열린상태
                page.setVisibility(View.INVISIBLE);

                button.setText("열기");
                isPageOpen = false;
            } else { //페이지 닫힌상황
                button.setText("닫기");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) { //애니메이션 끝나는 시점

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}