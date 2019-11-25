package com.example.simbirsoftapp.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

<<<<<<< Updated upstream:SimbirSoftApp/app/src/main/java/com/example/simbirsoftapp/BottomLine.java
=======
import com.example.simbirsoftapp.R;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;


>>>>>>> Stashed changes:SimbirSoftApp/app/src/main/java/com/example/simbirsoftapp/ui/BottomLine.java
public class BottomLine extends View {

    private final Paint paint = new Paint();
    private final RectF oval = new RectF();
    private Resources res;

    public BottomLine(Context context, AttributeSet set) {
        super(context, set);
        init(context);
    }

<<<<<<< Updated upstream:SimbirSoftApp/app/src/main/java/com/example/simbirsoftapp/BottomLine.java
    private void init(Context context){
=======
    private void init() {
>>>>>>> Stashed changes:SimbirSoftApp/app/src/main/java/com/example/simbirsoftapp/ui/BottomLine.java
        this.setBackgroundColor(Color.TRANSPARENT);
        res = context.getResources();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = res.getDisplayMetrics().widthPixels;
        float center_x = width / 2;
        float circleDiam = getResources().getDimensionPixelSize(R.dimen.circle_diam);
        float buttonPadding = getResources().getDimensionPixelSize(R.dimen.button_padding);
        float center_y = (circleDiam / 2) + (2 * buttonPadding);
        float radius = (circleDiam / 2) + (2 * buttonPadding);
        float left = center_x - radius;
        float top = center_y - radius;
        float right = center_x + radius;
        float bottom = center_y + radius;

        oval.set(left + buttonPadding, top + buttonPadding,
                right - buttonPadding, bottom - buttonPadding);

        paint.setStyle(Paint.Style.STROKE);
<<<<<<< Updated upstream:SimbirSoftApp/app/src/main/java/com/example/simbirsoftapp/BottomLine.java
        paint.setStrokeWidth(buttonPadding);
        paint.setColor(getResources().getColor(R.color.white));
        canvas.drawArc(oval, 190, 160, false, paint);

        paint.setColor(getResources().getColor(R.color.grey));
        paint.setStrokeWidth(3);
=======
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
>>>>>>> Stashed changes:SimbirSoftApp/app/src/main/java/com/example/simbirsoftapp/ui/BottomLine.java
        paint.setAntiAlias(true);
        paint.setAlpha(60);
        canvas.drawArc(oval, 180, 20, false, paint);
        canvas.drawArc(oval, 340, 20, false, paint);
        canvas.drawLine(0,
                center_y - (float) Math.sin(Math.toRadians(20)) * radius,
                center_x - (float) Math.cos(Math.toRadians(20)) * radius,
                center_y - (float) Math.sin(Math.toRadians(20)) * radius,
                paint);
        canvas.drawLine(center_x + (float) Math.cos(Math.toRadians(20)) * radius,
                center_y - (float) Math.sin(Math.toRadians(20)) * radius,
                width,
                center_y - (float) Math.sin(Math.toRadians(20)) * radius,
                paint);

        paint.setAlpha(140);
        canvas.drawArc(oval, 200, 140, false, paint);
    }
}
