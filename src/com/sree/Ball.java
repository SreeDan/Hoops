package com.sree;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Ball {

    private int x;
    private int y;
    private BufferedImage img;
    //private String path;

    public Ball() {
        x = 20;
        y = 400;

        try {
            img = ImageIO.read(new File(new File("src/com/sree/ball.png").getAbsolutePath()));
        } catch (IOException ex) {
            System.out.println( ex );
        }
    }

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            img = ImageIO.read(new File("ball.png"));
        } catch (IOException ex) {
            System.out.println( ex );
        }
    }

    public void setImg(String path) {
        try {
            img = ImageIO.read(new File(path));
            //path = Variables.PATH;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public void move(int changeX, int changeY) {
        x += changeX;
        y += changeY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*public String getPath() {
        return path;
    }

     */
}
