package com.littleblackfish.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;



public class Maze {

    private Node cells[][];

    private Node horizontalWalls[][];
    private Node verticalWalls[][];

    private int width;
    private int height;

    private int originX;
    private int originY;
    private int destinationX;
    private int destinationY;

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

        for(int y=0 ; y<height;y++){
            for (int x=0 ; x<width;x++){
                cells[y][x] = Node.cell(x,y,this);
            }
        }

        for(int y=0;y<height+1;y++){
            for(int x=0; x<width; x++)
                horizontalWalls[y][x] = Node.horizontalWall(x,y,this);
        }

        for(int y=0;y<height;y++){
            for(int x=0; x<width+1; x++)
                horizontalWalls[y][x] = Node.verticalWall(x,y,this);
        }
    }
//Todo : write a method for saving the maze as json file
/*
    public static Maze loadTest(String path){
        FileHandle fh = Gdx.files.internal(path);
        JsonReader jr = new JsonReader();
        jr.parse(fh);
        
    }
    */

}

