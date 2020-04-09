package org.techtown.customviewimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewImage extends View {
    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public CustomViewImage(Context context) {
        super(context);

        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        paint = new Paint();
    }

    //뷰가 차지하는 영역이 결정될 때 호출
    //그리는데 소요되는 시간 감축됨(메모리에 먼저 그리게 되므로)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        createCacheBitmap(w, h);
        testDrawing();
    }

    public void createCacheBitmap(int w, int h) {
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); //메모리에 RGB색상으로 된 새로운 비트맵 객체 생성
        cacheCanvas = new Canvas(); //cacheCanvas: 메모리에 만들어진 것
        cacheCanvas.setBitmap(cacheBitmap);

    }

    public void testDrawing() {
        Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        cacheCanvas.drawBitmap(srcImg, 100, 100, paint);

        //상하로 이미지 뒤집어 줌
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        Bitmap inverseBitmap = Bitmap.createBitmap(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), matrix, false);
        cacheCanvas.drawBitmap(inverseBitmap, 300, 300, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (cacheBitmap != null) {
            canvas.drawBitmap(cacheBitmap, 0, 0, null);
        }

    }
}
