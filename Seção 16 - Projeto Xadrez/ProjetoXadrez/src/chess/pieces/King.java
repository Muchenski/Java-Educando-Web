package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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
