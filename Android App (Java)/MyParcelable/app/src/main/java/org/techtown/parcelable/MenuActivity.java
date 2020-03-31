package org.techtown.parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent(); //전달받은 인텐트를 받음
        processIntent(intent);
    }

    public void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras(); //그 안에 있는 번들개체
            SimpleData data = bundle.getParcelable("data");
            if (data != null) {
                Toast.makeText(this, "전달받은 객체 : " + data.code + ", " + data.message, Toast.LENGTH_LONG).show();
            }

        }
    }
}
