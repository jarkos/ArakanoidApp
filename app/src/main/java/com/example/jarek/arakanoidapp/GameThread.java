package com.example.jarek.arakanoidapp;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Jarek on 2015-05-29.
 */
public class GameThread extends Thread
{
    // Surface holder that can access the physical surface
    private SurfaceHolder surfaceHolder;
    private MainGamePanel mainGamePanel;

    // flag to hold game state
    private boolean running;
    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameThread(SurfaceHolder surfaceHolder, MainGamePanel mainGamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.mainGamePanel = mainGamePanel;
    }

    @Override
    public void run()
    {
        Canvas canvas;

        while (running)
        {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.mainGamePanel.update();
                    this.mainGamePanel.render(canvas);
                }
            } finally
            {

                if (canvas != null)
                {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
