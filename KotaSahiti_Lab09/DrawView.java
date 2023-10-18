package com.example.lab09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite1;
    Sprite sprite2;
    Sprite sprite3;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite1 = new Sprite();
        sprite2 = new Sprite(3,4, Color.MAGENTA);
        sprite3 = new Sprite(1,2, Color.RED);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sprite1.update();
        sprite1.draw(canvas);
        sprite2.update();
        sprite2.draw(canvas);

        if (sprite2.intersect(sprite1)) {
            sprite3.update();
            sprite3.draw(canvas);
        }
        invalidate();
    }
}
