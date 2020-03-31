package org.techtown.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Log.d("Main", "onCreate 호출됨"); //디버그 메시지 출력(Logcat에서 확인가능)
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Main", "onStart 호출됨"); //디버그 메시지 출력(Logcat에서 확인가능)
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Main", "onStop 호출됨"); //디버그 메시지 출력(Logcat에서 확인가능)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Main", "onDestroy 호출됨"); //디버그 메시지 출력(Logcat에서 확인가능)
    }

    @Override
    protected void onPause() { //갑자기 정지되는 경우
        super.onPause();

        saveState(); //저장

        Log.d("Main", "onPause 호출됨"); //디버그 메시지 출력(Logcat에서 확인가능)
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadState(); //보관

        Log.d("Main", "onResume 호출됨"); //디버그 메시지 출력(Logcat에서 확인가능)
    }

    public void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit(); //객체 생성
        editor.putString("name", editText.getText().toString());
        editor.commit(); //저장됨 (단말에 파일로 저장되므로 앱이 종료되어도 유지됨)
    }

    public void loadState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if (pref != null) {
            String name = pref.getString("name", "");
            editText.setText(name);
        }
    }
}
