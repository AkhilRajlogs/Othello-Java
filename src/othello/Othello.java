package othello;

public class Othello {

    private Board board;
    private char currentPlayer;
    private final char p1Symbol, p2Symbol;

    public Othello(char p1Symbol, char p2Symbol) {

        this.board = new Board(p1Symbol, p2Symbol);
        this.p1Symbol = p1Symbol;
        this.p2Symbol = p2Symbol;

        currentPlayer = p1Symbol;

    }

    public boolean playMove(int row, int column) {
        if (!board.makeMove(row, column, currentPlayer)) {
            return false;
        }
        switchPlayer();
        return true;
    }

    public boolean isGameOver() {
        return !board.hasValidMove(p1Symbol) && !board.hasValidMove(p2Symbol);
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == p1Symbol) ? p2Symbol : p1Symbol;
    }

}