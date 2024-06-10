package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.pentrix.game.screens.GameScreen;


public class GameField extends Container{

    boolean spawnFlag, rotateFlag, fallFlag;
    MoveOption moveOption;
    long lastMoveTime, lastFallMoveTime;
    final long fallTimeGap, baseTimeGap;
    final double PENTAMINO_SIZE = (5*(9+1)+1) * 2;
    final double BRICK_GAP = 2;
    final double BRICK_SIZE = (PENTAMINO_SIZE-6*BRICK_GAP)/5;
    final double PENTAMINO_MOVE_DISTANCE = BRICK_SIZE+BRICK_GAP; //todo gamescreen height must be multiple of brick with gaps (for smooth fall on ground)

    Array<Pentamino> pentaminoes;
    Pentamino activePentamino;

    public GameField(int x, int y, int w, int h, GameScreen gameScreen) {
        super(x, y, w, h);
        baseTimeGap = gameScreen.BASE_TIME_GAP;
        fallTimeGap = baseTimeGap * 5;
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
        Pentamino p = new Pentamino(seed,x + width /2 - PENTAMINO_SIZE/2,y + height - PENTAMINO_SIZE,PENTAMINO_SIZE, this);
        pentaminoes.add(p);
        activePentamino = p;
    }

    void update(){
        long curTime = TimeUtils.nanoTime();
        if(spawnFlag){
            addPentamino();
            spawnFlag = false;
        }
        if(rotateFlag){
            activePentamino.rotate();
            rotateFlag = false;
        }
        if(fallFlag && curTime - lastFallMoveTime > fallTimeGap) {
            activePentamino.move(0, -PENTAMINO_MOVE_DISTANCE);
            lastFallMoveTime = TimeUtils.nanoTime();
        }
        boolean moved = false;
        switch (moveOption){
            case None: break;
            case Down:
                if(curTime - lastMoveTime > baseTimeGap) {
                    activePentamino.move(0, -PENTAMINO_MOVE_DISTANCE);
                    moved = true;
                }
                break;
            case Right:
                if(curTime - lastMoveTime > baseTimeGap){
                    activePentamino.move(PENTAMINO_MOVE_DISTANCE, 0);
                    moved = true;
                }
                break;
            case Left:
                if(curTime - lastMoveTime > baseTimeGap) {
                    activePentamino.move(-PENTAMINO_MOVE_DISTANCE, 0);
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
