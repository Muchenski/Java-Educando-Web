package boardgame;

public class Piece {

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

}
