package com.kdilla.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kdilla.game.engine.utils.RectContainer;
import com.kdilla.game.level.map.LevelMap;
import com.kdilla.game.utils.Force;

import java.util.ArrayList;

/**
 * Created by avetc on 23.11.2017.
 */

//public class StairsEntity extends Entity {
public class StairsEntity extends InteractableEntity {

    public ArrayList<StairsEntity> getStairs() {
        return stairs;
    }

    ArrayList<StairsEntity> stairs;
    Player player;
    float top;
    boolean isReadyToInterract;


    RectContainer stairsContainer;
    public Texture getImage() {
        return image;
    }

    Texture image;
    int count;

    public StairsEntity(float x, float y, LevelMap map, int count, Player player, TextureAtlas atlas) {
        super(x, y, EntityType.STAIRS, map, player, atlas, EntityType.STAIRS.getId());
        this.player = player;

//        image = new Texture("entities/stairs.png");
        stairs = new ArrayList<StairsEntity>();
        stairs.add(this);
        if (count > 1) {
            for (int i = 1; i < count; i++) {
                stairs.add(new StairsEntity(x, y + (this.getHeight() * i), map, 0, player, atlas));
            }
        }
        stairsContainer = new RectContainer(x,y,this.getWidth(), this.getHeight() * count);
    }

    @Override
    public void render(SpriteBatch batch) {
        for (StairsEntity stair : stairs) {
            batch.draw(stair.regions[frame], stair.getPos().x, stair.getPos().y, stair.getWidth(), stair.getHeight());
        }

    }
//
//    @Override
//    float getTop() {
//        StairsEntity stair = stairs.get(stairs.size()-1);
//        System.out.println(stairs.get(stairs.size()-1).getTop());
//        return stairs.get(stairs.size()-1).getTop();
//    }

    @Override
    public void update(float deltaTime, float gravity) {
        int a = 0;
        for (StairsEntity stair : stairs) {
            super.update(deltaTime, gravity);

            if (!isReadyToInterract) {
                if (isCollided(player, stair)) {
                    frame = 0;
                    isReadyToInterract = true;
                } else {
                    isReadyToInterract = false;
                    frame = 1;
                }
            }
            if (isReadyToInterract) {
                if (isCollided(player, stair)) {
                    frame = 0;
                    isReadyToInterract = true;
                } else {
                    isReadyToInterract = false;
                    frame = 1;
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                if (isReadyToInterract) {
                    if (stairsContainer.getHeight() > player.getBottom()) {
                        System.out.println(stairs.get(stairs.size() - 1).getTop());
                        System.out.println("container h="+stairsContainer.getHeight());
                      //  alignVertical(player, stairs.get(0));
                        interract(deltaTime);
                    }else isReadyToInterract = false;
                }
            }

            if (Gdx.input.justTouched()) {
//               w

//            if (type != null)
//                System.out.println("You clicked " + type.getId() + " " + type.getName());

            }

        }
    }

    @Override
    void checkInterract() {

    }

    @Override
    void interract(float deltaTime) {
        player.moveY(120 * deltaTime);
    }

}
