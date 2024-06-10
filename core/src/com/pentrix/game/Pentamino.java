package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Array;


public class Pentamino {

    int[][] pattern; // int[5][5];
    Array<Brick> bricks;
    final double gap,brickSize; // between bricks
    double x,y,size;  // matrix borders
    double x00,x01,y00,y01; // real borders (edges of bricks)
    GameField gameField;
    public Pentamino(int seed, double xx,double yy, double ssize, GameField gameField){
        x=xx;y=yy;size=ssize;
        this.gameField = gameField;
        gap = gameField.BRICK_GAP;
        brickSize = gameField.BRICK_SIZE;
        create(seed);
    }
    private void create(int seed){
        pattern = PatternGenerator.get(seed);
        bricks = new Array<>();
        for(int i = 5; i > 0; i--){
            bricks.add(new Brick(brickSize, gap));
        }
        updateBricks();
    }
    public void rotate(){
        rotatePattern();
        updateBricks();
        stayInBounds();
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
        //set bricks coords
        int brickNum = 0;
        for (int i = 0; i<5;i++) {
            for(int j = 0; j<5;j++){
                int val = pattern[i][j];
                if(val == 1){
                    bricks.get(brickNum).x = (float) (x + gap + j*(gap + brickSize));
                    bricks.get(brickNum).y = (float) (y + gap + (4-i)*(brickSize + gap));
                    brickNum++;
                }
            }
        }
        //find edge coords
        x00 = y00 = Double.MAX_VALUE;
        x01 = y01 = -1;
        for(Brick brick: bricks){
            if(brick.x < x00){
                x00 = brick.x;
            }
            if(brick.y < y00){
                y00 = brick.y;
            }
            if(brick.x+brickSize > x01){
                x01 = brick.x + brickSize;
            }
            if(brick.y+brickSize > y01){
                y01 = brick.y + brickSize;
            }
        }
    }
    public void move(double dx, double dy){
        x += dx;
        y += dy;
        updateBricks();
        stayInBounds();
    }

    void stayInBounds(){
        boolean isFallable = true;
        if (x00 < gameField.x) {
            x += gameField.x - x00;
        } else if (x01 > gameField.x + gameField.width) {
            x += gameField.x + gameField.width - x01;
        }
        if (y < gameField.y) {
            y += gameField.y - y00;
            isFallable = false;
        }//todo check collisions
        for(Pentamino p: gameField.pentaminoes){
//            if(this.overlaps(p)){ //todo
//                if(y00 < p.y01){
//                    y += p.y01-y00;
//                    isFallable = false;
//                }
//            }
        }
        gameField.setSpawnFlag(!isFallable);
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
