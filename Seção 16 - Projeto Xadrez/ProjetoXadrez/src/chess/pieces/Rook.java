package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Rook extends ChessPiece {

	public Rook() {

	}

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}

	@Override
	public String toString() {
		return "R";
	}
}
