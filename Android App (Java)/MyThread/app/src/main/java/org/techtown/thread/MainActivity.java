package org.techtown.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//주제 : 메인쓰레드 사용하기
public class MainActivity extends AppCompatActivity {
    TextView textView;

    //MainHandler handler; ->긴 Handler처리 코드
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start(); //run() 실행됨
            }
        });

        //handler = new MainHandler(); ->긴 Handler처리 코드
    }

    class BackgroundThread extends Thread {
        int value = 0;

        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }

                value += 1;
                Log.d("MyThread", "value : " + value);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값 : " + value);
                    }
                });

                //일정 시간 후에 실행하기
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값 : " + value);
                    }
                }, 5000);

                //textView.setText("값 : " + value); ->충돌남

                /* ->긴 Handler처리 코드
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);


                handler.sendMessage(message); //MainHandler 클래스로 이동
                */

            }
        }
    }

    /* ->긴 Handler처리 코드
    class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) { //쓰레드 처리(동시 접속 안되도록 해줌)
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");

            textView.setText("값 : " + value);
        }
    }
    */

}
