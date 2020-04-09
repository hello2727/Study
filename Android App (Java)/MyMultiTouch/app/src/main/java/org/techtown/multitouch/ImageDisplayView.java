package org.techtown.multitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ImageDisplayView extends View {
    private static final String TAG = "ImageDisplay";

    Paint paint;
    Matrix matrix;

    int lastX;
    int lastY;

    Bitmap mBitmap;
    Canvas mCanvas;

    float displayWidth = 0.0F;
    float displayHeight = 0.0F;

    int displayCenterX = 0;
    int displayCenterY = 0;

    public float startX;
    public float startY;

    float oldDistance = 0.0F;

    int oldPointerCount = 0;
    boolean isScrolling = false;
    float distanceThreshold = 3.0F;

    float scaleRatio;
    float totalScaleRatio;

    public static float MAX_SCALE_RATIO = 5.0F;
    public static float MIN_SCALE_RATIO = 0.1F;

    float bitmapCenterX;
    float bitmapCenterY;

    Bitmap sourceBitmap;

    float sourceWidth = 0.0F;
    float sourceHeight = 0.0F;

    public ImageDisplayView(Context context) {
        super(context);

        init(context);
    }

    public ImageDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        paint = new Paint();
        matrix = new Matrix(); //이미지 변환에 필요

        lastX = -1;
        lastY = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(w > 0 && h > 0) {
            newImage(w, h); //새로운 이미지 만들어주기
        }

        redraw(); //초기화(새로 그려주기)
    }

    public void newImage(int width, int height) {
        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);

        mBitmap = img;
        mCanvas = canvas;

        displayWidth = (float)width;
        displayHeight = (float)height;

        displayCenterX = width/2;
        displayCenterY = height/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    public void setImageData(Bitmap image) {
        recycle();

        sourceBitmap = image;

        sourceWidth = sourceBitmap.getWidth();
        sourceHeight = sourceBitmap.getHeight();

        bitmapCenterX = sourceBitmap.getWidth()/2;
        bitmapCenterY = sourceBitmap.getHeight()/2;

        scaleRatio = 1.0F;
        totalScaleRatio = 1.0F;
    }

    public void recycle() {
        if (sourceBitmap != null) {
            sourceBitmap.recycle(); //비트맵 객체 해제
        }
    }

    public void redraw() {
        if (sourceBitmap == null) {
            Log.d(TAG, "sourceBitmap is null in redraw().");
            return;
        }

        drawBackground(mCanvas);

        float originX = (displayWidth - (float)sourceBitmap.getWidth()) / 2.0F;
        float originY = (displayHeight - (float)sourceBitmap.getHeight()) / 2.0F;

        mCanvas.translate(originX, originY);
        mCanvas.drawBitmap(sourceBitmap, matrix, paint);
        mCanvas.translate(-originX, -originY);

        invalidate();
    }

    public void drawBackground(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        int pointerCount = event.getPointerCount(); //손가락 갯수 확인
        Log.d(TAG, "Pointer Count : " + pointerCount);

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                if (pointerCount == 1) { //손가락 1개면
                    //이전 좌표값
                    float curX = event.getX();
                    float curY = event.getY();

                    //눌렀을때 필요한 변수(현재 좌표값)
                    startX = curX;
                    startY = curY;

                } else if (pointerCount == 2) { //손가락 2개
                    oldDistance = 0.0F; //손가락 사이의 거리(거리값에 따라 이미지 확대)

                    isScrolling = true; //손가락 계속 움직이고 있는지 확인
                }

                return true;
            case MotionEvent.ACTION_MOVE:

                if (pointerCount == 1) {

                    if (isScrolling) {
                        return true;
                    }

                    float curX = event.getX();
                    float curY = event.getY();

                    if (startX == 0.0F) {
                        startX = curX;
                        startY = curY;

                        return true;
                    }

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointerCount == 2) {

                    } else {
                        Log.d(TAG, "ACTION_MOVE : " + offsetX + ", " + offsetY);

                        if (totalScaleRatio > 1.0F) { //거리만큼
                            moveImage(-offsetX, -offsetY); //움직이기
                        }

                        startX = curX;
                        startY = curY;
                    }

                } else if (pointerCount == 2) { //손가락 2개일때

                    float x1 = event.getX(0);
                    float y1 = event.getY(0);
                    float x2 = event.getX(1);
                    float y2 = event.getY(1);

                    float dx = x1 - x2;
                    float dy = y1 - y2;
                    //두 손가락 사이 거리 계산
                    float distance = new Double(Math.sqrt(new Float(dx * dx + dy * dy).doubleValue())).floatValue();

                    float outScaleRatio = 0.0F;
                    if (oldDistance == 0.0F) {
                        oldDistance = distance;

                        break;
                    }

                    if (distance > oldDistance) {
                        if ((distance-oldDistance) < distanceThreshold) {
                            return true;
                        }

                        outScaleRatio = scaleRatio + (oldDistance / distance * 0.05F);
                    } else if (distance < oldDistance) {
                        if ((oldDistance-distance) < distanceThreshold) {
                            return true;
                        }

                        outScaleRatio = scaleRatio - (distance / oldDistance * 0.05F);
                    }

                    if (outScaleRatio < MIN_SCALE_RATIO || outScaleRatio > MAX_SCALE_RATIO) {
                        Log.d(TAG, "Invalid scaleRatio : " + outScaleRatio);
                    } else {
                        Log.d(TAG, "Distance : " + distance + ", ScaleRatio : " + outScaleRatio);
                        scaleImage(outScaleRatio); //확대, 축소 위한것
                    }

                    oldDistance = distance;
                }

                oldPointerCount = pointerCount;

                break;

            case MotionEvent.ACTION_UP:

                if (pointerCount == 1) {

                    float curX = event.getX();
                    float curY = event.getY();

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointerCount == 2) {

                    } else {
                        moveImage(-offsetX, -offsetY);
                    }

                } else {
                    isScrolling = false;
                }

                return true;
        }

        return true;
    }

    private void scaleImage(float inScaleRatio) {
        Log.d(TAG, "scaleImage() called : " + inScaleRatio);

        matrix.postScale(inScaleRatio, inScaleRatio, bitmapCenterX, bitmapCenterY);
        matrix.postRotate(0);

        totalScaleRatio = totalScaleRatio * inScaleRatio;

        redraw();
    }

    private void moveImage(float offsetX, float offsetY) {
        Log.d(TAG, "moveImage() called : " + offsetX + ", " + offsetY);

        matrix.postTranslate(offsetX, offsetY);

        redraw();
    }

}
