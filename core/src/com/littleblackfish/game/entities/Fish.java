package com.littleblackfish.game.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.littleblackfish.game.utility.Assets;
import com.littleblackfish.game.utility.Constants;

import static com.littleblackfish.game.utility.Constants.CELL_SIZE;
import static com.littleblackfish.game.utility.Constants.FISH_SIZE;
import static com.littleblackfish.game.utility.Constants.FISH_VELOCITY;
import static com.littleblackfish.game.utility.Constants.WALL_SAFE_MARGIN;

public class Fish {

    public static final String TAG = Fish.class.getName();

    public Maze maze;
    private Vector2 position;
    private Constants.Direction direction;
    private Vector2 velocity;


    public Fish(Maze maze){
        this.maze = maze;
        init();
    }

    public void init(){
        position = new Vector2(maze.getOriginX(),maze.getOriginY());
        velocity = new Vector2(0,0);
        direction = Constants.Direction.DOWN;
    }


    public void render(float offsetX, float offsetY ,SpriteBatch batch){
        TextureAtlas.AtlasRegion region = Assets.instance.fishAssets.fish1;
        batch.draw(
                region.getTexture(),offsetX+((position.x+1)*Constants.CELL_SIZE)-(Constants.FISH_SIZE/2),
                offsetY+(position.y*Constants.CELL_SIZE)-Constants.FISH_SIZE/2,0,0,
                Constants.FISH_SIZE,Constants.FISH_SIZE,1,1,0,region.getRegionX(),region.getRegionY(),
                region.getRegionWidth(),region.getRegionHeight(),false,false
        );
    }

    public void render(float offsetX, float offsetY ,ShapeRenderer renderer){
        renderer.setColor(Color.RED);
        renderer.circle((+Constants.FISH_SIZE/2)+offsetX+(position.x*CELL_SIZE),
                (+Constants.FISH_SIZE/2)+offsetY+(position.y*CELL_SIZE),Constants.FISH_SIZE/2);
    }


    public void update(float delta){
        //Todo: do something for when several keys are pressed together
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velocity.set(FISH_VELOCITY,0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velocity.set(-FISH_VELOCITY,0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            velocity.set(0,FISH_VELOCITY);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            velocity.set(0,-FISH_VELOCITY);
        }else{
            velocity.set(0,0);
        }

        Vector2 lastPos = new Vector2(position.x,position.y);

        position.x += velocity.x*delta;
        position.y += velocity.y*delta;

        if(collision()){
            velocity.set(0,0);
            position.set(lastPos.x,lastPos.y);
        }

        victory();

    }

    /*
    private boolean collidesWidthWall(Node wall){
        if(wall == null)
            return false;
        Gdx.app.log(TAG,position.toString());
        Gdx.app.log(TAG,new Vector2(wall.x,wall.y).toString());
        switch (wall.getType()){
            case HORIZONTAL_WALL:
                return (Math.abs(position.y- wall.y)<Constants.WALL_SAFE_MARGIN);
               // break;
            case VERTICAL_WALL:
                return (Math.abs(position.x - wall.x)<Constants.WALL_SAFE_MARGIN);
               // break;
            default:
                //Todo: throw exception
                return false;
        }
        */

    private boolean collision(){

        if(position.x<0 || position.y<0){
            return true;
        }

        //Gdx.app.log(TAG,position.toString());
        boolean checkHorizontal = position.y %1.0f> 0.05 && position.y%1.0f<(1-0.05);
        boolean checkVertical = position.x %1.0f> 0.05 && position.x%1.0f<(1-0.05);

        if(checkHorizontal){
            int y = (int) Math.ceil(position.y);
            int x1 = (int) Math.ceil(position.x);
            int x2 = (int) Math.floor(position.x);
            if(maze.getWall(Node.NodeType.HORIZONTAL_WALL,x1,y)!=null && position.x%1.0>0.05){
                return true;
            }
            if(maze.getWall(Node.NodeType.HORIZONTAL_WALL, x2,y)!=null && position.x%1.0<(1.0-0.05)){
                return true;
            }
        }
        if(checkVertical){
            int y1= (int) Math.ceil(position.y);
            int y2 = (int) Math.floor(position.y);
            int x = (int) Math.ceil(position.x);
            if(maze.getWall(Node.NodeType.VERTICAL_WALL,x,y1)!=null && position.y%1.0>0.05){
                return true;
            }
            if(maze.getWall(Node.NodeType.VERTICAL_WALL,x,y2)!=null && position.y%1.0<(1.0-0.05)){
                return true;
            }
        }
        return false;
    }

    public boolean victory(){
        if(((int)position.x) == maze.getDestinationX() &&
                ((int)position.y)==maze.getDestinationY()) {
            Gdx.app.log(TAG, "VICTORY!");
            return true;
        }
        return false;
    }

}



