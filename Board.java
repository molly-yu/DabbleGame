package dabblegui;

public class Board{
    private Tile [] tiles;

    public Board(int n) {	// n is number of tiles in board (i.e. 2, 3, 4, 5, 6)
        tiles = new Tile[n];
    }
    
    public void add(Tile t){
        tiles[t.getPosition()] = t;     //each tile has a position (passed when the method is called)
    }
    
    public Tile remove(int l) {
        Tile t = tiles[l];
        tiles[l] = null;
        return t;                   //a Tile must be returned (not void) so the values can be kept
    }
    
    public boolean isTile(int p) {      //checks if there is a tile at that location
        if(tiles[p] == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public char getValue(int l) {           //returns the value of the Tile at the location
        return tiles[l].getValue();
    }
}