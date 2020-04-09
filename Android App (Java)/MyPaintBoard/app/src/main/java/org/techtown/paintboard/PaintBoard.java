package org.techtown.paintboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintBoard extends View {
    Paint paint;
    Canvas mCanvas;
    Bitmap mBitmap;

    int lastX;
    int lastY;

    public PaintBoard(Context context) {
        super(context);

        init(context);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.BLACK);

        //이전 좌표값
        lastX = -1;
        lastY = -1;
    }

    //뷰에 사이즈가 정해졌을 때 만들어지는 함수(메모리에 비트맵 객체 하나 만들어줌)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_UP: //손가락 뗐을때
                lastX = -1;
                lastY = -1;
                break;
            case MotionEvent.ACTION_DOWN: //손가락 눌렀을 때
                if (lastX != -1) {
                    if (X !=lastX || Y != lastY) {
                        mCanvas.drawLine(lastX, lastY, X, Y, paint);
                    }
                }

                //현재 값 넣어줌
                lastX = X;
                lastY = Y;

                break;
            case MotionEvent.ACTION_MOVE: //움직이면
                if (lastX != -1) {
                    mCanvas.drawLine(lastX, lastY, X, Y, paint);
                }

                lastX = X;
                lastY = Y;

                break;
        }

        //화면에 보여주기
        invalidate();

        //다시 그려주기
        return true;
    }

}
