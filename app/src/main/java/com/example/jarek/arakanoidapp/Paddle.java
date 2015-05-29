package com.example.jarek.arakanoidapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Paddle
{
	public int x;
	public int y;
	public Rect rect;
	public int width = 100;
	public int speed = 6;
	public boolean onTouch = false;
	public boolean left = false;
	
	public Paddle(int x, int y)
	{
		this.x = (int) (Math.random()*(x - width));
		this.y = y;
		rect = new Rect();
	}

	public void draw(Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		rect = new Rect(x, y, x + width, y + 20);
		canvas.drawRect(rect, paint);
	}

	public void update(int screenWidth)
	{
			if (onTouch)
			{
				if (left)
				{
					x -= speed;
					if (x < 0)
						x = 0;
				} 
				else 
				{
					x += speed;
					if (x + width > screenWidth)
						x = screenWidth - width;
				}
			}
	}
}
