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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Feild(this));
    }
    public class Feild extends View {

        Lvldot lvldot[][] = new Lvldot[5][5];
        float x = 100, y = 100, x2, y2, Xch, Ych;
        boolean open = true;
        Context ctx;
        Paint p = new Paint();
        float num=0;
        public Feild(Context context){
            super(context);
            ctx = context;
        }
        @Override
        protected void onDraw(Canvas canvas){
            for (int i = 0; i < 5; i++) {
                for (int i2 = 0; i2 < 5; i2++) {
                    p.setColor(Color.RED);
                    if (open) {
                        lvldot[i][i2] = new Lvldot(x + 10, y - 5, 25, p, num);
                        num++;
                    }
                    lvldot[i][i2].draw_circle(canvas);
                    x+=125;
                }
                x=100;
                y+=125;
            }
            open = false;
            for (int i=0;i<5;i++) {
                for (int i2 = 0; i2 < 5; i2++) {
                    if (x2 != Xch && y2 != Ych) {
                        if ((Math.abs(x2 - lvldot[i][i2].x) <= lvldot[i][i2].radius) &&
                                (Math.abs(y2 - lvldot[i][i2].y) <= lvldot[i][i2].radius)) {
                            Intent intent = new Intent(ctx, Main2Activity.class);
                            startActivity(intent);
                            intent.putExtra("number_of_levels", lvldot[i][i2].lvlnum);
                            Xch = x2;
                            Ych = y2;
                        }
                    }
                }
            }
        }
        @Override
        public boolean onTouchEvent(MotionEvent event){
            x2 = event.getX();
            y2 = event.getY();


            invalidate();
            return true;
        }
    }
}
