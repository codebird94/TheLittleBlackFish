package com.littleblackfish.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.littleblackfish.game.entities.Fish;
import com.littleblackfish.game.entities.Maze;
import com.littleblackfish.game.utility.Constants;
import com.littleblackfish.maze.DFS;
import com.littleblackfish.maze.Generator;

import java.util.ConcurrentModificationException;

import static jdk.nashorn.internal.objects.NativeMath.min;


public class MazeScreen implements Screen{

    private Maze maze;
    private Fish fish;

    ExtendViewport viewport;
    ShapeRenderer renderer;
    SpriteBatch batch;




    public MazeScreen(int mazeWidth, int mazeHeight, Generator generator){
        renderer = new ShapeRenderer();
        //renderer.setAutoShapeType(true);
        batch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        init(generator,mazeWidth,mazeHeight);
    }


    public void init(Generator generator,int mazeWidth,int mazeHeight){
        maze = new Maze(mazeWidth,mazeHeight);
        fish = new Fish(maze);
        generator.mazify(maze);
        fish.init();
    }


    @Override
    public void show() {
        //Todo: what i must do here?
    }

    public void update(float delta){
        fish.update(delta);
    }

    public void render(float delta){
        update(delta);

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,Constants.BACKGROUND_COLOR.a);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        //Todo: a better way to initialize the size of cells
        //float cellSize = (float) min((WORLD_SIZE-2*MARGIN)/maze.getWidth(),(WORLD_SIZE-2*MARGIN)/maze.getHeight());
        maze.render(renderer,Constants.MAZE_MARGIN,Constants.MAZE_MARGIN,Constants.CELL_SIZE,Constants.CELL_SIZE);
        renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        fish.render(Constants.MAZE_MARGIN,Constants.MAZE_MARGIN,renderer);
        renderer.end();
        //batch.begin();
        //fish.render(batch);
       // batch.end();
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
