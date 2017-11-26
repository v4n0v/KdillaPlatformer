package com.kdilla.game.engine.utils;

import com.badlogic.gdx.math.Vector2;
import com.kdilla.game.engine.math.Rect;

/**
 * Created by avetc on 25.11.2017.
 */

public class RectContainer {
    protected float angle;
    protected float scale = 1f;
    protected Vector2 pos = new Vector2();

    public int getHalfWidth() {
        return halfWidth;
    }

    public int getHalfHeight() {
        return halfHeight;
    }

    protected int halfWidth;
    protected int halfHeight;

    public RectContainer(RectContainer from) {
        this(from.pos.x, from.pos.y, from.getHalfWidth(), from.getHalfHeight());
    }

    public RectContainer(float x, float y, int halfWidth, int halfHeight) {
        pos.set(x, y);
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }
    public float getLeft(){
        return pos.x+getHalfHeight();

    }
    public float getY() {
        return pos.y;
    }
    public int getWidth() {
        return halfWidth*2;
    }

    public int getHeight() {
        return halfHeight*2;
    }
    public float getRight(){
        return pos.x+getWidth();
    }

    public float getTop(){
        return pos.y+getHeight();

    }
    public float getBottom(){
        return pos.y;

    }
}
