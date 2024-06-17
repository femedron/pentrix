package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Array;

import java.io.Console;


public class Pentamino {

    int seed;
    int[][] pattern; // int[5][5] or [4][4]
    Array<Brick> bricks;
    final double gap,brickSize; // between bricks
    double x,y,size;  // matrix borders
    Rectangle matrixArea; //for overlap speed improving
    double x00,x01,y00,y01; // real borders (edges of bricks) with outer gaps
    GameField gameField;
    boolean fixed;
    public final int brickCount;
    public Pentamino(int seed, double xx,double yy, int brickCount, GameField gameField){
        x=xx;y=yy;
        this.seed = seed;
        this.brickCount = brickCount;
        this.gameField = gameField;
        gap = gameField.brick_gap;
        brickSize = gameField.brick_size;
        size = gap + 5*(gap+brickSize);
        matrixArea = new Rectangle((float) x,(float)y,(float)size,(float)size);
        fixed = false;
        create();
    }
    public void updateBricks(){
        //set bricks coords
        int brickNum = 0;
        for (int i = 0; i < brickCount; i++) {
            for (int j = 0; j < brickCount; j++) {
                if(brickNum == brickCount){
                    break;
                }
                int val = pattern[i][j];
                if (val == 1) {
                    Brick brick = bricks.get(brickNum);
                    brick.setX((x + gap + j * (gap + brickSize)));
                    brick.setY((y + gap + (brickCount-1 - i) * (brickSize + gap))); //todo debug
                    brickNum++;
                }
            }
        }
        updateEdges();
    }
    private void updateEdges(){
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
    public void rotate(boolean force){
        if(force){
            rotatePattern();
            updateBricks();
            return;
        }
        int[][] ppattern = pattern;
        double px = x,py = y;       //save
        rotatePattern();
        updateBricks();
        collide(0, 0);  //ignore figure collisions until get collided with container
        updateBricks();
        if (collideWithFigures(0,0)){  //Rollback (todo_ normal handling?)
            pattern = ppattern;
            putFigure(px,py);
            updateBricks();
        }
        tryFixPosition();
        tryCollectBonus();
    }
    void tryCollectBonus(){
        if(gameField.bonus != null && gameField.bonus.overlaps(this)){
            gameField.bonus.collect();
        }
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

    public void move(double dx, double dy){
        shiftFigure(dx,dy);
        updateBricks();
        collide(dx,dy);
        updateBricks();
        tryFixPosition();
        tryCollectBonus();
    }
    void shiftFigure(double dx, double dy ){
        x += dx;
        y += dy;
        matrixArea.x = (float) x;
        matrixArea.y = (float) y;
        updateBricks();
    }

    void putFigure(double xx, double yy){
        x = xx;
        y = yy;
        matrixArea.x = (float) x;
        matrixArea.y = (float) y;
        updateBricks();
    }
    void collide(double dx, double dy){
        boolean isFallable = true;
        if (x00 < gameField.x) {
            shiftFigure(gameField.x - x00, 0);
        } else if (x01 > gameField.x + gameField.width) {
            shiftFigure(gameField.x + gameField.width - x01,0);
        }
        if (y00 < gameField.y) {
            shiftFigure(0,gameField.y - y00);
            isFallable = false;
        }
//        if(collide(gameField) && y00 == gameField.y+gap)
//            isFallable = false;

        isFallable &= !collideWithFigures(dx, dy);
        if((dx != 0 || dy != 0)) // NOT ROTATE
            fixed = !isFallable;
    }

    void tryFixPosition(){
        if(fixed) {
            gameField.setSpawnFlag(true);//todo
        }
    }


    public void removeBrick(Brick brick){
        bricks.removeValue(brick, true);
    }
    boolean collideWithFigures(double dx, double dy){
        for(Pentamino p: gameField.pentaminoes){
            if(p != this){  // ignore not involved figures
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
    private void create(){
        pattern = PatternGenerator.get(seed, brickCount);
        bricks = new Array<>();
        for(int i = brickCount; i > 0; i--){
            bricks.add(new Brick(this));
        }
        updateBricks();
    }
    public void render(SpriteBatch batch){
        for (Brick brick: bricks) {
            brick.render(batch);
        }
    }
}
