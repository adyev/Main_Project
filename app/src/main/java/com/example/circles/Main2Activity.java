package com.example.circles;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import java.util.LinkedList;

public class Main2Activity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(new Feild2(this));
    }
    public class Feild2 extends View  {
        //сравнение линий
        public boolean comp (Lines line1, Lines line2){
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
        int mass[][] = new int[25][25];
        float r = 25, x2 = 0, y2 = 0, num = 0, n = 0, Xch=0, Ych=0, peri=1000, peri2=1000;
        int yourlvl[][] = new int[25][25];
        int number = getIntent().getIntExtra("number_of_levels", 1);
        //список линий для рисовки
        LinkedList<Lines> lines = new LinkedList();
        Dots dots[][] = new Dots[5][5], perdot;
        boolean exist = false;
        boolean ch = true, win = false, win_check;
        public Feild2(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            win_check=true;
            //заполнение уровней
            mass[20][2]=1; mass[2][20]=1;
            mass[5][9]=1;  mass[9][5]=1;
            mass[5][24]=1; mass[24][5]=1;
            mass[9][20]=1; mass[20][9]=1;
            mass[24][2]=1; mass[2][24]=1;
            Lvl lvls[] = new Lvl[25];
            lvls[0]=new Lvl(mass, 5);
            for(int i=0;i<25;i++){
                for(int i2=0;i2<25;i2++){
                    mass[i][i2]=0;
                }
            }
            mass[20][2]=1; mass[2][20]=1;
            mass[5][9]=1;  mass[9][5]=1;
            mass[5][24]=1; mass[24][5]=1;
            mass[9][20]=1; mass[20][9]=1;
            mass[24][2]=1; mass[2][24]=1;
            mass[24][20]=1; mass[20][24]=1;
            mass[20][5]=1; mass[5][20]=1;
            mass[5][2]=1; mass[2][5]=1;
            mass[2][9]=1; mass[9][2]=1;
            mass[9][24]=1; mass[24][9]=1;
            lvls[1]=new Lvl(mass, 10);
            for(int i=0;i<25;i++){
                for(int i2=0;i2<25;i2++){
                    mass[i][i2]=0;
                }
            }
            mass[20][10]=1; mass[10][20]=1;
            mass[10][7]=1;  mass[7][10]=1;
            mass[7][14]=1; mass[14][7]=1;
            mass[14][24]=1; mass[24][14]=1;
            mass[24][10]=1; mass[10][24]=1;
            mass[24][20]=1; mass[20][24]=1;
            mass[20][14]=1; mass[14][20]=1;
            mass[10][14]=1; mass[14][10]=1;
            lvls[2]=new Lvl(mass, 2);
            float x = 100, y = 100;
            Paint p = new Paint();
            p.setColor(Color.RED);
            if(!win) {
                //отрисовка точек на поле
                for (int i = 0; i < 5; i++) {
                    for (int i2 = 0; i2 < 5; i2++) {
                        if (ch) {
                            for (int i3 = 0; i3 < 25; i3++) {
                                if (lvls[number].level[(int) n][i3] == 1) {
                                    exist = true;
                                }
                            }
                            dots[i][i2] = new Dots(x, y, r, p, n, exist);
                            exist = false;
                            n++;
                        }
                        if (dots[i][i2].exist) {
                            if (i == peri && i2 == peri2) {
                                p.setColor(Color.BLUE);
                                canvas.drawCircle(dots[i][i2].x, dots[i][i2].y, dots[i][i2].radius, p);
                                p.setColor(Color.RED);
                            } else {
                                canvas.drawCircle(dots[i][i2].x, dots[i][i2].y, dots[i][i2].radius, p);
                            }
                        }
                        //кнопка сброса линий
                        canvas.drawRect(1000, 50, 1250, 150, p);
                        p.setColor(Color.WHITE);
                        p.setTextSize(30);
                        canvas.drawText("Reset", 1075, 100, p);
                        p.setColor(Color.RED);
                        //кнопка возврата к выбору уровней
                        canvas.drawRect(1000, 500, 1250, 600, p);
                        p.setColor(Color.WHITE);
                        canvas.drawText("level pick", 1075, 550, p);
                        p.setColor(Color.RED);
                        x = x + 125;
                    }
                    y += 125;
                    x = 100;
                }
                //отрисовка линий на поле
                for (int i = 0; i < lines.size(); i++) {
                    Lines perline = lines.get(i);
                    canvas.drawLine(perline.x1, perline.y1, perline.x2, perline.y2, perline.paint);
                }
                //отрисовка линий уровня на поле
                float per = 0, Xper1 = 0, Yper1 = 0, Xper2 = 0, Yper2 = 0;
                while (per < lvls[number].lines * 2) {
                    for (int i = 0; i < 25; i++) {
                        for (int i2 = 0; i2 < 25; i2++) {
                            if (lvls[number].level[i][i2] == 1) {
                                for (int i3 = 0; i3 < 5; i3++) {
                                    for (int i4 = 0; i4 < 5; i4++) {
                                        if (dots[i3][i4].number == i) {
                                            Xper1 = dots[i3][i4].x;
                                            Yper1 = dots[i3][i4].y;
                                        }
                                        if (dots[i3][i4].number == i2) {
                                            Xper2 = dots[i3][i4].x;
                                            Yper2 = dots[i3][i4].y;
                                        }

                                    }
                                }
                                p.setARGB(125, 0, 0, 255);
                                canvas.drawLine(Xper1, Yper1, Xper2, Yper2, p);
                                per++;
                            }
                        }
                    }
                }
                p.setARGB(255, 0, 0, 255);
                ch = false;
                boolean open = true;
                //проверка на нажатие точки
                for (int i = 0; i < 5; i++) {
                    for (int i2 = 0; i2 < 5; i2++) {
                        if (x2 != Xch && y2 != Ych) {
                            if ((Math.abs(x2 - dots[i][i2].x) <= dots[i][i2].radius) &&
                                    (Math.abs(y2 - dots[i][i2].y) <= dots[i][i2].radius)) {
                                if (dots[i][i2].exist) {
                                    if (num == 0) {
                                        peri = i;
                                        peri2 = i2;
                                    }
                                    num++;
                                    //проверка на количество нажатий на кружки
                                    if (num == 1)
                                        perdot = dots[i][i2];
                                    if (num == 2) {
                                        num = 1;
                                        if (dots[i][i2] != perdot) {
                                            p.setColor(Color.BLUE);
                                            p.setStrokeWidth(10);
                                            //проверка на уникальность новой линии
                                            Lines new_line = new Lines(perdot.x, perdot.y, dots[i][i2].x, dots[i][i2].y, p);
                                            for (int i3 = 0; i3 < lines.size(); i3++) {
                                                Lines per_line = lines.get(i3);
                                                if (comp(per_line, new_line))
                                                    open = false;
                                            }
                                            //добавление линии на рисунок
                                            if (open) {
                                                lines.add(new_line);
                                                yourlvl[(int) dots[i][i2].number][(int) perdot.number] = 1;
                                                yourlvl[(int) perdot.number][(int) dots[i][i2].number] = 1;
                                                for (int i3=0;i3<25;i3++){
                                                    for (int i4=0;i4<25;i4++){
                                                        if(yourlvl[i3][i4]!=lvls[number].level[i3][i4])
                                                            win_check=false;
                                                    }
                                                }
                                                if (win_check)
                                                    win=true;
                                                perdot = dots[i][i2];
                                                peri = i;
                                                peri2 = i2;
                                            }
                                        }
                                    }
                                    Xch = x2;
                                    Ych = y2;
                                }
                            }
                        }
                    }
                }
                //кнопка сброса всех линий
                if (x2 < 1250 && x2 > 1000 && y2 < 150 && y2 > 50) {
                    int size = lines.size();
                    for (int i = 0; i < size; i++) {
                        lines.remove();
                        num = 0;
                        peri2 = 1000;
                        peri = 1000;
                    }
                    for(int i = 0;i<25;i++){
                        for(int i2 = 0;i2<25;i2++){
                            yourlvl[i][i2]=0;
                        }
                    }
                }
                //кнопка возврата к выбору уровня
                if (x2 < 1250 && x2 > 1000 && y2 < 600 && y2 > 500) {
                    finish();
                }
            }
            else{
                p.setColor(Color.RED);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, p);
                p.setColor(Color.WHITE);
                p.setTextSize(30);
                canvas.drawText("You won", getWidth()/2-50, getHeight() / 2, p);
                if ((Math.abs(x2 - getWidth() / 2) <= 100) &&
                        (Math.abs(y2 - getHeight() / 2) <= 100))
                    finish();
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
}
