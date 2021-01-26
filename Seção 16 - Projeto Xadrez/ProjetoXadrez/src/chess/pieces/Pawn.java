package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position auxPosition = new Position(0, 0);

		if (getColor() == Color.WHITE) {

			auxPosition.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			}

			auxPosition.setValues(position.getRow() - 1, position.getColumn());
			Position auxPosition2 = new Position(position.getRow() - 2, position.getColumn());
			if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)
					&& getBoard().positionExists(auxPosition2) && !getBoard().thereIsAPiece(auxPosition2)
					&& getMoveCount() == 0) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
				mat[auxPosition2.getRow()][auxPosition2.getColumn()] = true;
			}

			auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			}

			auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			}
		} else {
			auxPosition.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			}

			auxPosition.setValues(position.getRow() + 1, position.getColumn());
			Position auxPosition2 = new Position(position.getRow() + 2, position.getColumn());
			if (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)
					&& getBoard().positionExists(auxPosition2) && !getBoard().thereIsAPiece(auxPosition2)
					&& getMoveCount() == 0) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
				mat[auxPosition2.getRow()][auxPosition2.getColumn()] = true;
			}

			auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			}

			auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(auxPosition) && isThereAnOpponentPiece(auxPosition)) {
				mat[auxPosition.getRow()][auxPosition.getColumn()] = true;
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
