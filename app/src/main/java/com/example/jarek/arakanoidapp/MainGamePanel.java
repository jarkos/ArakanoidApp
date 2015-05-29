package com.example.jarek.arakanoidapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarek on 2015-05-28.
 */

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public List<Block> listOfBlocks;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    public Paddle paddle;
    private GameThread th;

    public MainGamePanel(Context context)
    {
        super(context);
        listOfBlocks = new ArrayList<Block>();
        getHolder().addCallback(this);
        surfaceHolder = getHolder();
//        canvas = this.surfaceHolder.lockCanvas();
        th = new GameThread(surfaceHolder, this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        th.setRunning(true);
        th.start();
        //canvas = this.surfaceHolder.lockCanvas();
        for (int i = 0; i < 36; i++)
            listOfBlocks.add(new Block(20 + ((getWidth() - 40) / 6) * (i % 6), 30 * (i / 6) + 20, (getWidth()) / 7, 25));
        paddle = new Paddle(getWidth(), getHeight() - 50);
       // this.render(canvas);
       // surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }

    public void render(Canvas canvas)
    {
        canvas.drawColor(Color.BLUE);
        for (Block block : listOfBlocks)
            block.paintBrick(canvas);
        paddle.draw(canvas);
    }

    public void update()
    {
        paddle.update(getWidth());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
        {
            paddle.onTouch = true;
            if (event.getX() * 2 < getWidth()) //jak jest sterowanie paletka
                paddle.left = true;
            else
                    paddle.left = false;

        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            paddle.onTouch = false;
        }
        return true;
    }
}
