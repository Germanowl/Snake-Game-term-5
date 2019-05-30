package Model;

import java.util.ArrayList;

public class Snake {

    private int HeadV;
    private int HeadH;

    private int Direction;

    private ArrayList<Integer> Horizontals = new ArrayList<Integer>();
    private ArrayList<Integer> Verticals = new ArrayList<Integer>();

    public Snake(int Horizontal, int Vertical, int Direction){
        this.HeadH = Horizontal;
        this.HeadV = Vertical;
        this.Direction = Direction;
    }

    public Snake(){

    }

    public void addSegment (int X, int Y){
    Horizontals.add(X);
    Verticals.add(Y);
    }

    public void addSegment (int X, int Y, int index){
        Horizontals.add(index , X);
        Verticals.add(index , Y);
    }

    public int Size(){
        return Horizontals.size();
    }

    public void setHeadH(int headH) {
        HeadH = headH;
    }
    public void setHeadV(int headV) {
        HeadV = headV;
    }

    public int getHeadH() {
        return HeadH;
    }
    public int getHeadV() {
        return HeadV;
    }

    public int getDirection() {
        return Direction;
    }
    public void setDirection(int direction) {
        Direction = direction;
    }

    public int getVertical( int index)
    {
        return Verticals.get(index);
    }
    public int getHorizontal( int index)
    {
        return Horizontals.get(index);
    }

    public  ArrayList<Integer> getHorizontals(){
        return Horizontals;
    }
    public  ArrayList<Integer> getVerticals(){
        return Verticals;
    }
}
