package com.example.simbirsoftapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.simbirsoftapp.R;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class BottomLine extends View {
    private final Paint paint = new Paint();
    private final RectF oval = new RectF();
    private int whiteColor;
    private int greyColor;
    private static final float START_ANGLE_MIDDLE_ARC = 200f;   //must be between 180 and 270
    private static final float SWEEP_ANGLE_MIDDLE_ARC = 2 * (270 - START_ANGLE_MIDDLE_ARC);
    private static final float SWEEP_ANGLE_EDGE_ARC = 20f;

    private static final float START_ANGLE_STROKE_BUTTON_PADDING = 190f;
    private static final float SWEEP_ANGLE_STROKE_BUTTON_PADDING = 160f;
    private static final float START_ANGLE_START_ARC = START_ANGLE_MIDDLE_ARC - SWEEP_ANGLE_EDGE_ARC;
    private static final float START_ANGLE_END_ARC = START_ANGLE_MIDDLE_ARC + SWEEP_ANGLE_MIDDLE_ARC;

    private static final float STROKE_THIN = 3;

    private static final int ALPHA_LIGHT = 60;
    private static final int ALPHA_MIDDLE = 140;

    private float width;
    private float circleDiam;
    private float buttonPadding;

    public BottomLine(Context context, AttributeSet set) {
        super(context, set);
        init(context);
    }

    private void init(Context context) {
        this.setBackgroundColor(Color.TRANSPARENT);
        Resources res = context.getResources();

        whiteColor = res.getColor(R.color.white);
        greyColor = res.getColor(R.color.grey);
        width = res.getDisplayMetrics().widthPixels;
        circleDiam = res.getDimensionPixelSize(R.dimen.circle_diam);
        buttonPadding = res.getDimensionPixelSize(R.dimen.button_padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = width / 2;
        float centerY = (circleDiam / 2) + (2 * buttonPadding);
        float radius = (circleDiam / 2) + (2 * buttonPadding);
        float left = centerX - radius;
        float top = centerY - radius;
        float right = centerX + radius;
        float bottom = centerY + radius;
        double helpAngle = toRadians(START_ANGLE_MIDDLE_ARC - 180);

        oval.set(left + buttonPadding, top + buttonPadding,
                right - buttonPadding, bottom - buttonPadding);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2 * buttonPadding);
        paint.setColor(whiteColor);
        canvas.drawArc(oval,
                START_ANGLE_STROKE_BUTTON_PADDING,
                SWEEP_ANGLE_STROKE_BUTTON_PADDING,
                false,
                paint);

        oval.set(left, top, right, bottom);

        paint.setColor(greyColor);
        paint.setStrokeWidth(STROKE_THIN);
        paint.setAntiAlias(true);
        paint.setAlpha(ALPHA_LIGHT);
        canvas.drawArc(oval, START_ANGLE_START_ARC, SWEEP_ANGLE_EDGE_ARC, false, paint);
        canvas.drawArc(oval, START_ANGLE_END_ARC, SWEEP_ANGLE_EDGE_ARC, false, paint);
        canvas.drawLine(0,
                centerY - (float) sin(helpAngle) * radius,
                centerX - (float) cos(helpAngle) * radius,
                centerY - (float) sin(helpAngle) * radius,
                paint);
        canvas.drawLine(centerX + (float) cos(helpAngle) * radius,
                centerY - (float) sin(helpAngle) * radius,
                width,
                centerY - (float) sin(helpAngle) * radius,
                paint);

        paint.setAlpha(ALPHA_MIDDLE);
        canvas.drawArc(oval, START_ANGLE_MIDDLE_ARC, SWEEP_ANGLE_MIDDLE_ARC, false, paint);
    }
}
