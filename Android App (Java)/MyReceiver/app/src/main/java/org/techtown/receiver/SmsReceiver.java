package org.techtown.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        // SMS 데이터 뽑기
        if (messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            String contents = messages[0].getMessageBody();

            Log.d(TAG, "sender : " + sender + ", contents : " + contents);

            //화면쪽으로 보내줌 (SmsActivity)
            sendToActivity(context, sender, contents);
        }

    }

    public void sendToActivity(Context context, String sender, String contents) {
        Intent intent = new Intent(context, SmsActivity.class);

        // 브로드 캐스트 수신자는 화면에 없으므로 New Task 플래그 필요하다
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // 수신자쪽에서 activity쪽으로 띄워준다.
        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);

        context.startActivity(intent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        //  pdus 라고 하는것은 SMS 데이터를 처리하는 SMBP 프로토콜안에 이런이름으로 들어가있다.
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i = 0; i < smsCount; i++) {
            //버전별로 다른 코드 넣기
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //단말기 OS 버전(23이후인 경우)
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }

        }

        return messages; //sms메시지가 파싱이되어 리턴됨
    }
}
