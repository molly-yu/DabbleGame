package dabblegui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class TitleScreen extends JFrame implements ActionListener {
    BufferedImage img = null;
    Splash titleSplash;
    static String text;        //possible text to be displayed- if no text is to be drawn, text will be an empty string
    
    JButton startGame = new JButton("Start Game");      //declare buttons that will be shown on the title screen
    JButton tutorial = new JButton("How To Play");
    JButton credits = new JButton("About");
    JButton leaderboard = new JButton("Scores");
    
    Box layout = new Box(BoxLayout.Y_AXIS);
    Box buttons = new Box(BoxLayout.X_AXIS);
    static Font font = new Font("Comic Sans MS", Font.BOLD, 24);        //this is the font we will be using for most text displayed

    public TitleScreen(String title) {
        super(title);
        setLayout(new FlowLayout());
        setResizable(false);
        
        titleSplash = new Splash(377, 196);         //Size of the title Splash- also the size of the image
        
        try {
            img = ImageIO.read(new File("titlesplash.png"));       //read the image
        } 
        catch (IOException e) {
            //do nothing
        }
        
        startGame.addActionListener(this);          //add ActionListeners for all the buttons
        tutorial.addActionListener(this);
        credits.addActionListener(this);
        leaderboard.addActionListener(this);
        
        startGame.setFocusPainted(false);       //make the buttons look slightly nicer
        tutorial.setFocusPainted(false);
        credits.setFocusPainted(false);
        leaderboard.setFocusPainted(false);
        
        startGame.setActionCommand("start");        //define ActionCommands for all the buttons
        tutorial.setActionCommand("tutorial");
        credits.setActionCommand("credits");
        leaderboard.setActionCommand("leaderboard");
        
        buttons.add(startGame);             //add the buttons to the JPanel
        buttons.add(Box.createRigidArea(new Dimension(10, 1)));     //nice spacing between the buttons
        buttons.add(tutorial);
        buttons.add(Box.createRigidArea(new Dimension(10, 1)));
        buttons.add(credits);
        buttons.add(Box.createRigidArea(new Dimension(10, 1)));
        buttons.add(leaderboard);
        
        layout.add(titleSplash);        //add the title splash and buttons to layout
        layout.add(Box.createRigidArea(new Dimension(1, 10)));
        layout.add(buttons);
        add(layout);            //add the layout to the JFrame
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           //set the JFrame to close when the X is pressed
    }
  
    public void actionPerformed(ActionEvent evt){       //checks which button was pressed and executes the according method
        switch(evt.getActionCommand()) {
            case "start":
                Game game = null;
                try {
                    game = new Game("Dabble");
                } 
                catch (FileNotFoundException ex) {
                    //do nothing
                }
                game.setVisible(true);
                break;
            case "tutorial":    
                tutorial();
                break;
            case "credits":
                credits();
                break;
            case "leaderboard":    
                leaderboard();
                break;
        }
    }

    public void tutorial() {
        JFrame frame2 = new JFrame("How To Play");      //the main frame
        frame2.setResizable(false);
        frame2.setBounds(600, 300, 500, 1000); 
        text = "\n       How To Play\n\n" + 
        		"     You will be given 20 tiles in a letter bank. \t\n" + 
        		"     Your objective is to try to create a 2, 3, 4, 5, \t\n     and 6 letter word, using each tile only once. \t\n" + 
        		"     Click a tile to pick it up and click again to drop it.\t\n" + 
        		"     Your game score will correspond to the length \t\n     of your words. \t\n" + 
        		"     Good luck!\t\n";
        JPanel p = new JPanel();            //the panel that stores basically everything
        p.setOpaque(true);
        p.setBackground(new Color(1, 193, 142));
        JTextArea textArea = new JTextArea(5, 10);      //the text area that goes in the panel
        textArea.append(text);
        textArea.setBackground(Color.white);
        
        textArea.setFont(font);
        textArea.setBackground(new Color(155,210,185));
        textArea.setEditable(false);
       
        frame2.add(p);          //adding components to the frame
        p.add(textArea);
        frame2.repaint();
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);           //set THIS JFrame to close when the X is pressed- not tne entire program
        frame2.pack();
        frame2.setVisible(true);
    }
    
    public void credits() {
        JFrame frame3 = new JFrame("About");        //the main frame
        frame3.setResizable(false);
        frame3.setBounds(600, 300, 500, 180);
        
        text = "\n       About\n\n   This game was created by Molly, Andrew and Owen for\t\n   their ICS GUI project over the course of three weeks. \t\n" + 
        		"   This would not have been possible without the guidance\n   and help of Mr. Jay.\t\n\n" + 
        		"";               //text to be displayed
        JPanel p = new JPanel ();           //the panel that stores basically everything
        p.setOpaque(true);
        p.setBackground(new Color(185, 23, 230));
        JTextArea textArea = new JTextArea(5,10);       //the text area that goes in the panel
        textArea.append(text);
        textArea.setBackground(new Color(200, 160,230));
        textArea.setFont(font);
        textArea.setEditable(false);
        
        frame3.add(p);      //adding components to the frame
        p.add(textArea);
        frame3.repaint();
        frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);           //set THIS JFrame to close when the X is pressed- not the entire program
        frame3.pack();
        frame3.setVisible(true);
    }
    
    public static void leaderboard() {
        JFrame frame4 = new JFrame("Leaderboard");      //the main frame
        frame4.setResizable(false);
        try {
            Leaderboard.load();     //load the leaderboard
        }
        catch (Exception e) {
            //do nothing
        }
        frame4.setBounds(100, 100, 600, 1000);
        
        text = "\n       Leaderboard\r\n\n";              //text to be displayed
        for(int i = 0; i < 9; i++) {
            text += "     " + (i + 1) + ". " + Leaderboard.scores.get(i).username;
            if(Leaderboard.scores.get(i).username.length() < 8) {       //formatting to match length of username and align numbers
                text += "        \t";
            }
            text += Leaderboard.scores.get(i).score + "\t\n";
        }
        JPanel p = new JPanel ();       //the panel that contains the text area
        p.setOpaque(true);
        p.setBackground(new Color(253, 173, 12));
        JTextArea textArea = new JTextArea(5,10);       //the text area that has the text
        textArea.append(text);
        textArea.setBackground(new Color(251, 210, 141));
        textArea.setFont(font);
        textArea.setEditable(false);
        
        frame4.add(p);      //adding all components to the frame
        p.add(textArea);
        frame4.repaint();
        frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);           //set THIS JFrame to close when the X is pressed- not the entire program
        frame4.pack();
        frame4.setVisible(true);
    }
    
    class Splash extends JPanel {       //used for drawing the title image on the title screen
        public Splash (int width, int height) {
            this.setPreferredSize (new Dimension (width, height));
        }
        
        public void paintComponent(Graphics g) {   
            g.drawImage(img, 1, 1, this);
        }
    }
}