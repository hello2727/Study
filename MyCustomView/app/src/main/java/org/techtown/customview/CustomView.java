package org.techtown.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomView extends View {
    Paint paint;

    public CustomView(Context context) { //소스코드에서 생성된 것 관리
        super(context);

        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) { //Attri~:xml에서 생성된 것 관리
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        paint = new Paint(); //색연필 개념
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) { //Canvas: 도화지 개념, OnDraw함수: 도화지에 그림 그리기
        super.onDraw(canvas);

        //canvas.drawRect(100, 100, 200, 200, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(10, 10, 300, 300, paint); //사각형 그리기

        paint.setStyle(Paint.Style.STROKE); //선(테두리)
        paint.setStrokeWidth(10.0F);
        paint.setColor(Color.GREEN);
        canvas.drawRect(10, 10, 300, 300, paint);

        paint.setAntiAlias(true); //부드럽게 그려주기(구분 안되게)
        canvas.drawCircle(400, 400, 200, paint); //원 그리기

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //사용자가 터치했을 때(모션 이벤트)
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(), "눌렸음 : " + event.getX() + ", " + event.getY(), Toast.LENGTH_LONG).show();
        }

        return super.onTouchEvent(event);
    }
}
