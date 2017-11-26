package com.kdilla.game.engine.math;

import java.util.Random;

/**
 * Created by avetc on 11.09.2017.
 */

public class Rnd {

    private static final Random rnd = new Random();

    public static float nextFloat(float min, float max) {
        return rnd.nextFloat() * (max - min) + min;
    }
}

