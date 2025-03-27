package entities;

import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Game {
    Board board;
    Deque<Player> players = new ArrayDeque<>();
    public Game() {
        System.out.print("Enter board size: ");
        int size = new Scanner(System.in).nextInt();
        board = new Board(size);
        players.add(new Player(new PieceO()));
        players.add(new Player(new PieceX()));
        playGame();
    }

    public void playGame () {
        boolean gameEnded = false;
        while (!gameEnded) {
            board.printBoard();
            if (board.filledCells == board.board.length * board.board.length) {
                gameEnded = true;
                System.out.println("It's a draw!");
                break;
            }
            var playerOnTurn = players.pop();
            System.out.print("Make a move: ");
            int i = new Scanner(System.in).nextInt();
            int j = new Scanner(System.in).nextInt();
            if (!addPiece(playerOnTurn.piece, i, j)){
                System.out.println("Invalid move. Try again.");
                players.addFirst(playerOnTurn);
            }
            else {
                if (board.isGameWon(playerOnTurn.piece)) {
                    gameEnded = true;
                    System.out.println(playerOnTurn.piece.pieceType + " wins!");
                }
                players.addLast(playerOnTurn);
            }
        }
    }

    boolean addPiece(Piece piece, int i, int j){
        if (board.board[i][j]!=null){
            return false;
        }
        board.board[i][j] = piece;
        board.filledCells++;
        return true;
    }
}
