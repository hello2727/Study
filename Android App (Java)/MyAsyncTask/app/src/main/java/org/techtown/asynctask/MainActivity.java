package org.techtown.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundTask task = new BackgroundTask();
                task.execute();
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> { //메인스레드로 실행됨
        @Override
        protected void onPreExecute() { //스레드로 실행되기 전 상태
            value = 0;
            progressBar.setProgress(value);
        }

        @Override
        protected void onPostExecute(Integer integer) { //스레드로 실행되고난 후 상태
            progressBar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //중간중간 업데이트
            progressBar.setProgress(values[0].intValue()); //values는 배열
        }

        @Override
        protected Integer doInBackground(Integer... integers) { //스레드로 실행되는 상태
            while(isCancelled() == false) {
                value += 1;

                if (value >= 100) {
                    break;
                }

                publishProgress(value); //onProgressUpdate()함수로 넘어감

                try {
                    Thread.sleep(1000); //1초마다 실행
                } catch (Exception e) {

                }
            }

            return value; //onPostExecute()함수로 넘어감
        }
    }

}
