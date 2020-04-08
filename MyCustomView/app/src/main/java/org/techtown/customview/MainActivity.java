package org.techtown.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomView view = new CustomView(this);
        setContentView(view); //이 화면에 채워질 최상위 뷰

    }
}
