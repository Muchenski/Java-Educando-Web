package boardgame;

public abstract class Piece {

	// Posição da peça no tabuleiro.
	protected Position position;
	// A peça deve conhecer o tabuleiro que está.
	private Board board;

	public Piece() {

	}

	public Piece(Board board) {
		// Quando a peça for criada, ela ainda não deve
		// ser colocada no tabuleiro.
		// Então sua posição inicial é nula.
		this.position = null;
		this.board = board;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	protected Board getBoard() {
		return board;
	}

	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
