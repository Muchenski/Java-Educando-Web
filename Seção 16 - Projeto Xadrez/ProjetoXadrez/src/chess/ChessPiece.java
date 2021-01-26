package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;

public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece() {
	}

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	protected boolean isThereAnOpponentPiece(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().getPiece(position);
		return piece != null && piece.getColor() != this.color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
}
