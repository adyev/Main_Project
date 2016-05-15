package com.example.circles;

/**
 * Created by Например_Андрей on 13.05.2016.
 */
public class Lvl {
    int level[][] = new int[25][25];
    float lines;
    Lvl(int level[][], int lines){
        for(int i=0;i<25;i++){
            for(int i2=0;i2<25;i2++){
                this.level[i][i2] = level[i][i2];
            }
        }
        this.lines = lines;
    }
}
