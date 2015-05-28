package com.example.jarek.arakanoidapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarek on 2015-05-28.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private List<Block> listOfBlocks;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public MainGamePanel(Context context)
    {
        super(context);
        listOfBlocks = new ArrayList<Block>();

        getHolder().addCallback(this);
        surfaceHolder = getHolder();
        canvas = this.surfaceHolder.lockCanvas();
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        canvas = this.surfaceHolder.lockCanvas();
        for (int i = 0; i < 36; i++)
            listOfBlocks.add(new Block(20 + ((getWidth() - 40) / 6) * (i % 6), 30 * (i / 6) + 40, (getWidth() - 30) / 10, 25));
        this.render(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
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
    }
}
