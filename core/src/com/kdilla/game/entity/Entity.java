package com.kdilla.game.entity;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kdilla.game.level.map.LevelMap;
import com.kdilla.game.utils.Regions;

public abstract class Entity {

    protected float angle;
    protected float scale = 1f;
    int frame;

    protected Vector2 pos;
    protected EntityType type;
    protected float velocityY = 0;
    protected LevelMap map;
    protected boolean isGrounded = false;
    TextureRegion region;
    TextureRegion[] regions;

    public float getWeight() {
        return type.getWeight();
    }


    public Entity(float x, float y, EntityType type, LevelMap map, boolean isGrounded, TextureAtlas atlas, String regionName) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.map = map;
        this.isGrounded = isGrounded;
        this.region = atlas.findRegion(regionName);

    }

    public void update(float deltaTime, float gravity) {
        float newY = pos.y;

        if (!isGrounded) {
            this.velocityY += gravity * deltaTime * getWeight();
            newY += this.velocityY * deltaTime;
        }
        // проверяем колизии с объектами
//        for (Entity entity : map.getEntities()) {
//            if (map.doesRectCollideWithRect(pos.x, newY, entity)) {
//                if (velocityY <0){
//                    isGrounded=true;
//                }
//                velocityY=0;
//            }
//        }

        // проверяем колизии с картами
        if (map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight())
                || map.getCollideWithRect(pos.x, newY) != null) {
            if (velocityY < 0) {
                this.pos.y = (float) Math.floor(pos.y);
                isGrounded = true;
            }
            this.velocityY = 0;

//        } else if (map.getCollideWithRect(pos.x, newY)!=null){
//            this.pos.y = map.getCollideWithRect(pos.x, newY).getY();
//            isGrounded=true;

        } else {
            this.pos.y = newY;
            isGrounded = false;
        }

    }


    public abstract void render(SpriteBatch batch);


    protected void moveX(float amount) {
        float newX = this.pos.x + amount;
        if (!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight()))
            this.pos.x = newX;
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public int getWidth() {
        return type.getWidth();
    }

    public int getHeight() {
        return type.getHeight();
    }

    public int getHalfWidth() {
        return type.getWidth() / 2;
    }

    public int getHalfHeight() {
        return type.getHeight() / 2;
    }

    public EntityType getType() {
        return type;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public boolean isGrounded() {
        return isGrounded;
    }

    public float getLeft() {
        return pos.x;

    }

    public void setWidthProportion(int width) {

        int aspect = region.getTexture().getHeight() / region.getTexture().getWidth();
        this.region.setRegion(pos.x, pos.y, width * aspect, width);

//        region.setRegionWidth(width);
//        region.setRegionHeight(width*aspect);

    }

    public float getRight() {
        return pos.x + getWidth();
    }

    public float getTop() {
        return pos.y + getHeight();

    }

    public float getBottom() {
        return pos.y;

    }

    // выравнивания
    protected void alignVertical(Entity entity1, Entity entity2) {
        entity1.getPos().x = entity2.getLeft() + entity2.getHalfWidth() - entity1.getHalfWidth();
    }

    protected void alignTop(Entity entity1, Entity entity2) {
        entity1.getPos().x = entity2.getLeft() + entity2.getHalfWidth() - entity1.getHalfWidth();
        entity1.getPos().y = entity2.getTop();
    }

    boolean isCollided(Entity kto, Entity vkogo) {
        return isCollided(kto, vkogo, 0);
    }

    // сталкновение с отступом
    protected boolean isCollided(Entity kto, Entity vkogo, int spread) {
        // если середине по горионтали, проверяем вертикаль
        if (vkogo.getLeft() < kto.getLeft() && vkogo.getRight() > kto.getRight()) {
            if (vkogo.getTop() > kto.getBottom() || vkogo.getTop() > kto.getTop() ||
                    vkogo.getBottom() < kto.getTop() && vkogo.getBottom() > kto.getBottom())
                return true;

        } else if (vkogo.getLeft() < kto.getRight() + spread && vkogo.getLeft() > kto.getLeft() ||
//                (
//
//                vkogo.getLeft()<kto.getLeft()&& vkogo.getRight()>kto.getRight()&&
//                        vkogo.getTop()>kto.getBottom()&& vkogo.getTop()<kto.getTop()
//                )||

                vkogo.getRight() > kto.getLeft() - spread && vkogo.getRight() < kto.getRight()) {
            return true;
        }
        return false;
    }

    public Entity getEntityByCoordinate(float x, float y) {
        if (getLeft() < x && getRight() > x && getTop() > y && getBottom() < y)
            return this;
        return null;
    }

}
