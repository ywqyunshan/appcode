package com.iigeo.appcode.praticeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.iigeo.appcode.R;


public class CircleImageView extends ImageView {

    //开启抗锯齿
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath=new Path();
    private Bitmap mBitmap;

    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_pepole);
        mPath.addCircle(200,200,100,Path.Direction.CCW);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap!=null){
            //canvas.clipPath(mPath);
            canvas.drawBitmap(mBitmap,100,100,mPaint);
        }
    }

}
