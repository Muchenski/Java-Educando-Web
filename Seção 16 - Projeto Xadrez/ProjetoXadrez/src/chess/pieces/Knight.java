package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Knight extends ChessPiece {

	public Knight() {

	}

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position auxPosition = new Position(0, 0);

		auxPosition.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		auxPosition.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		auxPosition.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		auxPosition.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		auxPosition.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		auxPosition.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		auxPosition.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		auxPosition.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		return mat;
	}

	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().getPiece(position);
		return piece == null || piece.getColor() != this.getColor();
	}

	@Override
	public String toString() {
		return "N";
	}

}
