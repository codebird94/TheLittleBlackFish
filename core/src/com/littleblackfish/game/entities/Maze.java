package com.littleblackfish.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;

import static com.littleblackfish.game.entities.Node.NodeType.HORIZONTAL_WALL;
import static com.littleblackfish.game.entities.Node.NodeType.VERTICAL_WALL;


public class Maze {

    private static final String TAG = Maze.class.toString();

    private Node cells[][];
    private Node horizontalWalls[][];
    private Node verticalWalls[][];

    private int width;
    private int height;

    private int originX=0;
    private int originY=0;
    private int destinationX=width-1;
    private int destinationY=height-1;

    public int getOriginX(){
       return originX;
    }

    public int getOriginY(){
        return originY;
    }

    public int getDestinationX(){
        return destinationX;
    }

    public int getDestinationY(){
        return destinationY;
    }

    public Node[][] getCells() {
        return cells;
    }

    public Node[][] getHorizontalWalls() {
        return horizontalWalls;
    }

    public Node[][] getVerticalWalls() {
        return verticalWalls;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public Maze(int width,int height){

        this.width = width;
        this.height = height;

        this.cells = new Node[height][width];
        this.horizontalWalls = new Node[height+1][width];
        this.verticalWalls = new Node[height][width+1];

        //Gdx.app.log(TAG,Integer.toString(horizontalWalls.length)+','+Integer.toString(horizontalWalls[0].length));


        for(int y=0 ; y<height;y++){
            for (int x=0 ; x<width;x++){
                cells[y][x] = Node.cell(x,y,this);
            }
        }

        for(int y=0 ; y<height;y++){
            for (int x=0 ; x<width;x++){
                Node c = cells[y][x];
                Gdx.app.log(TAG,""+Integer.toString(c.x)+" "+Integer.toString(c.y));
            }
        }

        for(int y=0;y<height+1;y++){
            for(int x=0; x<width; x++)
                horizontalWalls[y][x] = Node.horizontalWall(x,y,this);
        }

        for(int y=0;y<height;y++){
            for(int x=0; x<width+1; x++)
                verticalWalls[y][x] = Node.verticalWall(x,y,this);
        }
        //print();
    }
//Todo : write a method for saving the maze as json file
/*
    public static Maze loadTest(String path){
        FileHandle fh = Gdx.files.internal(path);
        JsonReader jr = new JsonReader();
        jr.parse(fh);
        
    }
    */
    //boolean flag = false;
    public void render(ShapeRenderer renderer,float x , float y, float cellWidth, float cellHeight){
        renderer.setColor(Color.BLUE);
        //draw the horizontal walls
        for(int i=0 ; i<=height ; i++){
            for(int j=0; j< width ; j++){
                if(horizontalWalls[i][j] != null){
                   // Gdx.app.log(TAG,Integer.toString(i*height+j));
                    renderer.line(x+cellWidth*j,y+cellHeight*i,x+cellWidth*(j+1),y+cellHeight*i);
                    //if(!flag)
                    //Gdx.app.log(TAG, Float.toString(x+cellWidth*j)+" , "+Float.toString(y+cellHeight*i)+" , "+
                          //  Float.toString(x+cellWidth*(j+1))+" , "+Float.toString(y+cellHeight*i));
                }
            }
        }
        //flag = true;
        //draw the vertical lines
        for(int i=0; i<height; i++){
            for(int j=0;j<=width;j++){
                if(verticalWalls[i][j]!=null) {
                    if ((originY - i <= 1 &&  i - originY <= 1) && (j - originX <= 1 && originX - j <= 1)) {
                        renderer.setColor(Color.RED);
                        renderer.line(x + cellWidth * j, y + cellHeight * i, x + cellWidth * j, y + cellHeight * (i + 1));
                        renderer.setColor(Color.BLUE);
                    }
                    if ((destinationY - i <= 1 && i - destinationY <= 1) && (j - destinationX <= 1 &&  destinationX - j <= 1)) {
                        renderer.setColor(Color.GREEN);
                        renderer.line(x + cellWidth * j, y + cellHeight * i, x + cellWidth * j, y + cellHeight * (i + 1));
                        renderer.setColor(Color.BLUE);
                    } else
                        renderer.line(x + cellWidth * j, y + cellHeight * i, x + cellWidth * j, y + cellHeight * (i + 1));
                }
            }
        }
        /*
        renderer.setColor(Color.RED);
        renderer.line(x+cellWidth*destinationX,y+cellHeight*destinationY,x+cellWidth*destinationX,
                y+cellHeight*(destinationY+1));
        renderer.line(x+cellWidth*destinationX,y+cellHeight*(1+destinationY),x+cellWidth*(destinationX+1),
                y+cellHeight*(destinationY+1));
        renderer.line(x+cellWidth*(1+destinationX),y+cellHeight*(1+destinationY),x+cellWidth*(destinationX+1),
                y+cellHeight*(destinationY));
        renderer.line(x+cellWidth*(1+destinationX),y+cellHeight*destinationY,x+cellWidth*destinationX,
                y+cellHeight*(destinationY));

        renderer.setColor(Color.GREEN);
        renderer.line(x+cellWidth*originX,y+cellHeight*originY,x+cellWidth*originX,
                y+cellHeight*(originY+1));
        renderer.line(x+cellWidth*originX,y+cellHeight*(1+originY),x+cellWidth*(originX+1),
                y+cellHeight*(originY+1));
        renderer.line(x+cellWidth*(1+originX),y+cellHeight*(1+originY),x+cellWidth*(originX+1),
                y+cellHeight*(originY));
        renderer.line(x+cellWidth*(1+originX),y+cellHeight*originY,x+cellWidth*originX,
                y+cellHeight*(originY));
                */

    }
/*
    public void removeNode(NodeType type, int x , int y){
        Node n = type == NodeType.CELL ? cells[y][x]:
                (type == NodeType.HORIZONTAL_WALL?horizontalWalls[y][x]:verticalWalls[y][x]);
        if(n.getBottomNode()!=null)
            n.getBottomNode().setTopNode(n.getf);
        if(n.getTopNode()!=null)
            n.getTopNode().setBottomNode(null);
        if(n.getRightNode()!=null)
            n.getRightNode().setLeftNode(null);
        if(n.getLeftNode()!=null)
            n.getLeftNode().setRightNode();
    }*/

    public void removeWall(Node.NodeType type, int x , int y){

        Node wall;

        switch (type){
            case HORIZONTAL_WALL:
                wall = horizontalWalls[y][x];
                Gdx.app.log(TAG,"removing h: "+Integer.toString(wall.x)+","+wall.y);
                if(wall.getTopNode()!=null)
                    wall.getTopNode().setBottomNode(null);
                if(wall.getBottomNode()!=null)
                    wall.getBottomNode().setTopNode(null);
                horizontalWalls[y][x]=null;
                break;
            case VERTICAL_WALL:
                wall = verticalWalls[y][x];
                Gdx.app.log(TAG,"removing v: "+Integer.toString(wall.x)+","+wall.y);
                if(wall.getLeftNode()!=null)
                    wall.getLeftNode().setRightNode(null);
                if(wall.getRightNode()!=null)
                    wall.getRightNode().setLeftNode(null);
                verticalWalls[y][x]=null;
                break;
            default:
                break;
        }
    }


    private void print(){
        int cellWidth = 36; int cellHeight = 36;
        float x =20;float y =20;
        for(int i=0 ; i<=height ; i++) {
            for (int j = 0; j < width; j++) {
                if (horizontalWalls[i][j] != null) {
                    Gdx.app.log(TAG, Float.toString(x+cellWidth*j)+" , "+Float.toString(y+cellHeight*i)+" , "+
                            Float.toString(x+cellWidth*(j+1))+" , "+Float.toString(y+cellHeight*i));
                }
            }
        }
        Gdx.app.log(TAG,"--------------------------------------");
        //draw the vertical lines
        for(int i=0; i<height; i++){
            for(int j=0;j<=width;j++){
                if(verticalWalls[i][j]!=null)

                    Gdx.app.log(TAG,Integer.toString(i*height+j));

            }
        }
    }

}

