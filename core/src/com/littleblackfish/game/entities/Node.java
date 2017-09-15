package com.littleblackfish.game.entities;



public class Node {
    private NodeType type;

    private Node topNode;
    private Node bottomNode;
    private Node rightNode;
    private Node leftNode;

    public int x;
    public int y;


    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public Node getTopNode() {
        return topNode;
    }

    public void setTopNode(Node topNode) {
        if(topNode!=null)
            topNode.bottomNode = this;
        this.topNode = topNode;
    }

    public Node getBottomNode() {
        return bottomNode;
    }

    public void setBottomNode(Node bottomNode) {
        if(bottomNode!=null)
            bottomNode.topNode = this;
        this.bottomNode = bottomNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        if(rightNode!=null)
            rightNode.leftNode = this;
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        if(leftNode!=null)
            leftNode.rightNode = this;
        this.leftNode = leftNode;
    }


    private Node(NodeType type,int x, int y, Node top, Node right,Node bottom , Node left){
        this.type = type;
        this.topNode = top;
        this.bottomNode = bottom;
        this.leftNode = left;
        this.rightNode = right;
        this.x = x;
        this.y = y;
    }

    public static Node horizontalWall(int x , int y , Maze maze){
        Node hw = new Node(NodeType.HORIZONTAL_WALL,x,y,null,null,null,null);
        if(y<maze.getHeight() && maze.getCells()[y][x]!=null)
            hw.setTopNode(maze.getCells()[y][x]);
        if(y>0 && maze.getCells()[y-1][x]!=null)
            hw.setBottomNode(maze.getCells()[y-1][x]);
        return hw;
    }

    public static Node verticalWall(int x , int y, Maze maze){
        Node vw = new Node(NodeType.VERTICAL_WALL,x,y,null,null,null,null);

        if(x>0 && maze.getCells()[y][x-1]!=null)
            vw.setLeftNode(maze.getCells()[y][x-1]);
        if(x<maze.getWidth() && maze.getCells()[y][x]!=null)
            vw.setRightNode(maze.getCells()[y][x]);
        return vw;
    }

    public static Node cell(int x, int y , Maze maze){
        Node c = new Node(NodeType.CELL,x,y,null,null,null,null);
        //if(maze.getVerticalWalls()[y][x] != null)
            c.setLeftNode(maze.getVerticalWalls()[y][x]);
        //if(maze.getVerticalWalls()[y][x+1] != null)
            c.setRightNode(maze.getVerticalWalls()[y][x+1]);
        //if(maze.getHorizontalWalls()[y][x]!=null)
            c.setBottomNode(maze.getHorizontalWalls()[y][x]);
        //if(maze.getHorizontalWalls()[y+1][x]!=null)
            c.setTopNode(maze.getHorizontalWalls()[y+1][x]);
        return c;
    }


    public enum  NodeType{
        HORIZONTAL_WALL,VERTICAL_WALL,CELL;
    }

}

