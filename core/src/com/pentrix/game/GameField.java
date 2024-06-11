package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.pentrix.game.screens.GameScreen;

public class GameField extends Container{
    final double x,y,width,height;
    final long fallTimeGap, baseTimeGap;
    final double bricks_count_x = 13;
    final double brick_gap = 2;
    final double pentamino_size, brick_size, pentamino_move_distance;
    boolean spawnFlag, rotateFlag, fallFlag;
    MoveOption moveOption;
    long lastMoveTime, lastFallMoveTime;

    Array<Pentamino> pentaminoes;
    Pentamino activePentamino;

    public GameField(double x, double y, double w, double h, long baseTimeGap) {
        super(x, y, w, h);
        this.x = x;this.y = y; width = w;height = h;
        this.baseTimeGap = baseTimeGap;
        fallTimeGap = baseTimeGap * 7;  //todo
        brick_size = (w-(bricks_count_x+1)*brick_gap)/bricks_count_x;
        pentamino_move_distance = brick_size+brick_gap;
        pentamino_size = 6*brick_gap+5*brick_size;

        lastMoveTime = lastFallMoveTime = -1;
        pentaminoes = new Array<>();
        moveOption = MoveOption.None;
        setSpawnFlag(true);
        setFallFlag(true);
    }

    public void setSpawnFlag(boolean v){
        spawnFlag = v;
    }
    public void setFallFlag(boolean v){
        fallFlag = v;
    }
    public void setRotateFlag(boolean v){
        rotateFlag = v;
    }
    public void setMoveOption(MoveOption moveOption){
        this.moveOption = moveOption;
    }

    public void addPentamino(){
        addPentamino(MathUtils.random(1,18));
    }
    public void addPentamino(int seed){
        Pentamino p = new Pentamino(seed,x + ((bricks_count_x-5)/2)*pentamino_move_distance,
                y +(22)*pentamino_move_distance,
                pentamino_size,
                this);
        pentaminoes.add(p);
        activePentamino = p;
    }

    void update(){
        long curTime = TimeUtils.nanoTime();
        if(spawnFlag){
            addPentamino(); //and deactivate previous pentamino
            spawnFlag = false;
        }
        if(rotateFlag){
            activePentamino.rotate();
            rotateFlag = false;
        }
        if(fallFlag && curTime - lastFallMoveTime > fallTimeGap) {
            activePentamino.move(0, -pentamino_move_distance);
            lastFallMoveTime = TimeUtils.nanoTime();
        }
        boolean moved = false;
        switch (moveOption){
            case None: break;
            case Down:
                if(curTime - lastMoveTime > baseTimeGap) {
                    activePentamino.move(0, -pentamino_move_distance);
                    moved = true;
                }
                break;
            case Right:
                if(curTime - lastMoveTime > baseTimeGap){
                    activePentamino.move(pentamino_move_distance, 0);
                    moved = true;
                }
                break;
            case Left:
                if(curTime - lastMoveTime > baseTimeGap) {
                    activePentamino.move(-pentamino_move_distance, 0);
                    moved = true;
                }
                break;
        }
        if(moved)
            lastMoveTime = curTime;
    }


    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        update();
        for (Pentamino p: pentaminoes) {
            p.render(batch);
        }
    }
}
