package com.example.circles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by Например_Андрей on 28.03.2016.
 */
public class Feild extends View {
    //сравнение линий
    public static boolean comp (Lines line1, Lines line2){
        Lines l1 = new Lines(line1.x1, line1.y1, line1.x2, line1.y2, line1.paint);
        Lines l2 = new Lines(line2.x1, line2.y1, line2.x2, line2.y2, line2.paint);
        if ((l1.x1==l2.x1  &&  l1.y1==l2.y1  &&  l1.x2==l2.x2  &&  l1.y2==l2.y2)||
                (l1.x1==l2.x2&&l1.y1==l2.y2&&l1.x2==l2.x1&&l1.y2==l2.y1)){
            return true;
        }
        else{
            return false;
        }
    }

    float r = 25, x2 = 0, y2 = 0, n = 0, Xch=0, Ych=0, peri=1000, peri2=1000;
    //список линий для рисовки
    LinkedList<Lines> linkedList = new LinkedList();
    Dots dots[][] = new Dots[5][5], perdot;
    boolean ch = true;
    public Feild(Context context){
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas){
        float x = 100, y = 100;
        Paint p = new Paint();
        p.setColor(Color.RED);
        //отрисовка точек на поле
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                if (ch) {
                    dots[i][i2] = new Dots(x, y, r, p);
                }
                if (i==peri&&i2==peri2) {
                    p.setColor(Color.BLUE);
                    canvas.drawCircle(dots[i][i2].x, dots[i][i2].y, dots[i][i2].radius, p);
                    p.setColor(Color.RED);
                }
                else{
                    canvas.drawCircle(dots[i][i2].x, dots[i][i2].y, dots[i][i2].radius, p);
                }
                canvas.drawRect(1000, 50, 1250, 150, p);
                p.setColor(Color.WHITE);
                p.setTextSize(30);
                canvas.drawText("Reset", 1075, 100, p);
                p.setColor(Color.RED);
                x = x + 125;
            }
            y += 125;
            x = 100;
        }
        //отрисовка линий на поле
        for (int i = 0;i<linkedList.size();i++){
            Lines perline = linkedList.get(i);
            canvas.drawLine(perline.x1, perline.y1, perline.x2, perline.y2, perline.paint);
        }
        ch = false;
        boolean open = true;
        //проверка на нажатие точки
        for (int i=0;i<5;i++) {
            for (int i2=0;i2<5;i2++) {
                if (x2!=Xch&&y2!=Ych) {
                    if ((Math.abs(x2 - dots[i][i2].x) <= dots[i][i2].radius) &&
                            (Math.abs(y2 - dots[i][i2].y) <= dots[i][i2].radius)) {
                        if (n==0) {
                            peri = i;
                            peri2 = i2;
                        }
                        n++;
                        //проверка на количество нажатий на кружки
                        if (n==1)
                            perdot = dots[i][i2];
                        if (n==2){
                            n = 1;
                            if (dots[i][i2]!=perdot) {
                                p.setColor(Color.BLUE);
                                p.setStrokeWidth(10);
                                //проверка на уникальность новой линии
                                Lines new_line = new Lines(perdot.x, perdot.y, dots[i][i2].x, dots[i][i2].y, p);
                                for (int i3 = 0; i3 < linkedList.size(); i3++) {
                                    Lines per_line = linkedList.get(i3);
                                    if (comp(per_line, new_line))
                                        open = false;
                                }
                                //добавление линии на рисунок
                                if (open) {
                                    linkedList.add(new_line);
                                    perdot = dots[i][i2];
                                    peri=i;
                                    peri2=i2;
                                }
                            }
                        }
                        Xch = x2;
                        Ych = y2;
                    }
                }
            }
        }
        //кнопка сброса всех линий
        if (x2<1250&&x2>1000&&y2<150&&y2>50){
            int size = linkedList.size();
            for (int i=0;i<size;i++){
                linkedList.remove();
                n=0;
                peri2=1000;
                peri=1000;
            }
        }
    }
    //считывание координат нажатия
    @Override
    public boolean onTouchEvent(MotionEvent event){
        x2 = event.getX();
        y2 = event.getY();


        invalidate();
        return true;
    }
}
