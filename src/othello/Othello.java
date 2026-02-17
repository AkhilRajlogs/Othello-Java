package othello;

public class Othello {

    private Board board;
    private char currentPlayer;

    public Othello(char p1Symbol, char p2Symbol) {
        this.board = new Board(p1Symbol, p2Symbol);
        this.currentPlayer = p1Symbol;
    }

}