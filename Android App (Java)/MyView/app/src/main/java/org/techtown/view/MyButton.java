package org.techtown.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {

    public MyButton(Context context) { //소스코드에서 호출
        super(context);

        init(context);
    }

    public MyButton(Context context, AttributeSet attrs) { //xml레이아웃에서 호출
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        setBackgroundColor(Color.CYAN);
        setTextColor(Color.BLACK);

        float textSize = getResources().getDimension(R.dimen.text_size); //dimens.xml 수치값 가져오기
        setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("MyButton", "onDraw 호출됨");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //화면 터치이벤트
        Log.d("MyButton", "onTouchEvent 호출됨");

        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN: //눌렸을 때
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.CYAN); //뗐을 때
                setTextColor(Color.BLACK);
                break;
        }

        invalidate(); //다시 화면을 그려줌 (onDraw함수 호출)

        return true;
    }
}
