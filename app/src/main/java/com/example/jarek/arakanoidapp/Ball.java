package com.example.jarek.arakanoidapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Jarek on 2015-05-29.
 */
public class Ball
{

    public int x;
    public int y;
    public int xx = 1;
    public int yy = 1;
    public int r = 10;
    public RectF rectF;
    public MainGamePanel mainGamePanel;
    public int speed = 6;
    public boolean IsOutOfPanel = false;

    public Ball(MainGamePanel mainGamePanel)
    {
        this.mainGamePanel = mainGamePanel;
        rectF = new RectF();
        x = mainGamePanel.paddle.x + mainGamePanel.paddle.width / 2;
        y = mainGamePanel.paddle.y - r;
    }

    public void drawBall(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        rectF = new RectF(x, y, x + r, y + r);
        canvas.drawOval(rectF, paint);
    }

    public void update(int width, int height)
    {
        for (int i = 0; i < speed; i++)
        {
            x += xx;
            y += yy;
            if (x <= 0 || x + r >= width)
            {
                xx = -xx;
            }
            if (y <= 0)
            {
                yy = -yy;
            }
            if (y + r >= height)
                IsOutOfPanel = true;
            for (Block block : mainGamePanel.listOfBlocks)
                if (clashBlock(block))
                {
                    mainGamePanel.listOfBlocks.remove(block);
                    break;
                }
            pickupPaddle();
        }
    }

    public void pickupPaddle()
    {
        if (rectF.intersect(new RectF(mainGamePanel.paddle.rect)))
        {
            yy = -1;
        }
    }

    public boolean clashBlock(Block block)
    {
        if (rectF.intersect(new RectF(block.rectCoordinates)))
        {
            yy = -yy;
            return true;
        }
        else
        {
            return false;
        }
    }
}
