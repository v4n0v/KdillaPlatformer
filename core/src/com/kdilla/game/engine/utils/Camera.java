package com.kdilla.game.engine.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kdilla.game.entity.Entity;
import com.kdilla.game.level.map.LevelMap;


public class Camera extends OrthographicCamera {


    public float getHalfHeight() {
        return h/2;
    }

    public float getHalfWidth() {
        return w/2;
    }

    public void initLevelMap(LevelMap levelMap) {
        this.levelMap = levelMap;
    }

    private LevelMap levelMap;
    private final float h;
    private final float w;

    public Camera(float w, float h) {
        this.setToOrtho(false, w, h);
        this.w = w;
        this.h=h;

    }


    public float getLeft(){
        return this.position.x -getHalfWidth();
    }
    public float getRight(){
        return this.position.x+getHalfWidth();
    }
    public float getTop(){
        return this.position.y+getHalfHeight();
    }
    public float getBottom(){
        return this.position.y-getHalfHeight();
    }

    public void setLeft(float newX){
        this.position.x=newX+getHalfWidth();
    }

    public void  follow(Entity entity){
        Vector2 pos = entity.getPos();
        Vector3 newPos = new Vector3();
        newPos.x=pos.x;
        newPos.y=pos.y;
        newPos.z=0;
        this.position.set(newPos);
    }

    @Override
    public void update() {
//        if (this.getLeft()<=levelMap.getLeftPixel()) {
//            this.setLeft(0);
//            this.position.y=levelMap.getPlayer().getY()+80;
//        }else
//            this.position.set(levelMap.getPlayer().getX(), levelMap.getPlayer().getY() + 80, 0);
        super.update();
    }

    public void setRight(int newX) {
        this.position.x=newX-getHalfWidth();
    }
}
