package hnefatafl;
import javax.swing.JOptionPane;
/**
 * Main game class
 *
 * @author Stef, Mika, Lowie
 */
public class Hnefatafl {

    private Board board;
    private WhitePlayer whitePlayer;
    private BlackPlayer blackPlayer;
    private Player currentPlayer;
    private boolean showTimer;


    /**
     * Constructor for Hnefatafl (game)
     */
    public Hnefatafl() {
        this.startup();
    }

    public void startup(){
        this.board = new Board();
        this.whitePlayer = new WhitePlayer(board);
        this.blackPlayer = new BlackPlayer(board);
        this.currentPlayer = whitePlayer;
        this.showTimer = true;
    }

    //getters
    /**
     * Getter for the board that belongs to this game
     *
     * @return The board
     */
    public boolean isTimerOn(){
        return showTimer;
    }
    private void turnTimerOf(){
        showTimer=false;
    }
    
    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public WhitePlayer getWhitePlayer() {
        return whitePlayer;
    }

    public BlackPlayer getBlackPlayer() {
        return blackPlayer;
    }

    //other methods
    public boolean selectPieceOn(int row, int column) {
        if (board.getPieceOn(row, column) != null && board.getPieceOn(row, column).getColor() == currentPlayer.getColor()) {
            return board.selectPieceOn(row, column);
        } else {
            return false;
        }
    }

    public boolean moveSelectedPieceTo(int row, int column) {
        return board.moveSelectedPieceTo(row, column);
    }

    public void killCapturedPieces() {
        board.killCapturedPieces(currentPlayer.getColor());
    }

    private void setBarriers() {
        board.setBarriers();
    }

    public void updateBoard() {
        killCapturedPieces();
        setBarriers();
    }

    public void endTurn() {
        if (currentPlayer instanceof WhitePlayer) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

    public boolean isGameFinished() {
        //System.out.println("checked for gamewon");
        if (blackPlayer.isAlive() == false || board.isWhiteKingOnCorner() == true) {
            System.out.println("This Game has ended: White player wins");
            board.fillWithPieces(Color.WHITE);
            turnTimerOf();
            JOptionPane.showMessageDialog(null,"White Player wins!!!");
            return true;
        } else if (whitePlayer.isAlive() == false) {
            System.out.println("This Game has ended: Black player wins");
            board.fillWithPieces(Color.BLACK);
            turnTimerOf();
            JOptionPane.showMessageDialog(null, "Black Player wins!!!");
            return true;
        }
        return false;
    }
}
