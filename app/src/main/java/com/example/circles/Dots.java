package com.example.circles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Например_Андрей on 27.03.2016.
 */
public class Dots {
    float x;
    float y;
    Paint paint;
    float radius;
    float number;
    boolean exist;
    Dots(float x, float y, float radius, Paint paint, float number, boolean exist){
        this.x=x;
        this.y=y;
        this.radius = radius;
        this.paint = paint;
        this.number = number;
        this.exist = exist;
    }
}
