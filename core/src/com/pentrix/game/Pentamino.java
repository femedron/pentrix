package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Array;

import java.io.Console;


public class Pentamino {

    int[][] pattern; // int[5][5];
    Array<Brick> bricks;
    final double gap,brickSize; // between bricks
    double x,y,size;  // matrix borders
    Rectangle matrixArea; //for overlap speed improving
    double x00,x01,y00,y01; // real borders (edges of bricks) with outer gaps
    GameField gameField;
    public Pentamino(int seed, double xx,double yy, double ssize, GameField gameField){
        x=xx;y=yy;size=ssize;
        matrixArea = new Rectangle((float) x,(float)y,(float)size,(float)size);
        this.gameField = gameField;
        gap = gameField.brick_gap;
        brickSize = gameField.brick_size;
        create(seed);
    }
    private void create(int seed){
        pattern = PatternGenerator.get(seed);
        bricks = new Array<>();
        for(int i = 5; i > 0; i--){
            bricks.add(new Brick(brickSize));
        }
        updateBricks();
    }
    public void rotate(){
        int[][] prevPattern = pattern;
        rotatePattern();
        updateBricks();
        if (y < gameField.y || collideWithFigures(0,0)){
            pattern = prevPattern;
        } else {
            stayInBounds(0, 0); //collide with container sideways
        }
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
        //set bricks coords
        int brickNum = 0;
        for (int i = 0; i<5;i++) {
            for(int j = 0; j<5;j++){
                int val = pattern[i][j];
                if(val == 1){
                    bricks.get(brickNum).setX((x + gap + j*(gap + brickSize)));
                    bricks.get(brickNum).setY((y + gap + (4-i)*(brickSize + gap)));
                    brickNum++;
                }
            }
        }
        //find edge coords
        x00 = y00 = Double.MAX_VALUE;
        x01 = y01 = -1;
        for(Brick brick: bricks){
            if(brick.getX() < x00){
                x00 = brick.getX();
            }
            if(brick.getY() < y00){
                y00 = brick.getY();
            }
            if(brick.getX()+brickSize > x01){
                x01 = brick.getX() + brickSize;
            }
            if(brick.getY()+brickSize > y01){
                y01 = brick.getY() + brickSize;
            }
        }
        x00-=gap; y00-=gap; //outer gaps
        x01+=gap; y01+=gap;
    }
    public void move(double dx, double dy){
        shiftFigure(dx,dy);
        updateBricks();
        stayInBounds(dx,dy);
        updateBricks();
    }

    void shiftFigure(double dx, double dy ){
        x += dx;
        y += dy;
        matrixArea.x = (float) x;
        matrixArea.y = (float) y;
    }



    void stayInBounds(double dx, double dy){
        boolean isFallable = true;
        if (x00 < gameField.x) {
            shiftFigure(gameField.x - x00, 0);
        } else if (x01 > gameField.x + gameField.width) {
            shiftFigure(gameField.x + gameField.width - x01,0);
        }
        if (y < gameField.y) {
            shiftFigure(0,gameField.y - y00);
            isFallable = false;
        }

        boolean result = !collideWithFigures(dx, dy);
        if(dx != 0 || dy != 0) {
            isFallable &= result;
        }
        gameField.setSpawnFlag(!isFallable);
    }

    /**
     * @return true if cant fall anymore
     */
    boolean collideWithFigures(double dx, double dy){
        for(Pentamino p: gameField.pentaminoes){
            if(p != this && overlaps(p)){  // ignore not involved figures
                for (Brick brick: bricks) {
                    for(Brick brickP: p.bricks){
                        if(brick.overlaps(brickP)) {
                            if(dx == 0 && dy == 0){ //ROTATE CASE
                                //NO ACTION
                                return true;
                            } else if(dy == 0){
                                shiftFigure(brickP.getX()-brick.getX()-Math.signum(dx)*(brickSize+gap),0); //brickP.getY()-brick.getY()
                                return false;
                            } else if (dx == 0) {
                                shiftFigure(brickP.getX()-brick.getX(),brickP.getY()-brick.getY()-Math.signum(dy)*(brickSize+gap));
                                return true;
                            }
//                    if (Math.abs(brick.y - brickP.y) < 5) {//todo
//                        if (brick.x < brickP.x + brickP.size + gap) {
//                            x += brickP.x + brickP.size + gap - brick.x;
//                            return true;
//                        } else if (brick.x + brick.size + gap > brickP.x) {
//                            x -= brick.x + brick.size + gap - brickP.x;
//                            return true;
//                        }
//                    }
//                    if (Math.abs(brick.x - brickP.x) < 5) {//todo
//                        if (brick.y < brickP.y + brickP.size + gap) {
//                            y += brickP.y + brickP.size + gap - brick.y;
//                            return true;
//                        }
//                    }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean overlaps(Pentamino p){
        return matrixArea.overlaps(p.matrixArea);
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
