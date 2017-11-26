package com.kdilla.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.kdilla.game.level.map.LevelMap;

import java.util.ArrayList;

public class Player extends Entity {

    private static final int SPEED = 180;
    private static final int JUMP_VELOCITY = 5;
    Texture image;

    boolean isInteracting;

    ArrayList<StairsEntity> stairsObject;
    private StairsEntity topStair;

    public Player(float x, float y, LevelMap map, TextureAtlas atlas) {
        super(x, y, EntityType.PLAYER, map, false, atlas, "player");

    }


    @Override
    public void render(SpriteBatch batch) {
        batch.draw(region, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public void update(float deltaTime, float gravity) {
        if (Gdx.input.isKeyPressed(Keys.SPACE) && isGrounded) {
            System.out.println("space and grounded");
            this.velocityY += JUMP_VELOCITY * getWeight();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && !isGrounded && this.velocityY > 0) {
            this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
            System.out.println("space and !grounded");
        }


        super.update(deltaTime, gravity);
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            moveX(-SPEED * deltaTime);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            moveX(SPEED * deltaTime);

    }

    private void setPos(Vector2 pos) {
        this.pos = pos;
    }

    void moveY(float amount) {
        pos.y += amount;

    }

}
