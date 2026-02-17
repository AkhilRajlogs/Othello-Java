package othello;

public class Board {

    private static final int BOARD_SIZE = 8;

    private char[][] board;

    private char p1Symbol, p2Symbol;

    private int count; // Will be used later

    private static final char EMPTY = ' ';

    public Board(char p1Symbol, char p2Symbol) {

        board = new char[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }

        // Currently assumes a 2-player game with fixed symbols
        this.p1Symbol = p1Symbol;
        this.p2Symbol = p2Symbol;

        initializeBoard();
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
        System.out.println();
    }


    public boolean isValidMove(int row, int column, char symbol) {
        // A valid move in Othello is one which can turn opponent pieces to own symbol 

        // Symbol check
        if (!isValidSymbol(symbol)) {
            return false;
        }
        
        // Boundary check 
        if (!isInBounds(row, column)) {
            return false;
        }

        // Empty check 
        if (board[row][column] != EMPTY) {
            return false;
        }

        // Direction loop 
        for (int i = 0; i < DIRECTIONS.length; i++) {
            int rowStep = DIRECTIONS[i][0];
            int colStep = DIRECTIONS[i][1];

            if (checkDirection(row, column, rowStep, colStep, symbol)) {
                return true;
            }
        }
        
        return false;
    }

    public boolean makeMove(int row, int column, char symbol) {

        if (!isValidMove(row, column, symbol)) {
            return false;            
        }

        board[row][column] = symbol;
        for(int i = 0; i < DIRECTIONS.length; i++) {
            int rowStep = DIRECTIONS[i][0];
            int colStep = DIRECTIONS[i][1];

            if (checkDirection(row, column, rowStep, colStep, symbol)) {
                flipInDirection(row, column, rowStep, colStep, symbol);
            }
        }
        return true;
    }

    private void initializeBoard() {
        // Othello game starts with 2 pieces of each player in the centre diagonally 
        int mid = BOARD_SIZE / 2;
        board[mid - 1][mid - 1] = p2Symbol;
        board[mid - 1][mid] = p1Symbol;
        board[mid][mid - 1] = p1Symbol;
        board[mid][mid] = p2Symbol;
    }

    private boolean checkDirection(int row, int column, int rowStep, int colStep, char symbol) {

        char opponentSymbol = getOpponentSymbol(symbol);

        int currentRow = row + rowStep;
        int currentCol = column + colStep;

        // First found piece should be opponent's, not EMPTY, not OutOfBounds and not ownSymbol 
        if (!isInBounds(currentRow, currentCol) || board[currentRow][currentCol] != opponentSymbol) {
            return false;
        }

        // Move further in same direction 
        currentRow += rowStep;
        currentCol += colStep;

        while (isInBounds(currentRow, currentCol)) {

            if (board[currentRow][currentCol] == EMPTY) {
                return false;
            }

            if (board[currentRow][currentCol] == symbol) {
                return true;
            }

            currentRow += rowStep;
            currentCol += colStep;
        }
        return false;
    }
        
    private boolean isInBounds(int row, int column) {
        return row >= 0 && column >= 0 && row < BOARD_SIZE && column < BOARD_SIZE;
    }

    private void flipInDirection(int row, int column, int rowStep, int colStep, char symbol) {
        
        char opponentSymbol = getOpponentSymbol(symbol);
        int currentRow = row + rowStep;
        int currentCol = column + colStep;

        while (isInBounds(currentRow, currentCol) && board[currentRow][currentCol] == opponentSymbol) {
            board[currentRow][currentCol] = symbol;
            currentRow += rowStep;
            currentCol += colStep;
        }
    }

    private boolean isValidSymbol(char symbol) {
        return symbol == p1Symbol || symbol == p2Symbol;
    }

    private char getOpponentSymbol(char symbol) {
        return (symbol == p1Symbol) ? p2Symbol : p1Symbol;
    }



    private static final int[][] DIRECTIONS = {
        { 0, -1 }, { -1, -1 },
        { -1,  0 }, { -1,  1 },
        { 0,  1 }, {  1,  1 },
        { 1,  0 }, {  1, -1 }
    };

}