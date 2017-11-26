package com.kdilla.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kdilla.game.level.map.LevelMap;
import com.kdilla.game.utils.Regions;

/**
 * Created by avetc on 24.11.2017.
 */

public abstract class InteractableEntity extends Entity {
    Player player;
    TextureAtlas atlas;
    TextureRegion region;
    TextureRegion[] regions;
    boolean isReadyToInterract;
    int frame = 1;

    public InteractableEntity(float x, float y, EntityType type, LevelMap map, Player player, TextureAtlas atlas, String regionName) {
        super(x, y, type, map, true, atlas, regionName);
        this.player = player;
        this.atlas = new TextureAtlas("entities/entity.pack");
        this.region = atlas.findRegion(regionName);
        this.regions=new TextureRegion[2];
        this.regions= Regions.split(region, 1, 2, 2);
    }

    @Override
    public void render(SpriteBatch batch) {
//        batch.draw(regions[frame], pos.x, pos.y, getWidth(), getHeight());
        batch.draw(
                regions[frame],
                getLeft(), getBottom(),
                getWidth()/2, getHeight()/2,
                getWidth(), getHeight(),
                scale, scale, angle
        );
    }

    @Override
    public void update(float deltaTime, float gravity) {


        super.update(deltaTime, gravity);

    }

    abstract void interract(float deltaTime);


    void checkInterract() {
        if (!this.isReadyToInterract) {
            if (isCollided(this.player, this,5)) {
                this.isReadyToInterract = true;

            } else this.isReadyToInterract = false;
        }
        if (this.isReadyToInterract) {
            if (isCollided(this.player, this,5)) {
                this.isReadyToInterract = true;

            } else this.isReadyToInterract = false;
        }
    }
}
