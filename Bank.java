package dabblegui;

public class Bank {
    private static Tile[] tiles;

    public Bank(){
        tiles = new Tile[20];
    }
    
    public static void add(Tile t){
        tiles[t.getPosition()] = t;         //each tile has a position (passed when the method is called)
    }
    
    public static char getValue(int l) {            //returns the value of the Tile at the location
        return tiles[l].getValue();
    }
    
    public static Tile remove(int l){           //a Tile must be returned (not void) so the values can be kept
        Tile t = tiles[l];
        tiles[l] = null;
        return t;
    }
    
    public static boolean isTile(int p){            //checks if there is a tile at that location
        if(tiles[p] == null){
            return false;
        }
        else{
            return true;
        }
    }
}