package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King() {

	}

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	private boolean testRookCastling(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().getPiece(position);
		return piece != null && piece instanceof Rook && piece.getColor().equals(getColor())
				&& piece.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position auxPosition = new Position(0, 0);

		// acima
		auxPosition.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// abaixo
		auxPosition.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// esquerda
		auxPosition.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// direita
		auxPosition.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// noroeste
		auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// nordeste
		auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// sudoeste
		auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// sudeste
		auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// #Movimento especial
		if (getMoveCount() == 0 && !chessMatch.isCheck()) {
			Position rookPosition1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(rookPosition1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}

			Position rookPosition2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(rookPosition2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null
						&& getBoard().getPiece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}
		return mat;
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().getPiece(position);
		return piece == null || piece.getColor() != this.getColor();
	}

	@Override
	public String toString() {
		return "K";
	}

}
