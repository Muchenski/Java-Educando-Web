package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Queen extends ChessPiece {
	public Queen() {

	}

	public Queen(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position auxPosition = new Position(0, 0);

		// norte
		auxPosition.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setRow(auxPosition.getRow() - 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// sul
		auxPosition.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setRow(auxPosition.getRow() + 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// esquerda
		auxPosition.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setColumn(auxPosition.getColumn() - 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// direita
		auxPosition.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setColumn(auxPosition.getColumn() + 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// noroeste
		auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn() - 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// noroeste
		auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn() + 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// sudoeste
		auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() - 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		// sudeste
		auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() + 1);
		}
		if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
			mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}

		return mat;
	}

	@Override
	public String toString() {
		return "Q";
	}
}
