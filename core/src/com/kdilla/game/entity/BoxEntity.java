package com.kdilla.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kdilla.game.level.map.LevelMap;
import com.kdilla.game.level.map.TileType;
import com.kdilla.game.utils.Regions;

/**
 * Created by avetc on 23.11.2017.
 */

public class BoxEntity extends InteractableEntity {
    //    Texture image;
//   TextureAtlas atlas;
//    TextureRegion region;
//    TextureRegion[] regions;
//    int frame = 1;
    enum Moving {
        LEFT, RIGHT, INTERRACT, FREE
    }

    ;
    boolean isInteracting;
    Moving moving;

    public BoxEntity(float x, float y, LevelMap map, Player player, TextureAtlas atlas) {
        super(x, y, EntityType.BOX, map, player, atlas, EntityType.BOX.getId());
//        atlas = new TextureAtlas("entities/entity.pack");
//        region = atlas.findRegion("box");
//        regions=new TextureRegion[2];
        regions = Regions.split(region, 1, 2, 2);

        //  image = new Texture("entities/box.png");


    }

//
//    @Override
//    public void render(SpriteBatch batch) {
//       // batch.draw(regions[frame], pos.x, pos.y, getWidth(), getHeight());
//
//    }

    @Override
    public void update(float deltaTime, float gravity) {
        super.update(deltaTime, gravity);

        checkInterract();

        if (isReadyToInterract) {
            frame = 0;
            float newX = pos.x;

            // отрабатываем движение вправо
            if (player.getLeft() <= getLeft() && player.getBottom() == getBottom()) {
                if (moving != Moving.INTERRACT)
                    if (!doesBoxCollideHorizontal(newX, pos.y, getWidth(), getHeight(), map)) {
                        moving = Moving.RIGHT;
                        newX = player.getX() + player.getWidth();
                    }
            } else if (player.getLeft() >= getLeft() && player.getBottom() == getBottom()) {
                if (moving != Moving.INTERRACT)
                    if (!doesBoxCollideHorizontal(newX, pos.y, getWidth(), getHeight(), map)) {
                        moving = Moving.LEFT;
                        newX = player.getX() - getWidth();
                    }
            }

            // чтобы не тянуть за собой, а только перед собой
            if (pos.x < newX && moving == Moving.RIGHT)
                // if (moving != Moving.INTERRACT)
                pos.x = newX;
            else if (pos.x > newX && moving == Moving.LEFT)
                //     if (moving != Moving.INTERRACT)
                pos.x = newX;
        } else frame = 1;

        if (Gdx.input.justTouched()) {
//            Vector3 pos = map.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
//            Vector2 pos2 = new Vector2(pos.x, pos.y);
//          //  if (isMe(pos2)){
//                if (isReadyToInterract) {
//                    moving = Moving.INTERRACT;
//                    interract(deltaTime);
//                } else isReadyToInterract = false;
//            }
//
////            if (type != null)
////                System.out.println("You clicked " + type.getId() + " " + type.getName());

        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (isReadyToInterract) {
                moving = Moving.INTERRACT;
                interract(deltaTime);
            } else isReadyToInterract = false;
        }
        //moving=Moving.FREE;
    }

    boolean doesBoxCollideHorizontal(float x, float y, float width, float height, LevelMap map){
        if (x < 0 ||  x + width > map.getPixelWidth())
            return true;
        for (int row = (int) y/TileType.TILE_SIZE; row< Math.ceil(y+height)/ TileType.TILE_SIZE; row++)
        for (int coll = (int) x / TileType.TILE_SIZE; coll < Math.ceil((x + width) / TileType.TILE_SIZE); coll++) {
            double a = Math.ceil(x+height);
            for (int layer = 0; layer < map.getLayers(); layer++) {
                TileType type = map.getTileByCoordinate(layer, coll, row);
                if (type != null && type.isCollidable()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void interract(float delta) {
        player.isGrounded=true;
        alignTop(player, this);

        moving = Moving.FREE;
        System.out.println("Box interract");
    }
}
