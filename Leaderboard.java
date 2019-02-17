package dabblegui;

import java.util.*;
import java.io.*;

class Player {      //basically a nice way to store a username and their score
    String username;
    int score;
    
    public Player(String username, int score) {     //constructor
        this.username = username;
        this.score = score;
    }
}

public class Leaderboard {
    static ArrayList<Player> scores = new ArrayList<>(10);     //stores all the players and their scores
    static Scanner scanner = new Scanner(System.in);
    
    public static void load() throws FileNotFoundException {        //populates the scores
        String leaderboardFile = "leaderboard.txt";      //this file only has 10 lines, each line has a name followed by their score, separated by a single space
        File file = new File(leaderboardFile);
        
        Scanner fileScanner = new Scanner(file);
        for(int i = 0; i < 9; i++) {
            String line = fileScanner.nextLine();           //reads the current line
            String[] sentence = line.split(" ");            
            scores.add(i, new Player(sentence[0], Integer.parseInt(sentence[1])));     //the first element in the array is the username, the second element is their score
        }
    }
    
    public static void update(String name, int score) {     //updates the current leaderboard after inserting most recent score
        int rank = 1;
        
        if(name.length() > 10) {
            name = name.substring(0, 10);       //shorten all names to <= 10 letters
        }
        
        for(int i = 0; i < 9; i++) {
            if(score <= scores.get(i).score) {       //increment rank if currentScore is lower than savedScore
                rank++;
            }
        }                   //note: rank will be 11 if lower than all high scores
        
        scores.add(rank - 1, new Player(name, score));      //rank is 1-indexed, while ArrayList is 0-indexed, so must subtract 1
        scores.remove(10);              //removes the player at rank 11
    }
    
    public static void save() throws IOException {     //overwrites the new scores back into the file
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), "utf-8"))) {
           for(int i = 0; i < 9; i++) {
            writer.write(scores.get(i).username + " " + scores.get(i).score);
            writer.newLine();
           }
        }
        catch (Exception e) {
            //do nothing
        }
    }
}