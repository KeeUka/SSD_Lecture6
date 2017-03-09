package game.network;

import java.io.IOException;
import java.util.Observable;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import game.Game;

public class GameServer extends Observable {

	private Server server;
	private int port = 12345;

	public GameServer() {
		server = new Server();
		Network.register(server);
		server.addListener(new NetworkListener());
	}

	public void send(Object object) {
		server.sendToAllTCP(object);
	}

	public void start() {
		server.start();
		try {
			server.bind(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class NetworkListener extends Listener {

		public void received(Connection connection, Object object) {
			setChanged();
			notifyObservers(object);
		}

		public void connected(Connection arg0) {
			setChanged();
			notifyObservers(Network.CONNECT);
		}
	}

}
