package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Pentamino {

    int[][] pattern; // int[5][5];
    Array<Brick> bricks;
    final double gap,brickSize; // between bricks
    double x,y,size;  // matrix borders

    boolean rotateable;
    public Pentamino(int seed, double xx,double yy, double ssize, GameField gameField){
        x=xx;y=yy;size=ssize;
        gap = gameField.BRICK_GAP;
        brickSize = gameField.BRICK_SIZE;
        create(seed);
    }
    private void create(int seed){
        rotateable = true;
        pattern = PatternGenerator.get(seed);
        bricks = new Array<>();
        for(int i = 5; i > 0; i--){
            bricks.add(new Brick(brickSize));
        }
        updateBricks();
    }
    public void rotate(){
        rotatePattern();
        updateBricks();
    }
    private void rotatePattern(){
        final int M = pattern.length;
        final int N = pattern[0].length;
        int[][] ret = new int[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M-1-r] = pattern[r][c];
            }
        }
        pattern = ret;
    }
    private void updateBricks(){
        int brickNum = 0;
        for (int i = 0; i<5;i++) {
            //int[] row = ;
            for(int j = 0; j<5;j++){
                int val = pattern[i][j];
                if(val == 1){
                    bricks.get(brickNum).x = x + gap + j*brickSize;
                    bricks.get(brickNum).y = y + gap + (4-i)*brickSize;
                    brickNum++;
                }
            }
        }
    }
    public void move(double dx, double dy){
        x += dx;
        y += dy;
        updateBricks();
    }
    public void eraseParts(int lines){
        //remove bricks
    }
    public void render(SpriteBatch batch){
        updateBricks();
        for (Brick brick: bricks) {
            brick.render(batch);
        }
    }
}
