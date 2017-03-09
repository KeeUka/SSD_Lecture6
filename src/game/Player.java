package game;

public class Player {

	private String name;
	private String symbolValue;
	
	public Player() { /* Need this for serialization */ }	
	
	public Player(String name, String symbolValue) {
		super();
		this.name = name;
		this.symbolValue = symbolValue;
	}
	
	public String getName() {
		return name;
	}

	public String getSymbolValue() {
		return symbolValue;
	}
	
	public boolean placeSymbol(Board b, int row, int col) {
		return b.placeSymbol(new Symbol(symbolValue), row, col);
	}
	
}
