package dabblegui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Game extends JFrame implements ActionListener{
    Board board2 = new Board(2);        //generate the boards
    Board board3 = new Board(3);
    Board board4 = new Board(4);
    Board board5 = new Board(5);
    Board board6 = new Board(6);
    Bank b = new Bank();        //initialize the bank
    Tile current;
    
    String name;        //the player's name
    int pScore;         //the player's score
    Panel bankR1, bankR2, bankR3, bankR4, bankR5, bankSide, boardSide, boardR1, boardR2, boardR3, boardR4, boardR5;
    JButton complete;       //these are used during score submission
    JTextField username;
    JFrame score;
    Color tileC = new Color(251, 185, 30);      //colours of tiles
    Color emptyC = new Color(180, 180, 182);
    Font font = new Font("Comic Sans MS", Font.BOLD, 24);        //this is the font we will be using for most text displayed
 
    //Create an array of Button type Objects
    JButton[] bank = new JButton[20];
    JButton[] board = new JButton[20];
    
    public Game(String title)throws FileNotFoundException {
        super(title);
        setBounds(100, 100, 600, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      //close this window only when X is clicked
        Dictionary.init();      //initialize dictionary
        String scrambled = DabbleGUI.scramble(Dictionary.random());     //creates a String of scrambled letters from 5 words, picked from the dictionary
        
        for(int i = 0; i < 20; i++) {
            Bank.add(new Tile(scrambled.charAt(i), i));     //create new tiles and add them to the bank, where they all start
        }
 
        bankR1 = new Panel();       //initializing stuff
        bankR2 = new Panel();
        bankR3 = new Panel();
        bankR4 = new Panel();
        bankR5 = new Panel();
        bankSide = new Panel();
        boardSide = new Panel();
        boardR1 = new Panel();
        boardR2 = new Panel();
        boardR3 = new Panel();
        boardR4 = new Panel();
        boardR5 = new Panel();
        complete = new JButton("Completed!");
        complete.addActionListener(this);
        complete.setActionCommand("complete");
 
        for(int i = 0; i < 20; i++) {           //instantiate every button object and set attributes
            bank[i] = new JButton("" + Bank.getValue(i));
            bank[i].setHorizontalAlignment(SwingConstants.CENTER);
            bank[i].setOpaque(true);
            bank[i].setFont(new Font(bank[i].getFont().getName(), bank[i].getFont().getStyle(), 20));
            bank[i].setBackground(tileC);
            bank[i].setPreferredSize(new Dimension(50,50));
            
            bank[i].addActionListener(this);
            bank[i].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            bank[i].setBorderPainted(false);
            bank[i].setFocusPainted(false);
            bank[i].setActionCommand("bank " + i);
            
            board[i] = new JButton("");
            board[i].setHorizontalAlignment(SwingConstants.CENTER);
            board[i].setOpaque(true);
            board[i].setFont(new Font(bank[i].getFont().getName(), bank[i].getFont().getStyle(), 20));
            board[i].setBackground(emptyC);
            board[i].setPreferredSize(new Dimension(56,56));
            
            board[i].addActionListener(this);
            board[i].setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            board[i].setBorderPainted(false);
            board[i].setFocusPainted(false);
                
            if(i <= 1) {                //setting ActionCommands for each board button- these numbers and locations are important
                board[i].setActionCommand("board " + i + " 1");
            }
            else if(i <= 4) {    
                board[i].setActionCommand("board " + i + " 2");
            }
            else if(i <= 8) {    
                board[i].setActionCommand("board " + i + " 3");
            }
            else if(i <= 13) {    
                board[i].setActionCommand("board " + i + " 4");
            }
            else if(i <= 19) {    
                board[i].setActionCommand("board " + i + " 5");
            }
        }
        
        setSize(920,450);       //set dimensions
        bankSide.setBounds(0,0,363,450);       //side of the tile bank
        bankSide.setBackground(new Color(155, 30,30));
        
        bankR1.setBounds(40, 60, 300, 71);
        bankR2.setBounds(40, 132, 300, 71);
        bankR3.setBounds(40, 204, 300, 71);
        bankR4.setBounds(40, 276, 300, 71);
        bankR5.setBounds(40, 348, 300, 71);
     
        boardSide.setBounds(380, 0, 200, 350);     //side with tile board
        boardR1.setBounds(400, 60, 300, 71);
        boardR2.setBounds(400, 152,300, 71);
        boardR3.setBounds(400, 204, 300, 71);
        boardR4.setBounds(400, 276, 300, 71);
        boardR5.setBounds(400, 348, 300, 71);
 
        // Add panels to respective frames and panels
        add(bankSide);
        add(boardSide);
        bankSide.add(Box.createRigidArea(new Dimension(500, 30)));    //create a blank space at the top
        bankSide.add(bankR1);
        bankSide.add(bankR2);
        bankSide.add(bankR3);
        bankSide.add(bankR4);
        bankSide.add(bankR5);
        boardSide.add(Box.createRigidArea(new Dimension(200, 10)));   //create a blank space at the top
        boardSide.add(boardR1);
        boardSide.add(boardR2);
        boardSide.add(boardR3);
        boardSide.add(boardR4);
        boardSide.add(boardR5);
        boardSide.add(Box.createRigidArea(new Dimension(350, 10)));
        boardSide.add(complete);
 
        for(int i = 0; i < 4; ++i) {        //Add all of the labels to the respective panels
            bankR1.add(bank[i]);
            bankR1.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        for(int i = 4; i < 8; ++i) {
            bankR2.add(bank[i]);
            bankR2.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        for(int i = 8; i < 12; ++i) {
            bankR3.add(bank[i]);
            bankR3.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        for(int i = 12; i < 16; ++i) {
            bankR4.add(bank[i]);
            bankR4.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        for(int i = 16; i < 20; ++i) {
            bankR5.add(bank[i]);
            bankR5.add(Box.createRigidArea(new Dimension(10, 10)));
        }
 
        boardR1.add(Box.createRigidArea(new Dimension(675, 10)));       //draw board tiles
        for(int i = 0; i < 2; ++i) {    
            boardR1.add(board[i]);
            boardR1.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        boardR1.add(Box.createRigidArea(new Dimension(300, 10)));
        
        boardR2.add(Box.createRigidArea(new Dimension(675, 10)));
        for(int i = 2; i < 5; ++i) {            
            boardR2.add(board[i]);
            boardR2.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        boardR2.add(Box.createRigidArea(new Dimension(300, 10)));
        
        boardR3.add(Box.createRigidArea(new Dimension(675, 10)));
        for(int i = 5; i < 9; ++i) {    
            boardR3.add(board[i]);
            boardR3.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        boardR3.add(Box.createRigidArea(new Dimension(300, 10)));
        
        boardR4.add(Box.createRigidArea(new Dimension(675, 10)));
        for(int i = 9; i < 15; ++i) {   
            boardR4.add(board[i]);
            boardR4.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        boardR4.add(Box.createRigidArea(new Dimension(300, 10)));
        
        boardR5.add(Box.createRigidArea(new Dimension(675, 10)));
        for(int i = 14; i < 20; ++i) {  
            boardR5.add(board[i]);
            boardR5.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        boardR5.add(Box.createRigidArea(new Dimension(300, 10)));
    }
    
    public void actionPerformed(ActionEvent evt) {
        String ev = evt.getActionCommand();
        String[] event = ev.split(" ");         //event[0] is the Board/Bank, event[1] is the location, event[2] is the board# (optional)
        
        if(event[0].equals("bank")) {       //if the action regarded the bank
            bankMovement(event);            
        }
        else if(event[0].equals("board")) {     //if the action regarded any of the boards
            boardMovement(event);           
        }
        else if (ev.equals("complete")) {       //Completed! button is clicked
            complete();     
        }
        else if (ev.equals("submit")) {         //Submit button (in completed) is clicked
            submit();
        }
    }
    
    public void bankMovement(String[] event) {
        int location = Integer.parseInt(event[1]);
        if(Bank.isTile(location) && current == null) {          //if there is a tile at that location AND there is no tile currently in hand
            current = Bank.remove(location);        //put the removed tile in hand
            bank[location].setText("");
            bank[location].setBackground(emptyC);
        }
        else {          //if there is no tile at that location
            if(current != null && !Bank.isTile(location)) {       //if there is a tile currently in hand
                Bank.add(new Tile(current.getValue(), location));      //add the tile in hand to the Bank
                bank[location].setText("" + current.getValue());        //draw the new tile
                bank[location].setBackground(tileC);
                current = null;         //remove the tile from hand
            }
        }
        bankR1.revalidate();    //redraw all tiles!
        bankR1.repaint();
        bankR2.revalidate();
        bankR2.repaint();
        bankR3.revalidate();
        bankR3.repaint();
        bankR4.revalidate();
        bankR4.repaint();
        bankR5.revalidate();
        bankR5.repaint();
    }

    public void boardMovement(String[] event) {
        int location = Integer.parseInt(event[1]);
        int nboard = Integer.parseInt(event[2]);
        switch(nboard) {
            case 1:
                if(board2.isTile(location) && current == null) {          //if there is a tile at that location AND there is no tile currently in hand
                    current = board2.remove(location);        //put the removed tile in hand
                    board[location].setText("");
                    board[location].setBackground(emptyC);
                    bankR1.revalidate();
                    bankR1.repaint();
                }
                else {          //ONLY if there is a tile currently in hand AND there is no tile at the location
                    if(current != null && !board2.isTile(location)) {
                        board2.add(new Tile(current.getValue(), location));      //add the tile in hand to the Bank
                        board[location].setText("" + current.getValue());        //draw the new tile
                        board[location].setBackground(tileC);
                        current = null;         //remove the tile from hand
                    }
                }
                break;              
            case 2:                 //basically, repeat everything but change the board and row # depending on "nboard"
                if(board3.isTile(location - 2) && current == null) {          //don't forget, board# is for the board itself while board is for the entire set of boards
                    current = board3.remove(location - 2);        
                    board[location].setText("");
                    board[location].setBackground(emptyC);
                    bankR2.revalidate();
                    bankR2.repaint();
                }
                else {          
                    if(current != null && !board3.isTile(location - 2)) {       
                        board3.add(new Tile(current.getValue(), location - 2));      
                        board[location].setText("" + current.getValue());        
                        board[location].setBackground(tileC);
                        current = null;         
                    }
                }
                break;
            case 3:
                if(board4.isTile(location - 5) && current == null) {
                    current = board4.remove(location - 5);
                    board[location].setText("");
                    board[location].setBackground(emptyC);
                    bankR3.revalidate();
                    bankR3.repaint();
                }
                else {          
                    if(current != null && !board4.isTile(location - 5)) {      
                        board4.add(new Tile(current.getValue(), location - 5));   
                        board[location].setText("" + current.getValue());      
                        board[location].setBackground(tileC);
                        current = null;        
                    }
                }
                break;
            case 4:
                if(board5.isTile(location - 9) && current == null) {       
                    current = board5.remove(location - 9);       
                    board[location].setText("");
                    board[location].setBackground(emptyC);
                    bankR4.revalidate();
                    bankR4.repaint();
                }
                else {         
                    if(current != null  && !board5.isTile(location - 9)) {      
                        board5.add(new Tile(current.getValue(), location - 9));   
                        board[location].setText("" + current.getValue());     
                        board[location].setBackground(tileC);
                        current = null;        
                    }
                }
                break;
            case 5:
                if(board6.isTile(location - 14) && current == null) {        
                    current = board6.remove(location - 14);      
                    board[location].setText("");
                    board[location].setBackground(emptyC);
                    bankR5.revalidate();
                    bankR5.repaint();
                }
                else {          //if there is no tile at that location
                    if(current != null  && !board6.isTile(location - 14)) {      
                        board6.add(new Tile(current.getValue(), location - 14));      
                        board[location].setText("" + current.getValue());       
                        board[location].setBackground(tileC);
                        current = null;        
                    }
                }
                break;
        }
    }

    public void complete() {
        try {
            Leaderboard.load();     //load leaderboard
        }
        catch(IOException e) {
            //do nothing
        }
        String[] words = new String[5];     //read the words off the boards
        words[0] = board[0].getText() + board[1].getText();
        words[1] = board[2].getText() + board[3].getText() + board[4].getText();
        words[2] = board[5].getText() + board[6].getText() + board[7].getText() + board[8].getText();
        words[3] = board[9].getText() + board[10].getText() + board[11].getText() + board[12].getText() + board[13].getText();
        words[4] = board[14].getText() + board[15].getText() + board[16].getText() + board[17].getText() + board[18].getText() + board[19].getText();
        
        JLabel enterName = new JLabel("Enter name:");       //get username
        JPanel bg = new JPanel();       //display stuff
        username = new JTextField();
        JButton submit = new JButton("Submit");
        Box layout = new Box(BoxLayout.Y_AXIS);
        Box layout2 = new Box(BoxLayout.X_AXIS);
        JLabel scoreSplash;
        
        submit.setActionCommand("submit");
        submit.addActionListener(this);
        
        score = new JFrame("Scoring");
        score.setLayout(new FlowLayout());
        try {
            pScore = DabbleGUI.computeScore(words);     //calculate total score
        }
        catch (FileNotFoundException e) {
            //do nothing
        }
        score.setBounds(100, 100, 500, 300);
        
        enterName.setFont(font);
        username.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        
        bg.setBackground(new Color(102, 158, 231));
        scoreSplash = new JLabel("Congratulations! Your score was " + pScore + "!");        //display
        
        scoreSplash.setFont(font);
        bg.add(layout);
        layout.add(Box.createRigidArea(new Dimension(0, 20)));      //add components to the frame
        layout.add(scoreSplash);
        layout.add(Box.createRigidArea(new Dimension(0, 70)));
        layout.add(enterName);
        layout.add(layout2);
        layout2.add(Box.createRigidArea(new Dimension(110, 0)));
        layout2.add(username);
        layout2.add(Box.createRigidArea(new Dimension(20, 0)));
        layout2.add(submit);
        layout2.add(Box.createRigidArea(new Dimension(20, 0)));
        layout.add(Box.createRigidArea(new Dimension(0, 20)));
        bg.add(Box.createRigidArea(new Dimension(130, 0)));
        score.add(bg);
        score.pack();
        
        this.dispose();         //close the game screen
        score.setVisible(true);     //display the score screen  
    }
    
    public void submit() {
        try {
            Leaderboard.load();
        }
        catch(FileNotFoundException e) {
            //do nothing
        }
        name = username.getText();
        Leaderboard.update(name, pScore);
        try {
            Leaderboard.save();
        }
        catch(IOException e) {
            //do nothing
        }
        score.dispose();
        TitleScreen.leaderboard();      //display leaderboard screen
    }
  
    public void keyPressed(KeyEvent e) {}   //unused methods that need to be declared
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}