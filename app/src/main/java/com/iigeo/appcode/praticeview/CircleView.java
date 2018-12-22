package com.iigeo.appcode.praticeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.iigeo.appcode.R;

/**
 * 继承View，全新自定义尺寸
 */
public class CircleView extends View {

    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor=Color.BLUE;
    Shader mShader;

    private int defaultSize=500;
    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor=typedArray.getColor(R.styleable.CircleView_circle_color,Color.RED);
        typedArray.recycle();
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingTop=getPaddingTop();
        int paddingBottom=getPaddingBottom();
        int width=getWidth()-paddingLeft-paddingRight;
        int height=getHeight()-paddingTop-paddingBottom;

        int radius=Math.min(width,height)/2;
        //线性渐变
        mShader=new LinearGradient(paddingLeft,paddingTop,paddingLeft+width,paddingTop+height,
                Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        //
        paint.setShader(mShader);
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1.根据父View的MeasureSpec计算尺寸
        int width=getMySize(defaultSize,widthMeasureSpec);
        int height=getMySize(defaultSize,heightMeasureSpec);
        //保存计算尺寸
        setMeasuredDimension(width,height);
    }

    void init(){
        paint.setColor(mColor);
    }

    int getMySize(int defaultSize,int measureSpec){
        int mSize=defaultSize;
        int mode=MeasureSpec.getMode(measureSpec);
        int size=MeasureSpec.getSize(measureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                mSize=defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                if (defaultSize<size) mSize=defaultSize;
                else mSize=size;
                 break;
            case MeasureSpec.EXACTLY:
                mSize=size;
                break;
        }
        return mSize;
    }

}
