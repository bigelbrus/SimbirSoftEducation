package com.example.simbirsoftapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;



public class BottomLine extends View {

    private final Paint paint = new Paint();
    private final RectF oval = new RectF();
    private float circleDiam;
    private float buttonPadding;
    private float width;
    private int whiteColor;
    private int greyColor;

    private final float START_ANGLE_MIDDLE_ARC = 200f;      //must be between 180 and 270
    private final float SWEEP_ANGLE_EDGE_ARC = 20f;
    private final float SWEEP_ANGLE_MIDDLE_ARC = 2 * (270 - START_ANGLE_MIDDLE_ARC);
    private final float START_ANGLE_START_ARC = START_ANGLE_MIDDLE_ARC - SWEEP_ANGLE_EDGE_ARC;
    private final float START_ANGLE_END_ARC = START_ANGLE_MIDDLE_ARC + SWEEP_ANGLE_MIDDLE_ARC;
    private final float START_ANGLE_STROKE_BUTTON_PADDING = 190f;
    private final float SWEEP_ANGLE_STROKE_BUTTON_PADDING = 160f;

    private final double ANGLE_HELPER = toRadians(START_ANGLE_MIDDLE_ARC - 180f);

    private final int ALPHA_LIGHT = 60;
    private final int ALPHA_MEDIUM = 140;

    private final int STROKE_THIN = 3;


    public BottomLine(Context context, AttributeSet set) {
        super(context, set);
        init();
    }

    private void init(){
        this.setBackgroundColor(Color.TRANSPARENT);
        Resources res = getResources();

        width = res.getDisplayMetrics().widthPixels;
        circleDiam = res.getDimensionPixelSize(R.dimen.circle_diam);
        buttonPadding = res.getDimensionPixelSize(R.dimen.button_padding);

        whiteColor = res.getColor(R.color.white);
        greyColor = res.getColor(R.color.grey);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float center_x = width / 2;
        float center_y = (circleDiam / 2) + (2 * buttonPadding);
        float radius = (circleDiam / 2) + (2 * buttonPadding);
        float left = center_x - radius;
        float top = center_y - radius;
        float right = center_x + radius;
        float bottom = center_y + radius;

        oval.set(left, top, right, bottom);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(buttonPadding);
        paint.setColor(whiteColor);
        canvas.drawArc(oval,
                START_ANGLE_STROKE_BUTTON_PADDING,
                SWEEP_ANGLE_STROKE_BUTTON_PADDING,
                false,
                paint);

        paint.setColor(greyColor);
        paint.setStrokeWidth(STROKE_THIN);
        paint.setAntiAlias(true);
        paint.setAlpha(ALPHA_LIGHT);
        canvas.drawArc(oval, START_ANGLE_START_ARC, SWEEP_ANGLE_EDGE_ARC, false, paint);
        canvas.drawArc(oval, START_ANGLE_END_ARC, SWEEP_ANGLE_EDGE_ARC, false, paint);
        canvas.drawLine(0,
                center_y - (float) sin(ANGLE_HELPER) * radius,
                center_x - (float) cos(ANGLE_HELPER) * radius,
                center_y - (float) sin(ANGLE_HELPER) * radius,
                paint);
        canvas.drawLine(center_x + (float) cos(ANGLE_HELPER) * radius,
                center_y - (float) sin(ANGLE_HELPER) * radius,
                width,
                center_y - (float) sin(ANGLE_HELPER) * radius,
                paint);

        paint.setAlpha(ALPHA_MEDIUM);
        canvas.drawArc(oval, START_ANGLE_MIDDLE_ARC, SWEEP_ANGLE_MIDDLE_ARC, false, paint);
    }
}
