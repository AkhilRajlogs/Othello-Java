package othello;

public class Board {

    private final int boardSize = 8;

    private char[][] board;

    private char p1Symbol, p2Symbol;

    private int count;

    private static final char EMPTY = ' ';

    public Board(char p1Symbol, char p2Symbol){
        board = new char[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                board[i][j] = EMPTY;
            }
        }
        // Currently assumes a 2-player game with fixed symbols
        this.p1Symbol = p1Symbol;
        this.p2Symbol = p2Symbol;

        initializeBoard();
    }

    public void initializeBoard(){
        //othello game starts with 2 pieces of each player in the centre diagonally
        int mid = boardSize / 2;
        board[mid - 1][mid - 1] = p2Symbol;
        board[mid - 1][mid] = p1Symbol;
        board[mid][mid - 1] = p1Symbol;
        board[mid][mid] = p2Symbol;
    }

    public boolean isValidMove (int row, int column, char symbol) {
        // A valid move in Othello is one which can turn opponent pieces to own symbol
        return false; // to be implemented
    }

}
