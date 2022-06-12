package com.sree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

    private Point[] dragPoint;
    private ArrayList<Point> pts;
    private int x;
    private int y;
    private int counter;
    private javax.swing.Timer timer;
    private Ball ball;
    private Hoop hoop;
    private Hoop oldHoop;

    public Board( Color c ) {
        setBackground( c );
        setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        dragPoint = new Point[2];
        pts = new ArrayList<Point>();
        counter = 0;
        timer = new javax.swing.Timer( 25, this );
        ball = Variables.BALL;
        hoop = new Hoop(230, 320);
        addMouseMotionListener( this );
        addMouseListener( this );
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        //this.setBackground(Color.decode("#b2b2b2"));
        //Color color = this.getBackground().darker();
        //System.out.println(String.format("#%06x", color.getRGB() & 0x00FFFFFF));

        /*for (int i = 0; i < pts.size(); i++) {
            g.drawOval(pts.get(i).getX(), pts.get(i).getY(), 10, 10);
        }

         */


        /*if (Variables.DISABLED)
            this.setBackground(Color.decode("#DADADA"));
        else
            this.setBackground(Color.WHITE);

         */
        Line[] ls = hoop.getLines();
        for (int i = 0; i < ls.length; i++) {
            g.drawLine(ls[i].getStart().getX(), ls[i].getStart().getY(), ls[i].getEnd().getX(), ls[i].getEnd().getY());
            if (Variables.newHoop) {
                Line[] ols = oldHoop.getLines();
                g.drawLine(ols[i].getStart().getX(), ols[i].getStart().getY(), ols[i].getEnd().getX(), ols[i].getEnd().getY());
            }
        }
/*
        g.drawLine(ls[1].getStart().getX(), ls[1].getStart().getY(), ls[1].getEnd().getX(), ls[1].getEnd().getY());
        g.drawLine(ls[2].getStart().getX(), ls[2].getStart().getY(), ls[2].getEnd().getX(), ls[2].getEnd().getY());
 */
        //System.out.println(ball.getX() + ", " + ball.getY());

        String winningText = "";
        if (Variables.LOSE) {
            winningText = "You finished with a score of " + Variables.SCORE;
        }
        g.drawString(winningText, 58, 50);

        String highScore = "High Score: " + Variables.HIGH;
        g.drawString(highScore, 10, 15);
        String score = "Score: " + Variables.SCORE;
        g.drawString(score, 240, 15);


        g.drawImage(ball.getImg(), ball.getX(), ball.getY(), 30, 30, null);

        if (Variables.newHoop) {
            Line[] ols = oldHoop.getLines();
            if (ols[1].getStart().getY() <= 350) {
                oldHoop.move(0, 1);
                hoop.move(0, 1);
                ball.move(0, 1);
                repaint();
            }
        }
        /*ball.setX(x);
    ball.setY(y);
        */

    }

    public void mouseEntered( MouseEvent e ){}
    public void mousePressed( MouseEvent e ){
        /*if (Variables.DISABLED)
            return;

         */
        if (Variables.LOSE)
            Variables.SCORE = 0;


        dragPoint[0] = new Point(e.getX(), e.getY());
    }
    public void mouseReleased( MouseEvent e ){
        //if (Variables.DISABLED)
        //    return;
        dragPoint[1] = new Point(e.getX(), e.getY());
        Calculate.linearSpeed(dragPoint[0], dragPoint[1]);
        pts = Calculate.trajectory(dragPoint[0], dragPoint[1], hoop);
        counter = 0;
        timer.stop();
        timer = new javax.swing.Timer( 25, this );
        timer.start();
    }
    public void mouseExited( MouseEvent e ){}
    public void mouseClicked( MouseEvent e ){}
    public void mouseDragged( MouseEvent e ){}
    public void mouseMoved( MouseEvent e ){}

    public void actionPerformed( ActionEvent e ) {
        if (counter < pts.size()) {
            Point pt = pts.get(counter);
            ball.setX(pt.getX());
            ball.setY(pt.getY());
            counter++;
            if (counter == pts.size()) {
                if (pts.get(pts.size() - 1).getY() > 500) {
                    Variables.LOSE = true;
                    ball.setX(20);
                    ball.setY(400);
                    Variables.newHoop = false;
                    if (Variables.SCORE > Variables.HIGH) {
                        Variables.HIGH = Variables.SCORE;
                        updateLeaderboard();
                    }
                } else {
                    Variables.LOSE = false;
                    Variables.newHoop = true;
                    ball.setX(hoop.getX() - 14);
                    ball.setY(hoop.getY());
                    //int randX = (int) (250 * Math.random()) + 30;
                    int randX = 0;
                    if (Variables.HOOP_RIGHT_SIDE) {
                        randX = (int) (91 * Math.random()) + 30;
                        Variables.HOOP_RIGHT_SIDE = false;
                    } else {
                        randX = (int) (121 * Math.random()) + 150;
                        Variables.HOOP_RIGHT_SIDE = true;

                    }
                    int randY = (int) ((hoop.getY() - 30) * Math.random() + 10);
                    oldHoop = hoop;
                    hoop = new Hoop(randX, randY);
                    Variables.SCORE++;
                    if (Variables.SCORE > Variables.HIGH) {
                        Variables.HIGH = Variables.SCORE;
                        updateLeaderboard();
                    }
                }
                timer.stop();
            }
            repaint();
        } /*else {
            System.out.println(pts.get(pts.size() - 1).getY());
            if (pts.get(pts.size() - 1).getY() > 500) {
                Variables.LOSE = true;
                System.out.println("true");
            }

            timer.stop();
        }*/
    }

    public void updateLeaderboard() {
        Leader user = new Leader(Variables.USER, Variables.SCORE);
        ArrayList<Leader> leaders = new ArrayList<>();
        boolean replace = false;
        for (int i = 0; i < 6; i++) {
            if (i == 5) {
                if (!replace) {
                    leaders.add(user);
                }
                break;
            }

            Leader old = Variables.LEADERS[i];
            System.out.println(old.getName() + " != " + user.getName());
            System.out.println(old.getName().equals(user.getName()));
            if (old.getName().equals(user.getName()) && !replace) {
                 if (user.getScore() > old.getScore()) {
                     leaders.add(user);
                     replace = true;
                 } else {
                     leaders.add(old);
                     replace = true;
                 }
                System.out.println("replace is " + replace);
            } else {
                leaders.add(old);
            }
        }

        Collections.sort(leaders);

        if (leaders.size() > 5) {
            leaders.remove(leaders.size() - 1);
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("src/com/sree/Leaderboard.txt").getAbsolutePath());
            for (int i = 0; i < 5; i++) {
                fileWriter.write(leaders.get(i).getName() + " - " + leaders.get(i).getScore() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void updateLeaderboard() {
        System.out.println(Variables.USER);
        Leader user = new Leader(Variables.USER, Variables.SCORE);
        Leader[] newLeaders = new Leader[5];
        boolean replaced = false;
        int indexCurrentLeaders = 0;
        for (int i = 0; i < newLeaders.length; i++) {
            if (user.getScore() > Variables.LEADERS[indexCurrentLeaders].getScore() && !replaced) {
                newLeaders[i] = user;
                replaced = true;
            } else {
                if (user.getName().equals(Variables.LEADERS[indexCurrentLeaders].getName())) {
                    indexCurrentLeaders++;
                }
                newLeaders[i] = Variables.LEADERS[indexCurrentLeaders];
                indexCurrentLeaders++;
            }
        }
        System.out.println(Arrays.toString(newLeaders));
        ArrayList<Leader> l = new ArrayList<>();

        Collections.sort(l);
        System.out.println(l);

        /*FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("src/com/sree/Leaderboard.txt").getAbsolutePath());
            for (int i = 0; i < 5; i++) {
                fileWriter.write(newLeaders[i].getName() + " - " + newLeaders[i].getScore() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

     */
}
