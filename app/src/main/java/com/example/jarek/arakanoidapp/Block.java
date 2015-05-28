package com.example.jarek.arakanoidapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Jarek on 2015-05-28.
 */
public class Block
{
    public Paint paint;
    public Rect rectCoordinates;

    public Block(int x, int y, int width, int height)
    {
        rectCoordinates = new Rect(x, y, x + width, y + height);
        paint = new Paint();
        paint.setColor(Color.YELLOW);

    }

    public void paintBrick(Canvas canvas)
    {
        canvas.drawRect(rectCoordinates, paint);
    }
}
