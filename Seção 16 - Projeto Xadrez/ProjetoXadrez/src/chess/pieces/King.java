package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class King extends ChessPiece {

	public King() {

	}

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}

	@Override
	public String toString() {
		return "K";
	}

}
