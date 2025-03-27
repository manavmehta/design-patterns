package entities;

import java.util.HashMap;
import java.util.Map;

public class Board {
    public Piece[][] board;
    public int filledCells;

    Map<PieceType, Map<Integer, Integer>> countByRowNumber;
    Map<PieceType, Map<Integer, Integer>> countByColNumber;
    Map<PieceType, Integer> countByDiagonal;
    Map<PieceType, Integer> countByAntiDiagonal;

    public Board(int n) {
        board = new Piece[n][n];
        countByRowNumber = new HashMap<>();
        countByColNumber = new HashMap<>();
        countByDiagonal = new HashMap<>();
        countByAntiDiagonal = new HashMap<>();
    }
    public void printBoard(){
        for (var row: board){
            for (var col: row){
                if (col == null)
                    System.out.print(". ");
                else
                    System.out.print(col.pieceType.toString() + " ");
            }
            System.out.println();
        }
    }

    boolean isGameWon(Piece type, int i, int j){
        if (checkRows(type, i, j)){
            return true;
        }
        if (checkCols(type, i, j)){
            return true;
        }
        return checkDiagonals(type, i, j);
    }
    boolean checkRows(Piece piece, int i, int j) {
        return (countByRowNumber.get(piece.pieceType).get(i).equals(board.length));
    }

    boolean checkCols(Piece piece, int i, int j) {
        return (countByColNumber.get(piece.pieceType).get(j).equals(board.length));
    }

    boolean checkDiagonals(Piece piece, int i, int j) {
        if ((i==j) && countByDiagonal.get(piece.pieceType).equals(board.length)) return true;
        return ((i + j) == (board.length - 1)) && countByAntiDiagonal.get(piece.pieceType).equals(board.length);
    }

    boolean addPiece(Piece piece, int i, int j){
        if (board[i][j]!=null){
            return false;
        }
        board[i][j] = piece;

        countByRowNumber.computeIfAbsent(piece.pieceType, _ -> new HashMap<>());
        countByRowNumber.get(piece.pieceType).put(i,
                countByRowNumber.get(piece.pieceType).getOrDefault(i, 0) + 1);

        countByColNumber.computeIfAbsent(piece.pieceType, _ -> new HashMap<>());
        countByColNumber.get(piece.pieceType).put(j,
                countByColNumber.get(piece.pieceType).getOrDefault(j, 0) + 1);

        countByDiagonal.computeIfAbsent(piece.pieceType, _ -> 0);

        if(i == j) {
            countByDiagonal.put(piece.pieceType, countByDiagonal.get(piece.pieceType) + 1);
        }

        countByAntiDiagonal.computeIfAbsent(piece.pieceType, _ -> 0);
        if(i + j == board.length - 1) {
            countByAntiDiagonal.put(piece.pieceType, countByAntiDiagonal.get(piece.pieceType) + 1);
        }

        filledCells++;
        return true;
    }

}
