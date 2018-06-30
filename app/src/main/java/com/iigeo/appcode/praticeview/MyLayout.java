package com.iigeo.appcode.praticeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 自定义Layout
 */
public class MyLayout extends ViewGroup{


    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1.计算每个子View的MeasureSpec
        int count=getChildCount();
        //1.1 获取父View的限制MeasureSpec
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        for (int i=0;i<count;i++){
            int childMeasureSpec;
            View subView=getChildAt(i);
            //1.2 获取开发者定义的参数
            LayoutParams layoutParams=subView.getLayoutParams();
            switch (layoutParams.width){
                case WRAP_CONTENT:
                    if (widthMode==MeasureSpec.EXACTLY||widthMode==MeasureSpec.AT_MOST){
                        childMeasureSpec=MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.AT_MOST);
                    }else {
                        childMeasureSpec=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                    }
                    break;
                case MATCH_PARENT:
                    if (widthMode==MeasureSpec.EXACTLY||widthMode==MeasureSpec.AT_MOST){
                        childMeasureSpec=MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY);
                    }else {
                        childMeasureSpec=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
                    }
                    break;
                default:
                    childMeasureSpec=MeasureSpec.makeMeasureSpec(layoutParams.width,MeasureSpec.EXACTLY);
                    break;
            }
            //2 保存子view尺寸
            //3 计算自己的尺寸并保存

        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //布局子view位置
        for (int j=0;j<getChildCount();j++){
            View subView=getChildAt(i);
            subView.layout(subView.getLeft(),subView.getTop(),subView.getRight(),subView.getBottom());
        }
    }


}
