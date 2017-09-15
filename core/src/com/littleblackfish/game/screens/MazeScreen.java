package com.littleblackfish.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.littleblackfish.game.entities.Maze;
import com.littleblackfish.maze.DFS;
import com.littleblackfish.maze.Generator;

import static jdk.nashorn.internal.objects.NativeMath.min;


public class MazeScreen implements Screen{

    private Maze maze;
    ExtendViewport viewport;
    ShapeRenderer renderer;

    private static final float MARGIN = 25;
    private static final float WORLD_SIZE = 400;

    public MazeScreen(int mazeWidth, int mazeHeight, Generator generator){
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(WORLD_SIZE,WORLD_SIZE);
        maze = new Maze(mazeWidth,mazeHeight);
        generator.mazify(maze);
    }

    @Override
    public void show() {

    }

    public void render(float delta){

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Line);

        float cellSize = (float) min((WORLD_SIZE-2*MARGIN)/maze.getWidth(),(WORLD_SIZE-2*MARGIN)/maze.getHeight());
        maze.render(renderer,MARGIN,MARGIN,cellSize,cellSize);

        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }


}
