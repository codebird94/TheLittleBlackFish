package com.littleblackfish.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.littleblackfish.game.entities.Maze;
import com.littleblackfish.game.screens.MazeScreen;
import com.littleblackfish.game.utility.Assets;
import com.littleblackfish.game.utility.Constants;
import com.littleblackfish.maze.DFS;

public class LittleBlackFishGame extends Game {

	Screen testScreen;

	@Override
	public void create () {
        Assets.instance.init();
        testScreen = new MazeScreen(Constants.DEFAULT_MAZE_WIDTH,Constants.DEFAULT_MAZE_HEIGHT,new DFS());
		setScreen(testScreen);
	}

}
