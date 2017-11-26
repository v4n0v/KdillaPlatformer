package com.kadilo.game.engine.utils;

/**
 * Created by avetc on 17.09.2017.
 */

public class Timer {

    private static float timer;


    public static boolean interval(float interval, float delta) {
        timer += delta;
        if (timer >= interval) {
            timer = 0;
            return true;
        } else return false;
    }
}
