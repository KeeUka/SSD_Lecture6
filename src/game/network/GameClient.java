package game.network;

import java.io.IOException;
import java.util.Observable;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class GameClient extends Observable {

	private Client client;
	private String serverAddress = "127.0.0.1";
	private int serveerPort = 12345;
	private int timeout = 5000;

	public GameClient() {
		client = new Client();
		client.addListener(new NetworkListener());
	}

	public void send(Object object) {
		client.sendTCP(object);
	}

	public void connect() {
		client.start();
		Network.register(client);
		try {
			client.connect(timeout, serverAddress, serveerPort);
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
