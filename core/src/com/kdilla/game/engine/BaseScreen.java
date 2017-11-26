package com.kdilla.game.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.kdilla.game.level.map.LevelMap;

/**
 * Created by avetc on 25.11.2017.
 */

public class BaseScreen implements Screen, InputProcessor {
    protected SpriteBatch batch;
    protected final Game game;
    protected OrthographicCamera cam;



    public BaseScreen(Game game ) {
        this.game = game;

    }

    @Override
    public void show() {
        System.out.println("show");
        Gdx.input.setInputProcessor(this);
        if (batch != null)
            throw new RuntimeException("batch != null, повторная установка screen без dispose");
        batch = new SpriteBatch();
     //   cam = new OrthographicCamera();

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    protected void touchDown(Vector2 touch, int pointer) {
    }
    protected void touchUp(Vector2 touch, int pointer) {
    }
    protected void touchMove(Vector2 touch, int pointer) {
    }
    private final Vector2 touch = new Vector2();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //touch.set(screenX, cam. - 1f - screenY);
        System.out.println("touch x="+ screenX+", y="+screenY);
        touchDown(touch, pointer);
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
