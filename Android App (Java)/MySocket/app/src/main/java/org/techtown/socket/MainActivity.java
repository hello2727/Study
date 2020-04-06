package org.techtown.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText input1;

    TextView output1;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        output1 = findViewById(R.id.output1);

        final Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = input1.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data); //스레드와 인터넷 필요
                    }
                }).start();
            }
        });

        final Button startServerButton = findViewById(R.id.startServerButton);
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();

            }
        });

    }

    public void startServer() {
        int port = 5001;

        try {
            ServerSocket server = new ServerSocket(port);

            while(true) {
                Socket sock = server.accept(); //클라이언트 소켓
                InetAddress clientHost = sock.getLocalAddress(); //remote:클라이언트쪽, local:서버쪽
                int clientPort  = sock.getPort();
                println("클라이언트 연결됨 : " + clientHost + ", " + clientPort);

                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                String input = (String) instream.readObject();
                println("데이터 받음 : " + input);

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(input + " from server.");
                outstream.flush(); //남아있는 것 전부 출력
                println("데이터 보냄");

                sock.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                output1.append(data + "\n");
            }
        });
    }

    public void send(String data) {
        int port = 5001;

        try {
            Socket sock = new Socket("localhost", port);

            //실제 실무에서는 Data~Stream사용
            ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream()); //객체를 바이트 어레이로 만들어줌
            outstream.writeObject(data);
            outstream.flush(); //남아있는 것 전부 출력

            ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
            String input = (String) instream.readObject();
            sock.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
