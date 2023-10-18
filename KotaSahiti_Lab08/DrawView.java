package com.kotasahiti.l08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Paint p = new Paint();
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.BLUE);
        canvas.drawRect(0,0, 5000, 5000, p);
        p.setColor(Color.MAGENTA);
        canvas.drawCircle(400, 100, 20f, p);
        canvas.drawCircle(800, 200, 20f, p);
        canvas.drawCircle(350, 1500, 20f, p);
        canvas.drawCircle(600, 1700, 20f, p);
        canvas.drawCircle(900, 800, 20f, p);
        canvas.drawCircle(70, 40, 20f, p);
        canvas.drawCircle(900, 400, 20f, p);
        canvas.drawCircle(500, 200, 20f, p);
        canvas.drawCircle(350, 700, 20f, p);
        canvas.drawCircle(100, 650, 20f, p);
        canvas.drawCircle(780, 920, 20f, p);
        canvas.drawCircle(820, 100, 20f, p);
        canvas.drawCircle(730, 1000, 20f, p);
        canvas.drawCircle(970, 1600, 20f, p);
        canvas.drawCircle(940, 1500, 20f, p);
        canvas.drawCircle(800, 800, 20f, p);
        canvas.drawCircle(830, 1030, 20f, p);
        canvas.drawCircle(200, 1800, 20f, p);
        canvas.drawCircle(400, 2000, 20f, p);
        canvas.drawCircle(630, 1870, 20f, p);
        canvas.drawCircle(820, 1900, 20f, p);
        p.setColor(Color.YELLOW);
        canvas.drawCircle(530, 800, 300f, p);
        p.setColor(Color.BLACK);
        canvas.drawCircle(390, 750, 50f, p);
        canvas.drawCircle(670, 750, 50f, p);
        canvas.drawCircle(530, 900, 100f, p);
    }
}
