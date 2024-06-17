package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.pentrix.game.parameters.GameParameters;
import com.pentrix.game.screens.GameScreen;

public class GameField extends Container{
    public final double x,y,width,height;
    public final double pentamino_size, brick_size, pentamino_move_distance;
    final int bricks_count_x,bricks_count_y, brick_gap, lineReward;
    long fallTimeGap, baseTimeGap;
    boolean spawnFlag, rotateFlag, fallFlag;
    MoveOption moveOption;
    long lastMoveTime, lastFallMoveTime;
    Array<Pentamino> pentaminoes;
    Pentamino activePentamino;
    Brick[][] brickMap; //fixed bricks; used for line clearing
    GameParameters gameParameters;
    GameScreen gameScreen;
    FigureContainer figureContainer;
    public int score, lines;

    public GameField(double x, double y, double w, double h, GameParameters gp, GameScreen gs) {
        super(x, y, w, h);
        this.x = x;this.y = y; width = w;height = h;
        gameScreen = gs;
        bricks_count_x = gp.brickCountX;
        bricks_count_y = gp.brickCountY;
        brick_gap = gp.brickGap;
        baseTimeGap = gp.baseTimeGap;
        fallTimeGap = gp.fallTimeGap;
        lineReward = gp.lineReward;

        brick_size = (w-(bricks_count_x+1)*brick_gap)/bricks_count_x;
        pentamino_move_distance = brick_size+brick_gap;
        pentamino_size = 6*brick_gap+5*brick_size;

        lastMoveTime = lastFallMoveTime = -1;
        pentaminoes = new Array<>();
        moveOption = MoveOption.None;
        setSpawnFlag(true);
        setFallFlag(true);

        brickMap = new Brick[bricks_count_y][bricks_count_x];
        lines = score = 0;
    }
    public void setFigureContainer(FigureContainer fc){
        figureContainer = fc;
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

    public boolean addPentamino(Pentamino p){
        p.putFigure(x + (bricks_count_x/2 - 2)*pentamino_move_distance,
            y + height - ((p.y01+brick_gap)-p.y));
        if (p.collideWithFigures(0,0)){
            return false;
        }
        p.gameField = this;
        pentaminoes.add(p);
        activePentamino = p;
        return true;
    }

    private int clearLines(){
        updateBrickMap();
        int linesStreak = 0;
        for(int i = 0; i<26; i++) {//todo
            if(linesStreak == 5)
                break;
            else if(isLineFilled(i)) {
                for (int j = 0; j < bricks_count_x; j++) {
                    Brick brick = brickMap[i][j];
                    brick.remove();
                }
                linesStreak++;
            }
        }
        return linesStreak;
    }

    private boolean isLineFilled(int order){
        for(int i = 0; i< bricks_count_x; i++){
            if(brickMap[order][i] == null)
                return false;
        }
        return true;
    }

    public void updateBrickMap(){
        brickMap = new Brick[26][(int) bricks_count_x];
        for(Pentamino p: pentaminoes) {
            for(Brick brick: p.bricks) {
                if (brick != null) {
                    Point point = brick.calcMatrixPoint();
                    brickMap[point.y][point.x] = brick;
                }
            }
        }
    }
    private void fallBricks(){
        updateBrickMap();
        for(int i = 0; i<26; i++){
            if(brickMap[i] == null || isBrickMapRowEmpty(i)){
                for(int j = i+1; j<26; j++){
                    if(brickMap[j] != null && !isBrickMapRowEmpty(j)) {
                        brickMap[i] = brickMap[j];
                        brickMap[j] = null;
                        for (int n = 0; n < bricks_count_x; n++)
                            if(brickMap[i][n] != null)
                                brickMap[i][n].setCoords(n, i);
                        break;
                    }
                }
            }
        }
    }
    private boolean isBrickMapRowEmpty(int row){
        for (Brick el: brickMap[row]) {
            if(el != null)
                return false;
        }
        return true;
    }

    private void addScore(int streak){
        score += (int) (Math.pow(2,streak-1)*streak*lineReward);
    }

    private void handleFilledLines(){
        int streak = clearLines();
        if(streak > 0) {
            gameScreen.playLineClearSound();
            lines += streak;
            addScore(streak);
            fallBricks();
        }
    }

    void update(){
        long curTime = TimeUtils.nanoTime();
        if(spawnFlag){
            if(pentaminoes.size > 0){
                gameScreen.playFallSound();
            }
            handleFilledLines();
            if(!addPentamino(figureContainer.releaseAndUpdate())){
                gameScreen.gameOver();
            }
            spawnFlag = false;
        }
        if(rotateFlag){
            activePentamino.rotate(false);
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
                moveOption = MoveOption.None; // remove holding of key
                break;
            case Left:
                if(curTime - lastMoveTime > baseTimeGap) {
                    activePentamino.move(-pentamino_move_distance, 0);
                    moved = true;
                }
                moveOption = MoveOption.None; // remove holding of key
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
