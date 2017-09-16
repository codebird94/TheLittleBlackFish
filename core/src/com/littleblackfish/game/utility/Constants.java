package com.littleblackfish.game.utility;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by RAYAPARDAZ1 on 06/09/2017.
 */

public class Constants {
    //Maze drawing
    public static final float WORLD_SIZE = 400;
    public static final float MAZE_MARGIN = 20;
    public static final float CELL_SIZE = 36;
    public static final int DEFAULT_MAZE_WIDTH = 10;
    public static final int DEFAULT_MAZE_HEIGHT = 10;

    //the fish
    public static final float FISH_SIZE = CELL_SIZE;
    public static final String FISH_0 = "fish-0";
    public static final String FISH_1 = "fish-1";
    public static final float FISH_VELOCITY = 3; //velocity unit = cell per second
    public static final float FISH_CENTER_X = FISH_SIZE/2;
    public static final float FISH_CENTER_Y = FISH_SIZE/2;

    public static final float WALL_SAFE_MARGIN = FISH_SIZE/(2*CELL_SIZE);


    public static final Color BACKGROUND_COLOR = new Color(0.906f,0.902f,0.988f,1);

    public static final String TEXTURE_ATLAS = "images/littleBlackFish.pack.atlas";

    public enum Direction{
        UP,DOWN,LEFT,RIGHT;
    }


}


