package com.dong.drawdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by BZT on 2016/2/26.
 */
public class ShiZhongView extends View {

    private static final String TAG = "ShiZhongView";
    private int radius;//半径
    private int width;//正方形控件边长
    private int padding = 10;
    private int count = 12;//圆盘刻度数

    private Paint hPaint;
    private Paint lPaint;
    private Paint hTextPaint;
    private Paint lTextPaint;

    private Paint paint;
    public ShiZhongView(Context context) {
        this(context, null);
    }

    public ShiZhongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);


        //大刻度
        hPaint = new Paint(paint);
        hPaint.setStrokeWidth(2);
        //小刻度
        lPaint = new Paint(paint);
        lPaint.setStrokeWidth(1);

        //大字
        hTextPaint = new Paint(paint);
        hTextPaint.setStyle(Paint.Style.FILL);
        hTextPaint.setStrokeWidth(1);
        hTextPaint.setTextSize(30);
        //小字
        lTextPaint = new Paint(paint);
        lTextPaint.setStyle(Paint.Style.FILL);
        lTextPaint.setStrokeWidth(1);
        lTextPaint.setTextSize(22);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = Math.min(getMeasuredWidth(),getMeasuredHeight());
        setMeasuredDimension(width, width);
        radius = (width-2*padding)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(padding + radius, padding + radius);
        //画圆盘
        canvas.drawCircle(0, 0, radius, paint);



        canvas.save();
        for(int i=0;i<count;i++){
            if(i%6==0){
                canvas.drawLine(0,-radius,0,-radius+40,hPaint);

                String s = String.valueOf(i);
                float textWidth = hTextPaint.measureText(s);
                canvas.drawText(s,-textWidth/2,-radius+65,hTextPaint);
            }else{
                canvas.drawLine(0,-radius,0,-radius+10,lPaint);

                String s = String.valueOf(i);
                float textWidth = lTextPaint.measureText(s);
                canvas.drawText(s,-textWidth/2,-radius+40,lTextPaint);
            }
            canvas.rotate(360/count);
        }
        canvas.restore();

        //画中间的圆圈
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(0, 0, 40, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(0,0,25,paint);

        //画分针
        paint.setStrokeWidth(3);
        canvas.drawLine(0,0,0,-radius/2,paint);

        //画时针
        paint.setStrokeWidth(1);
        canvas.drawLine(0,0,radius*2/5,0,paint);

        //画秒针
        canvas.drawLine(0,0,(radius/2)*5/6,(radius/2)*5/6,paint);


    }
}
