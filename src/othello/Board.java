package othello;

public class Board {

    private final int boardSize = 8;

    private char[][] board;

    private char p1Symbol;

    private int count;

    private static final char EMPTY = ' ';

    public Board(char p1Symbol){
        board = new char[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                board[i][j] = EMPTY;
            }
        }
        //Currently the board assumes a 2 player game with fixed symbols
        this.p1Symbol = p1Symbol;
    }

}
