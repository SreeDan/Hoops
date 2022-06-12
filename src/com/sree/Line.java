package com.sree;

public class Line {
    private Point start;
    private Point end;
    private double slope;
    private double b;

    public Line() {
        start = new Point(0, 0);
        end = new Point(0, 0);
    }

    public Line(int x1, int y1, int x2, int y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        slope = ((double) y2 - y1) / ((double) x2 - x1);
        b = y1 - (slope * x1);
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    /*public boolean intersect(Line l, boolean vertical) {
        if (vertical) {
            //System.out.println("y is " +)

            double y = slope * l.getStart().getX() + b;
            System.out.println("y of ball is " + start.getY() + ", y of wall is start: " + l.getStart().getY() + " - end: " + l.getEnd().getY());
            if ((y <= l.getStart().getY() * -1 && y >= l.getEnd().getY() * -1) && ((y <= start.getY() && y >= end.getY()) || (y <= end.getY() && y >= start.getY()))) {
                System.out.println("banking at " + start.getY() + " - " + end.getY() + "; " + y);
                return true;

            }

            return false;
        }
        double x = (l.getStart().getY() + b) / slope;
        if (x >= l.getStart().getX() && x <= l.getEnd().getX()) {
            return true;
        }
        return false;
    }

     */

    /*public boolean intersect(Line l, boolean vertical) {
        if (vertical) {

        }
    }

     */
}
