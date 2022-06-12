package com.sree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {
    public Menu( Color c) {
        setBackground( c );
        setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        setOpaque(false);
        setLayout(new FlowLayout());
        JButton leaderboards = new JButton("Leaderboards");
        leaderboards.setBounds(20, 300, 100, 100);
        this.add(leaderboards);

        JLabel instructions = new JLabel("Drag anywhere on the \nscreen to launch the ball \nlike a slingshot." +
                "\n The goal is to get the ball into as many baskets as possible.");
        instructions.setBounds(20, 20, 150, 100);

        this.add(instructions);
        //start.addActionListener(this);
    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ASD");
    }
}
