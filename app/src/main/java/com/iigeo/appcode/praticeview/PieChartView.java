package com.iigeo.appcode.praticeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class PieChartView extends View{

    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor=Color.BLUE;

    public PieChartView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
