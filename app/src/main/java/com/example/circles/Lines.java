package com.example.circles;

import android.graphics.Paint;

/**
 * Created by Например_Андрей on 02.04.2016.
 */
public class Lines {
    float x1, x2, y1, y2;
    Paint paint;
    Lines(float x1, float y1, float x2, float y2, Paint paint){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.paint = paint;
    }
}
