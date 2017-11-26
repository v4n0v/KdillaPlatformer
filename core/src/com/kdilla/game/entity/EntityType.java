package com.kdilla.game.entity;


public enum EntityType {
    PLAYER("player", 29, 58, 100, false, false),
    BOX("box", 54, 54, 20, true, true),
    STAIRS("stairs", 36, 18, 20, true, false);

    private String id;
    private int width;
    private int height;
    private float weight;
    private boolean isInteractable;


    private boolean isCollidable;

    EntityType(String id, int width, int height, float weight, boolean isInteractable, boolean isCollidable) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.isInteractable = isInteractable;
        this.isCollidable=isCollidable;
    }


    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

}
