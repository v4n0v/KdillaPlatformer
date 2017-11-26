package com.kdilla.game.engine.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kdilla.game.engine.math.Rect;
import com.kdilla.game.utils.Regions;


/**
 * Created by avetc on 11.09.2017.
 */

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;
    static final int VERTEX_SIZE = 2 + 1 + 2;
    static final int SPRITE_SIZE = 4 * VERTEX_SIZE;

    private boolean isDestroyed;
    final float[] vertices = new float[SPRITE_SIZE];

    public Sprite() {
    }

    public Sprite(TextureRegion region) {
        if(region == null) throw new RuntimeException("Create Sprite with null region");
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        regions = Regions.split(region, rows, cols, frames);
    }

    public void resize(Rect worldBounds) {
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchMove(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public void update(float deltaTime) {

    }

    public void setOpacity(){

    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale, angle
        );
    }

    public void setWidthProportion(float width) {
        setWidth(width);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setHeight(width / aspect);
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getAngle() {
        return angle;
    }

    public float getScale() {
        return scale;
    }

    public void destroy() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void flushDestroy() {
        isDestroyed = false;
    }

    public void flipHorizontal( ){
        regions[frame].flip(true, false);
    }

    public void flipVertical( ){
        regions[frame].flip(false, true);
    }

//    public void setAlpha (float a) {
//        int intBits = NumberUtils.floatToIntColor(regions[frame].getU());
//        int alphaBits = (int)(255 * a) << 24;
//
//        // clear alpha on original color
//        intBits = intBits & 0x00FFFFFF;
//        // write new alpha
//        intBits = intBits | alphaBits;
//        float color = NumberUtils.intToFloatColor(intBits);
//        regions[frame].setU(color);
//        regions[frame].setV(color);
//        regions[frame].setU2(color);
//        regions[frame].setV2(color);
//
//      //  vertices[C4] = color;
//    }

    @Override
    public String toString() {
        return "Sprite: " + " angle = " + angle + " scale = " + scale + " " + super.toString();
    }
}

