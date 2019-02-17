package dabblegui;

import java.util.*;
import java.io.*;

public class Dictionary {
    
    static HashSet<String> hs = new HashSet<>();    //declares hashset for dictionary
    static ArrayList<String> twoletters = new ArrayList<>(10000);       //sets up arraylists to sort words by length
    static ArrayList<String> threeletters = new ArrayList<>(10000);
    static ArrayList<String> fourletters = new ArrayList<>(10000);
    static ArrayList<String> fiveletters = new ArrayList<>(10000);
    static ArrayList<String> sixletters = new ArrayList<>(10000);
    
    public static void init() throws FileNotFoundException {
        String word; 
        File dictionary = new File("words.txt");        //import dictionary text file
        
        Scanner sc = new Scanner(dictionary); 
        
        while (sc.hasNextLine()) {      //while there is another word in the dictionary file
            word = sc.nextLine();
            word = word.toUpperCase();      //convert to uppercase
            hs.add(word);           //add the word to the total dictionary
        
            if (word.length() == 2) {       //adds 2,3,4,5,6-letter words to their corresponding groups
                twoletters.add(word);  
            }
            else if (word.length () == 3) {
                threeletters.add(word); 
            }
            else if (word.length () == 4) {
                fourletters.add(word); 
            }
            else if (word.length () == 5) {
                fiveletters.add(word); 
            }
            else if (word.length () == 6) {
                sixletters.add(word); 
            }
        }
    }

    public static boolean exists (String testword){     //method to test if inputted word is valid
        if (hs.contains(testword)) {        //checks if the dictionary contains the word, and returns corresponding boolean value
            return true;
        }
        else 
            return false;
    }
    
    public static String[] random() {       //method to return 2, 3, 4, 5, 6 letter words to be scrambled and for the user to play with
        Random ran = new Random(); 
        String [] words = new String[5];      //declare string array to be returned
        
        words[0] = twoletters.get(ran.nextInt(twoletters.size()));    //gets one 2-letter word
        words[1] = threeletters.get(ran.nextInt(threeletters.size()));    //gets one 3-letter word
        words[2] = fourletters.get(ran.nextInt(fourletters.size()));  //gets one 4-letter word
        words[3] = fiveletters.get(ran.nextInt(fiveletters.size()));  //gets one 5-letter word
        words[4] = sixletters.get(ran.nextInt(sixletters.size()));    //gets one 6-letter word
        
       return words; 
    }
}
