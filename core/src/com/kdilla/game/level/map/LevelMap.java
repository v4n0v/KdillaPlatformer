package com.kdilla.game.level.map;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kdilla.game.entity.BoxEntity;
import com.kdilla.game.entity.Entity;
import com.kdilla.game.entity.Player;
import com.kdilla.game.entity.StairsEntity;

import java.util.ArrayList;

public abstract class LevelMap {
    TextureAtlas atlas = new TextureAtlas("entities/entity.pack");

    public void setGRAVITY(float GRAVITY) {
        this.GRAVITY = GRAVITY;
    }

    float GRAVITY = -9.7f;

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    protected ArrayList<Entity> entities;

    public Player getPlayer() {
        return player;
    }

    private Player player;

    private OrthographicCamera camera;

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public LevelMap() {
        entities = new ArrayList<Entity>();
        player = new Player(140, 158, this, atlas);
        entities.add(new BoxEntity(750, 108, this, player, atlas));
        entities.add(new StairsEntity(360, 108, this, 6, player, atlas));
        entities.add(new BoxEntity(60, 108, this, player, atlas));
        entities.add(player);
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public void update(float delta) {
        for (Entity entity : entities) {
            entity.update(delta, GRAVITY);
//            entity.update(delta, 0f);
        }
    }

    public abstract void dispose();


    /**
     * берем позицию тайла
     */
    public TileType getTileByLocation(int layer, float x, float y) {
        return this.getTileByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

    /**
     * берем тайл из координаты
     */
    public abstract TileType getTileByCoordinate(int layer, int x, int y);

    public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
        if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight())
            return true;

        for (int row = (int) y / TileType.TILE_SIZE; row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
            for (int coll = (int) x / TileType.TILE_SIZE; coll < Math.ceil((x + width) / TileType.TILE_SIZE); coll++) {
                for (int layer = 0; layer < getLayers(); layer++) {
                    TileType type = getTileByCoordinate(layer, coll, row);
                    if (type != null && type.isCollidable())
                        return true;
                }
            }
        }
        return false;
    }

    public boolean doesRectCollideWithMapVertical(float x, float y, int width, int height) {
        if (x < 0 || x + width > getPixelWidth())
            return true;
        for (int row = (int) y / TileType.TILE_SIZE; row < Math.ceil(y + height) / TileType.TILE_SIZE; row++)
            for (int coll = (int) x / TileType.TILE_SIZE; coll < Math.ceil((x + width) / TileType.TILE_SIZE); coll++) {
                double a = Math.ceil(x + height);
                for (int layer = 0; layer < getLayers(); layer++) {
                    TileType type = getTileByCoordinate(layer, coll, row);
                    if (type != null && type.isCollidable()) {
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean doesRectCollideWithRect(float x, float y, Entity entity) {
        if (entity.getType().isCollidable()) {
            if (player.getLeft() <= entity.getRight() && player.getRight() > entity.getRight() ||
                    player.getRight() >= entity.getLeft() && player.getLeft() < entity.getLeft() ||

                    player.getBottom() <= entity.getTop() && player.getTop() > entity.getTop() ||
                    player.getTop() >= entity.getBottom() && player.getBottom() < entity.getBottom()
                    )
                return true;

        }
        return false;
    }

    public Entity getCollideWithRect(float x, float y) {
        for (Entity entity : entities) {
           if (entity.getEntityByCoordinate(x, y)!=null){
               if (entity.getType().isCollidable())
                   return entity;
           }
        }
        return null;
    }

    public int getPixelWidth() {
        return this.getWidth() * TileType.TILE_SIZE;
    }

    public int getPixelHeight() {
        return this.getHeight() * TileType.TILE_SIZE;
    }

    public int getTopPixel() {
        return getPixelHeight();
    }

    public int getBottomPixel() {
        return 0;
    }

    public int getLeftPixel() {
        return 0;
    }

    public int getRightPixel() {
        return getPixelWidth();
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getLayers();
}
