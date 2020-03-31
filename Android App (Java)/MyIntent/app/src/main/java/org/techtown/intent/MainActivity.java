package org.techtown.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메뉴 화면 띄우기
                //Context=원하는 뷰가 포함되어있는 주변 정보
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, 101); //두번째 파라미터는 요청코드
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //메뉴->메인 액티비티로 돌아올 때 자동호출
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (data != null) {
                String name = data.getStringExtra("name"); //다른 액티비티로부터 데이터 가져오기
                if (name != null) {
                    Toast.makeText(this, "응답으로 받은 데이터 : " + name, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
