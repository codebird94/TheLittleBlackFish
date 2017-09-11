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
        topNode.bottomNode = this;
        this.topNode = topNode;
    }

    public Node getBottomNode() {
        return bottomNode;
    }

    public void setBottomNode(Node bottomNode) {
        bottomNode.topNode = this;
        this.bottomNode = bottomNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        rightNode.leftNode = this;
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        leftNode.rightNode = this;
        this.leftNode = leftNode;
    }


    private Node(NodeType type,int x, int y, Node top, Node right,Node bottom , Node left){
        this.type = type;
        this.topNode = top;
        this.bottomNode = bottom;
        this.leftNode = left;
        this.rightNode = right;

    }

    public static Node horizontalWall(int x , int y , Maze maze){
        Node hw = new Node(NodeType.HORIZONTAL_WALL,x,y,null,null,null,null);
        if(y<maze.getHeight())
            hw.setTopNode(maze.getCells()[y+1][x]);
        if(y>0)
            hw.setBottomNode(maze.getCells()[y-1][x]);
        return hw;
    }

    public static Node verticalWall(int x , int y, Maze maze){
        Node vw = new Node(NodeType.VERTICAL_WALL,x,y,null,null,null,null);

        if(x>0)
            vw.setLeftNode(maze.getCells()[y][x-1]);
        if(x<maze.getWidth())
            vw.setRightNode(maze.getCells()[y][x+1]);
        return vw;
    }

    public static Node cell(int x, int y , Maze maze){
        Node c = new Node(NodeType.CELL,x,y,null,null,null,null);
        c.setLeftNode(maze.getVerticalWalls()[y][x]);
        c.setRightNode(maze.getVerticalWalls()[y][x+1]);
        c.setBottomNode(maze.getHorizontalWalls()[y][x]);
        c.setTopNode(maze.getHorizontalWalls()[y+1][x]);
        return c;
    }


}

enum  NodeType{
    HORIZONTAL_WALL,VERTICAL_WALL,CELL;
}