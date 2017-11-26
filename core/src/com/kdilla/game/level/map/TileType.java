package com.kdilla.game.level.map;


import java.util.HashMap;

public enum TileType {

    ASPHALT(1, true,   "Grass"),
    GROUND(2, true,   "Dirt"),
    SKY(3, false,  "Sky"),
    WALLS(4, false, "Lava"),
    LEFT_STAIRS(5, false, "Cloud"),
    RIGHT_STAIRS(6, false,  "Stole");


    public static final int TILE_SIZE = 18;

    private int id;
    private boolean collidable;
    private String name;
    private float damage;


    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    private TileType(int id, boolean collidable,   String name) {
        this(id, collidable,   name, 0);

    }

    private TileType(int id, boolean collidable,   String name, float damage) {
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }




    private static HashMap<Integer, TileType> tileMap;


    static {
        tileMap = new HashMap<Integer, TileType>();
        for (TileType tileType : TileType.values()) {
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }

}
