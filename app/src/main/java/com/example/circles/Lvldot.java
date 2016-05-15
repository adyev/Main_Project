package com.example.circles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Например_Андрей on 02.05.2016.
 */
public class Lvldot {
    float x;
    float y;
    float radius;
    Paint paint;
    boolean exist = false;
    float lvlnum;
    Lvldot(float x, float y, float radius, Paint paint, float lvlnum){
        this.x=x;
        this.y=y;
        this.radius=radius;
        this.paint = paint;
        this.lvlnum = lvlnum;
    }
    public void draw_circle(Canvas canvas){
            canvas.drawCircle(x, y, radius, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(25);
            canvas.drawText("" + (int) (lvlnum+1), x-10, y+5, paint);
    }
}
