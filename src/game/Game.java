package game;

public class Game {

	public static final int BOARD_SIZE = 9;

	private Player p1;
	private Player p2;
	private Board board;

	private Player[] players;
	private int turn;

	public Game() {
		p1 = new Player("Player 1", "X");
		p2 = new Player("Player 2", "O");
		board = new Board(BOARD_SIZE);
	}

	public void start() {
		players = new Player[] { p1, p2 };
		turn = 0;
	}

	public boolean isEnd() {
		return getWinner() != null;
	}

	public void nextTurn() {
		turn = (turn + 1) % players.length;
	}

	public boolean isP1Turn() {
		return turn % 2 == 0;
	}

	public boolean isP2Turn() {
		return turn % 2 != 0;
	}

	public String getCurrentPlayerName() {
		return players[turn].getName();
	}

	public void currentPlayerTakesAction(int row, int col) {
		boolean success = players[turn].placeSymbol(board, row, col);
		if (success) {
			nextTurn();
		}
	}

	public int getBoardSize() {
		return board.getSize();
	}

	public String getSymbolOnBoard(int row, int col) {
		Symbol symbol = board.getSquare(row, col).getSymbol();
		return (symbol != null) ? symbol.getValue() : null;
	}

	public Player getWinner() {
		String[] rows = rows();
		String[] cols = cols();
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			String s = p.getSymbolValue();
			String winCondition = String.format("%s%s%s%s%s", s, s, s, s, s);
			for (int r = 0; r < rows.length; r++) {
				if (rows[r].contains(winCondition)) {
					return p;
				}
			}
			for (int c = 0; c < cols.length; c++) {
				if (cols[c].contains(winCondition)) {
					return p;
				}
			}
		}
		return null;
	}

	public String getWinnerName() {
		return getWinner().getName();
	}

	public String[] rows() {
		int size = board.getSize();
		String[] lines = new String[size];
		for (int i = 0; i < size; i++) {
			for (int c = 0; c < size; c++) {
				lines[i] += board.getSquare(i, c);
			}
		}
		return lines;
	}

	public String[] cols() {
		int size = board.getSize();
		String[] lines = new String[size];
		for (int i = 0; i < size; i++) {
			for (int c = 0; c < size; c++) {
				lines[i] += board.getSquare(c, i);
			}
		}
		return lines;
	}

}
