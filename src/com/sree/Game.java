package com.sree;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
//import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Game implements ActionListener {
    private JFrame jf = new JFrame();
    private JPanel jp = new JPanel();
    private Board board = new Board( Color.WHITE );
    private JPanel menu = new JPanel();
    private javax.swing.Timer timer;
    private JLabel leadersLabel = new JLabel("");
    private JLabel leaderNameLabel = new JLabel();
    private JButton leaderNameSave = new JButton("Save");
    private JTextField leaderNameText = new JTextField();
    private JButton leaderboards = new JButton("Leaderboards");
    private String leaderboardString = "";

    private JButton selectFile = new JButton("Change Ball");
    private JLabel selectFileText = new JLabel();

    public Game(){
        jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jf.setTitle( "Basketball" );

        jp.setBackground( Color.WHITE );
        jp.setPreferredSize( new Dimension( 525, 500 ) );
        jp.setLayout( null );
        //jf.setResizable(false);
        board.setBounds( 0, 0, 300, 500 );
        menu.setBounds(300, 0, 200, 500);
        timer = new javax.swing.Timer( 25, null );
        timer.start();


        JLabel instructions = new JLabel();
        instructions.setText("<html><p style=\"width:100px\">Drag anywhere on the screen to launch the ball like a slingshot. <br/><br/>The goal is to get the ball into as many baskets as possible.</p></html>");
        leaderNameLabel.setText("<html><p style=\"width:100px\">Enter your name to save on the leaderboards</p></html>");
        selectFileText.setText("<html><p style=\"width:120px\">Feel free to upload your own png, jpg, jpeg files</p><html>");

        instructions.setBounds(320, 20, 150, 200);
        leaderNameLabel.setBounds(320, 220, 150, 40);
        leaderNameText.setBounds(320, 265, 120, 25);
        leaderNameSave.setBounds(450, 265, 70, 25);
        leaderboards.setBounds(320, 300, 150, 25);
        leadersLabel.setBounds(320, 290, 120, 150);
        selectFile.setBounds(320, 405, 120, 25);
        selectFileText.setBounds(320, 310, 200, 300);

        menu.setBackground(Color.WHITE);
        jp.add( board );
        jp.add(leaderboards);
        jp.add(instructions);
        jp.add(leadersLabel);
        jp.add(leaderNameLabel);
        jp.add(leaderNameText);
        jp.add(leaderNameSave);

        jp.add(selectFile);
        jp.add(selectFileText);



        jp.add(menu);
        //jp.moveToFront(menu);

        leaderNameSave.addActionListener(this);
        leaderboards.addActionListener(this);
        selectFile.addActionListener(this);

        jf.getContentPane().add( jp );
        jf.pack();

        readLeaderboards();

    }

    public void readLeaderboards() {
        leaderboardString = "";
        ArrayList<String> leadersString = new ArrayList<String>();
        try {
            leadersString = (ArrayList<String>) Files.readAllLines(Paths.get(new File("src/com/sree/Leaderboard.txt").getAbsolutePath()));
        } catch (IOException error) {
            error.printStackTrace();
        }

        for (int leader = 0; leader < 5; leader++) {
            String leaderString = leadersString.get(leader);
            String name = leaderString.substring(0, leaderString.indexOf(" -"));
            int score = Integer.parseInt(leaderString.substring(leaderString.indexOf("- ") + 2));
            leaderboardString += leaderString + "<br/>";
            Variables.LEADERS[leader] = new Leader(name, score);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource().equals( leaderNameSave ) ) {
            Variables.USER = leaderNameText.getText();
            System.out.println("saved");
        } else if (e.getSource().equals( leaderboards )) {
            readLeaderboards();
            leadersLabel.setText("<html><p style=\"width:100px\">" + leaderboardString + "</p></html>");
            //leadersLabel.setText("leaderboardString");
        } else if (e.getSource().equals( selectFile )) {
            String com = e.getActionCommand();
            if (com.equals("save")) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int r = j.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    System.out.println(j.getSelectedFile().getAbsolutePath());
                }
            } else {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Images only", "png", "jpg", "jpeg");
                j.addChoosableFileFilter(restrict);
                j.setAcceptAllFileFilterUsed(false);

                int r = j.showOpenDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    Variables.BALL.setImg(j.getSelectedFile().getAbsolutePath());
                    System.out.println(j.getSelectedFile().getAbsolutePath());

                }

            }
        }


    }

    public void display() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                jf.setVisible(true);
            }
        });
    }
}
