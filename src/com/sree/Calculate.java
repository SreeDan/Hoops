package com.sree;

import java.awt.geom.Line2D;
import java.util.*;

public class Calculate {

    public static double linearSpeed(Point start, Point end) {
        start.setY(start.getY() * -1);
        end.setY(end.getY() * -1); //  Convert it to rectangular form
        double distance = Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
        return distance;
    }

    public static ArrayList<Point> trajectory(Point start, Point end, Hoop hoop) {
        Ball ball = Variables.BALL;

        ArrayList<Point> pts = new ArrayList<Point>();
        double x = ball.getX();
        double y = ball.getY() * -1;
        double refx = start.getX() - end.getX();
        double refy = start.getY() - end.getY();
        double launchx = refx * -1;
        double launchy = refy * -1;
        double time = 0;

        //  To keep the speed from getting too high
        refx *= 0.75;
        if (refx > 10) {
            refx = 10;
        }

        refy *= 0.5;
        if (refy > 40) {
            refy = 40;
        }

        while (true) {
            pts.add(new Point((int) (x + 0.5), (int) (y * -1 + 0.5)));
            /*if (bounce) {
                refx *= -1;
                bounce = false;
            }
             */
            //System.out.println(refx);
            x += refx - 0.1 * time;
            y += refy - ((9.84/2) * time*time);
            time += 0.15;
             /*
             if (x + refx - 0.1 * (time + 1) <= 0) {
                x = 1;
            } else if (x + refx - 0.1 * (time + 1) >= 270) {
                x = 269;
            }
              */

            if (x >= 269 || x <= 1) {
                refx *= 0.85;
                refx *= -1;
                //bounceReset = true;
            }

            if (hoop.isInside(x, y * -1)) {
                break;
            }

            /*hoopsLoop:
            for (Hoop hoop: hoops) {*/
                Line[] lines = hoop.getLines();

                Line left = lines[0];
                Line bottom = lines[1];
                Line right = lines[2];

                for (int i = 0; i < 3; i++) {
                    //Line2D lStart = new Line2D.Double(lines[i].getStart().getX(), lines[i].getStart().getY(), lines[i].getEnd().getX(), lines[i].getEnd().getY());
                    //Line2D ballTrajectory = new Line2D.Double(x, y*-1, x + refx - 0.1 * (time + 0.15), y + refy - ((9.84/2) * (time + 0.15)* (time + 0.15)));
                    //if (i == 0 || i == 2) {
                        //Line pos = new Line((int) x, (int) y * -1, (int) (x + refx - 0.1 * (time + 1)), (int) (y + refy - ((9.84 / 2) * (time + 1) * (time + 1))) * -1);
                        //Line2D pos = new Line2D.Double(x, y * -1, (x + refx - 0.1 * (time + 1)), (y + refy - ((9.84 / 2) * (time + 1) * (time + 1))) * -1);
                        Line2D border = new Line2D.Double(lines[i].getStart().getX(), lines[i].getStart().getY(), lines[i].getEnd().getX(), lines[i].getEnd().getY());
                        Line2D pos = new Line2D.Double(x, y * -1, refx - 0.1 * (time + 0.15), (y + refy - ((9.84 / 2) * (time + 0.15) * (time + 0.15))) * -1);

                        if (pos.intersectsLine(border) && i != 1) {
                            System.out.println("before x is " + refx);
                            if (refx < 0) {
                                System.out.println(refx);
                                x = lines[i].getStart().getX() + 22;
                            } else {
                                System.out.println("else");
                                System.out.println(x + " before");
                                x = lines[i].getStart().getX() - 30;
                                System.out.println(x + " after");
                            }
                            refx *= -1;
                            //x = lines[i].getStart().getX();
                            System.out.println(lines[i].getStart().getX());
                            x = lines[i].getStart().getX() - Math.signum(refx);
                            break;
                            //break hoopsLoop;
                        //}
                    }
                    //Point lStart = lines[i].getStart();
                    //Point lEnd = lines[i].getEnd();

                }

            if (y *-1 > 500) {
                pts.add(new Point((int) (x + 0.5), (int) (y * -1 + 0.5)));
                //System.out.println("break");
                break;
            }
        }
        return pts;

    /*while (true) {
      pts.add(new Point((int) x, (int) y * -1));
      x += refx - 0.1 * time;
      y += refy - (9.84 / 2) * time;
      time += 0.05;
      if (x > 300 || y*-1 > 500 || x < 0) {
        break;
      }
    }

    while (y * -1 <= 500) {
      refx *= 0.75;
      //if (y * -1 <= 500 && x + )
      if (x < 0) {
         while (y * -1 <= 500 && x <= 300) {
          Point pt = pts.get(pts.size() - 1);
          if (refx < 0) {
            x = x - refx;
          } else {
            x = x + refx;
          }
          y += refy - (9.84 / 2) * time;
          time += 0.25;
          pts.add(new Point((int) x, (int) y * -1));
         }
      } else if (x > 300) {
        while (y * -1 <= 500 && x >= 0) {
          Point pt = pts.get(pts.size() - 1);
          if (refx < 0) {
            x = x + refx;
          } else {
            x = x - refx;
          }
          y += refy - (9.84 / 2) * time;
          time += 0.25;
          pts.add(new Point((int) x, (int) y * -1));
        }
      } else {
        break;
      }
    }
    return pts;*/
    }
    /*ArrayList<Point> pts = new ArrayList<Point>();

    double x = start.getX();
    double y = start.getY();
    double initialX = x;
    double initialY = y;
    //double angle = Math.toRadians(110);
    double refx = end.getX() - start.getX();
    double refy = end.getY() - start.getY();
    double angle = Math.atan(refy / refx);
    System.out.println("ref is " + angle);
    double degrees = Math.toDegrees(angle);
    if (degrees < 0) {
      degrees *= -1;
    }

    if (refx < 0 && refy > 0) {
      degrees += 90;
    } else if (refx < 0 && refy < 0) {
      degrees += 180;
    } else if (refx > 0 && refy < 0) {
      degrees += 270;
    }

    angle = Math.toRadians(degrees);

    System.out.println("angle is " + Math.toDegrees(angle));
    double velocity = 10;
    double xVelocity = velocity * Math.cos(angle);
    double yVelocity = velocity * Math.sin(angle);
    double time = 0;


    for (int i = 0; i < 101; i ++) {
      pts.add(new Point((int) x, (int) y * -1));
      pts.add(new Point((int) ((start.getX() - x) + start.getX()), (int) y * -1));
      time += 0.25;
			x = initialX + xVelocity * time;
			y = initialY + (yVelocity * time - (9.84 / 2) * time * time);
    }

    return pts;*/
/*
  public static void setup(Graphics g){
		double x = 0;
  double y = 400;
  double initialX = x;
   double initialY = y;
   double angle = 45;
   double velocity = 80;
   double xVelocity = velocity * Math.cos(Math.toRadians(angle));
   double yVelocity = velocity * Math.sin(Math.toRadians(angle));
   double time = 0;

   for(int i = 0; i < 1000; i++){
			g.drawEllipse((int) x,(int) y, 20, 20);
			time += 0.1;
			x = initialX + xVelocity * time;
			y = initialY - (yVelocity * time - (9.84 / 2) * time * time);
		}
	}
	}
  */
}
