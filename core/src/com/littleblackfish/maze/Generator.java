package com.littleblackfish.maze;

import com.badlogic.gdx.Gdx;
import com.littleblackfish.game.entities.Maze;

/**
 * Created by RAYAPARDAZ1 on 06/09/2017.
 */

public abstract class Generator {
    public static final String TAG = Generator.class.getName();

    public Generator() {

        //Gdx.app.log(TAG, "Generator created");
    }
    //Todo: use this instead of mazify()
    public Maze generate(int width,int height){
        Maze maze = new Maze(width,height);
        mazify(maze);
        return maze;
    }

    public abstract void mazify(Maze raw);

}
