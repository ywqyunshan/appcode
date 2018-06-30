package com.iigeo.appcode.praticeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 修改已有view尺寸
 * 继承已有View
 */
public class SquareImageView extends AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1.执行原来测量的结果
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //2.获取原有测量结果
        int measuredWidth=getMeasuredWidth();
        int measuredHeight=getMeasuredHeight();
        //计算新尺寸
        if (measuredWidth>measuredHeight){
            measuredWidth=measuredHeight;
        }else {
            measuredHeight=measuredWidth;
        }
        //保存计算尺寸
        setMeasuredDimension(measuredWidth,measuredHeight);
    }


}
