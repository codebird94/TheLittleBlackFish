package com.littleblackfish.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.littleblackfish.game.entities.Maze;
import com.littleblackfish.game.entities.Node;
import com.littleblackfish.game.entities.Node.NodeType;
import com.littleblackfish.game.utility.Constants;

import java.util.Random;
import java.util.Stack;

import static com.badlogic.gdx.Gdx.*;

/**
 * Created by RAYAPARDAZ1 on 11/09/2017.
 */

public class DFS extends Generator {

    public DFS(){

    }

    @Override
    public void mazify(Maze raw) {
        //Todo: implement the dfs algorithm
        Stack<Node> stack = new Stack<Node>();
        boolean seen[][] = new boolean[raw.getHeight()][raw.getWidth()];

        Node current = raw.getCells()[raw.getOriginY()][raw.getOriginX()];
        stack.add(current);

        for(int i=0;i<raw.getHeight();i++)
            for(int j=0;j<raw.getWidth();j++)
                seen[i][j]=false;

        //seen[raw.getOriginY()][raw.getOriginX()] = true;
        //Random direction = new Random();

        while(!stack.isEmpty()){
            app.log(TAG,"current cell: "+current.x+Integer.toString(current.y));
            seen[current.y][current.x] = true;

            Array<Constants.Direction> openWays = new Array<Constants.Direction>();
            if(current.x >0 && !seen[current.y][current.x-1]){
                app.log(TAG,"left is open");
                openWays.add(Constants.Direction.LEFT);}
            if(current.x < raw.getWidth()-1 && !seen[current.y][current.x+1]){
                app.log(TAG,"right is open");
            openWays.add(Constants.Direction.RIGHT);}
            if(current.y >0 && !seen[current.y-1][current.x]){
                app.log(TAG,"bottom is open");
            openWays.add(Constants.Direction.DOWN);}
            if(current.y < raw.getHeight()-1 && !seen[current.y+1][current.x]){
                app.log(TAG,"up is open");
                openWays.add(Constants.Direction.UP);}

            if(openWays.size>0){
                stack.add(current);
                Constants.Direction path = openWays.get(MathUtils.random(0,openWays.size-1));
                switch (path){
                    case UP:
                        app.log(TAG,"going upward");
                        app.log(TAG,"next position: "+(current.y+1)+Integer.toString(current.x));

                        raw.removeWall(NodeType.HORIZONTAL_WALL,current.getTopNode().x,current.getTopNode().y);
                        current = (raw.getCells()[current.y+1][current.x]);

                        break;
                    case DOWN:
                        app.log(TAG,"going downward");
                        app.log(TAG,"next position: "+(current.y-1)+Integer.toString(current.x));

                        raw.removeWall(NodeType.HORIZONTAL_WALL,current.getBottomNode().x,current.getBottomNode().y);
                        current = raw.getCells()[current.y-1][current.x];
                        break;
                    case LEFT:
                        app.log(TAG,"going left");
                        app.log(TAG,"next position: "+current.y+Integer.toString(current.x-1));


                        raw.removeWall(NodeType.VERTICAL_WALL,current.getLeftNode().x,current.getLeftNode().y);
                        current = raw.getCells()[current.y][current.x-1];
                        break;
                    case RIGHT:
                        app.log(TAG,"going right");
                        app.log(TAG,"next position: "+current.y+Integer.toString(current.x+1));

                        raw.removeWall(NodeType.VERTICAL_WALL,current.getRightNode().x,current.getRightNode().y);
                        current = raw.getCells()[current.y][current.x+1];


                        break;
                    default:
                        break;
                }
                app.log(TAG,"updated position: "+current.x+Integer.toString(current.y));
            }
            else{
                current = stack.pop();
                app.log(TAG,"stack popped");
            }
        }

        //Todo: remove the walls at origin and destination
    }
}
