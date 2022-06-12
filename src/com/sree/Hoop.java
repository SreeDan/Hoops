package com.sree;

public class Hoop {
    private Line edgeLeft;
    private Line edgeBottom;
    private Line edgeRight;
    private int x;
    private int y;

    public Hoop(int x, int y) {
        /*edgeLeft = new Line(x - 30, y - 20, x - 30, y + 30);
        edgeBottom = new Line(x - 30, y + 30, x + 30, y + 30);
        edgeRight = new Line(x + 30, y - 20, x + 30, y + 30);
         */
        /*edgeLeft = new Line(x - 30, y - 20, x - 30, y + 30);
        edgeBottom = new Line(x - 30, y + 30, x + 45, y + 30);
        edgeRight = new Line(x + 45, y - 20, x + 45, y + 30);
         */
        setup(x, y);
        //System.out.println(edgeLeft.getStart().getX() + " - " + edgeRight.getStart().getX() + ", " + edgeBottom.getStart().getY() + " - " + edgeRight.getStart().getY());
    }

    private void setup(int x, int y) {
        edgeLeft = new Line(x - 30, y - 20, x - 30, y + 30);
        edgeBottom = new Line(x - 30, y + 30, x + 30, y + 30);
        edgeRight = new Line(x + 30, y - 20, x + 30, y + 30);
        this.x = x;
        this.y = y;
    }

    public Line[] getLines() {
        return new Line[] {edgeLeft, edgeBottom, edgeRight};
    }

    public boolean isInside(double x, double y) {
        //System.out.println(x + ", " + y);
        //System.out.println(edgeLeft.getStart().getX() + " - " + edgeRight.getStart().getX() + ", " + edgeBottom.getStart().getY() + " - " + edgeRight.getStart().getY());
        return (x > edgeLeft.getStart().getX() && x < edgeRight.getStart().getX() && y - 20 < edgeBottom.getStart().getY() && y - 20 > edgeRight.getStart().getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setAttributes(int x, int y) {
        setup(x, y);
    }

    public void move(int changeX, int changeY) {
        setup(changeX + x, changeY + y);
    }


}