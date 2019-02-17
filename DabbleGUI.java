package dabblegui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class Tile {
    private char value;
    private int position;

    //constructs a tile with parameters v and p
    public Tile(char v, int p) {
        value = v;
        position = p;
    }
    
    public char getValue() {        //getters
        return value;
    }
    
    public int getPosition() {
        return position;
    }
}

public class DabbleGUI{
    public static TitleScreen frame;
    
    public static void main (String[] args){
        frame = new TitleScreen("Dabble");
        frame.setBounds(100, 100, 500, 680);
        frame.pack();
        frame.setVisible(true);   
    }
    
    public static String scramble (String[] words) {        //method to scramble words
        Random ran = new Random ();
        String unscrambled = words[0] + words[1] + words[2] + words[3] + words[4];      //builds a continuous string of the words from array
        String scrambled = ""; 
        int switch1 = 0, switch2 = 0, switch3;  
        char p1, p2; 

        for (int i = 1; i <= 100; i++) {        //scrambles 20 times
            switch1 = ran.nextInt(19) + 1; 
            switch2 = ran.nextInt(18) + 1; 

            if (switch1 > switch2) {        //ensures that the first index is less than the second index
                switch3 = switch1; 
                switch1 = switch2; 
                switch2 = switch3; 
            }

            if (switch1 == switch2) {       //ensures that the indexes are not the same
                switch2 = switch2 + 1;
            }

            p1 = unscrambled.charAt(switch1);       //finds corresponding char values at the given indexes
            p2 = unscrambled.charAt(switch2);
            
            unscrambled = unscrambled.substring(0, switch1) + p2  + unscrambled.substring(switch1 + 1, switch2) + p1 + unscrambled.substring(switch2 + 1); 
            scrambled = unscrambled;    //swaps the two values and does this several times to mix up the output
        }
        return scrambled; 
    }
    
    public static int computeScore (String [] input) throws FileNotFoundException {
        Dictionary.init();  //initialize dictionary
        String words; 
        int score = 0; 
        for (int count = 0; count < input.length; count++) {        //checks every word in the array inputted
            words = input[count]; 
            if (Dictionary.exists (words) && words.length () - 2 == count) {        ////makes sure that the first word is 2-letters, second word is 3 letters, etc.
                if (words.length () == 2) {     //score is added accordingly
                    score = score + 2; 
                }
                else if (words.length () == 3) {
                    score = score + 4; 
                }
                else if (words.length () == 4) {
                    score = score + 7;  
                }
                else if (words.length () == 5) {
                    score = score + 11;  
                }
                else if (words.length() == 6) {
                    score = score + 16; 
                }
                else {
                    score = score + 0; 
                }
            }
        }
        return score;       //return total score
    }
}