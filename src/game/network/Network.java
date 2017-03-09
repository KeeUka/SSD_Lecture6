package game.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import game.Board;
import game.Game;
import game.Player;
import game.Square;
import game.Symbol;

public class Network {
	
	public static final int CONNECT = 1;
	
	// We need to register all classes we want to send over network
	public static void register(EndPoint endpoint) {
		Kryo k = endpoint.getKryo();
		k.register(Game.class);
		k.register(Player[].class);
		k.register(Player.class);
		k.register(Board.class);
		k.register(Square[][].class);
		k.register(Square[].class);
		k.register(Square.class);
		k.register(Symbol.class);
	}
}
