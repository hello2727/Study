package org.techtown.callintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
                startActivity(intent); //시스템으로 요청

                /*
                Intent intent = new Intent();
                ComponentName name = new ComponentName("org.techtown.callintent",
                        "org.techtown.callintent.MenuActivity"); //액티비티 설정
                intent.setComponent(name); //위의 메뉴 액티비티를 띄어라
                startActivityForResult(intent, 101);
                */
            }
        });
    }
}
