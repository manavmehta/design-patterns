package entities;

import java.util.Arrays;

public class Board {
    public Piece[][] board;
    public int filledCells;
    public Board(int n) {
        board = new Piece[n][n];
        filledCells = 0;
    }
    public void printBoard(){
        for (var row: board){
            for (var col: row){
                if (col == null)
                    System.out.print("null, ");
                else
                    System.out.print(col.pieceType.toString() + ", ");
            }
            System.out.println();
        }
    }

    boolean isGameWon(Piece type){
        if (checkRows(type)){
            return true;
        }
        if (checkCols(type)){
            return true;
        }
        if (checkDiags(type)){
            return true;
        }
        return false;
    }
    boolean checkRows(Piece type) {
        for (var row: board){
            var rowWon = true;
            for (var cell: row){
                if (cell == null || cell != type){
                    rowWon = false;
                }
            }
            if (rowWon) return true;
        }
        return false;
    }

    boolean checkCols(Piece type) {
        for (var j=0; j<board.length; j++){
            var colWon = true;
            for (var i=0; i<board.length; i++){
                if (board[i][j] == null || board[i][j] != type){
                    colWon = false;
                }
            }
            if (colWon) return true;
        }
        return false;
    }

    boolean checkDiags(Piece type) {
        var diagWon = true;
        for (var i=0; i<board.length; i++){
            if (board[i][i] == null || board[i][i] != type){
                diagWon = false;
            }
        }
        if (diagWon) return true;

        diagWon = true;
        for (var i=board.length-1; i>=0; i--){
            if (board[i][board.length-1-i] == null || board[i][board.length-1-i] != type){
                diagWon = false;
            }
        }
        return diagWon;
    }
}
