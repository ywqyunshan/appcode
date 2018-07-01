package com.iigeo.appcode.base;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.iigeo.appcode.R;
import com.iigeo.appcode.praticeview.SportsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 动画
 * ViewPropertyAnimator——不断更新View的属性
 * ObjectAnimator
 */
public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.animationIv)
    ImageView animationIv;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.sportsView)
    SportsView sportsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        // ViewPropertyAmimation
        //animationIv.animate().translationX(500).setDuration(500).setInterpolator(new AnticipateInterpolator());
        // ObjectAmimation
        //ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(animationIv,"translationX",100);
        //objectAnimator.start();

        ObjectAnimator animator = ObjectAnimator.ofFloat(sportsView,"progress",100);
        animator.start();
    }
}
