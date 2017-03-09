package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Game;
import game.network.GameClient;
import game.network.GameServer;
import game.network.Network;

public class MultiPlayerGui extends JFrame implements Observer {

	public static final int WINDOW_SIZE = 300;

	private Game game;
	private GameServer gameServer;
	private GameClient gameClient;
	
	private boolean isServer;
	private boolean isClient;

	private JLabel infoText;
	private JPanel mainPanel;
	private JButton startServerButton;
	private JButton startClientButton;
	
	public MultiPlayerGui() {
		super("SSD - Tic Tac Toe Multiplayer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		initComponents();
	}

	public void start() {
		game = new Game();
		gameServer = new GameServer();
		gameServer.addObserver(this);
		gameClient = new GameClient();
		gameClient.addObserver(this);
		pack();
		setVisible(true);
	}

	public void startServer() {
		// TODO: Complete the logic here
	}

	public void startClient() {
		// TODO: Complete the logic here
	}

	private void initComponents() {
		setSize(WINDOW_SIZE, WINDOW_SIZE);
		mainPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				drawBackground(g);
				drawLines(g);
				drawSymbols(g);
			}
		};
		mainPanel.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
		mainPanel.addMouseListener(new MouseHandler());
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);

		infoText = new JLabel(" Welcome to Tic-Tac-Toe");
		infoText.setFont(new Font(infoText.getName(), Font.PLAIN, 20));
		add(infoText, BorderLayout.NORTH);

		add(new JPanel() {
			{
				setLayout(new FlowLayout(FlowLayout.CENTER));
				startServerButton = new JButton("Start Server");
				startServerButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						startServer();
					}
				});
				add(startServerButton);
				startClientButton = new JButton("Start Client");
				startClientButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						startClient();
					}
				});
				add(startClientButton);
			}
		}, BorderLayout.SOUTH);
	}

	private void drawBackground(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
	}

	private void drawLines(Graphics g) {
		int gap = squareSize();
		g.setColor(Color.black);
		for (int i = 0; i < Game.BOARD_SIZE; i++) {
			g.drawLine(0, i * gap, WINDOW_SIZE, i * gap);
			g.drawLine(i * gap, 0, i * gap, WINDOW_SIZE);
		}
	}

	private void drawSymbols(Graphics g) {
		int gap = squareSize();
		for (int row = 0; row < game.getBoardSize(); row++) {
			for (int col = 0; col < game.getBoardSize(); col++) {
				int y = row * gap + gap / 2;
				int x = col * gap + gap / 2;
				String symbol = game.getSymbolOnBoard(row, col);
				if (symbol != null) {
					g.drawString(symbol, x, y);
				}
			}
		}
	}

	private int squareSize() {
		return WINDOW_SIZE / Game.BOARD_SIZE;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO: Complete the logic here
	}

	public void refreshGui() {
		if((isServer && game.isP1Turn()) || (isClient && game.isP2Turn())) {
			infoText.setText("Your Turn");
		} else {
			infoText.setText("Your Opponent's Turn");
		}
		mainPanel.repaint();
		if(game.isEnd()) {
			JOptionPane.showMessageDialog(this, game.getWinnerName() + " Win!");
		}
	}
	
	private class MouseHandler extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			int row = e.getY() / squareSize();
			int col = e.getX() / squareSize();
			// TODO: Complete the logic here
		}
	}
	
	public static void main(String[] args) {
		MultiPlayerGui gui = new MultiPlayerGui();
		gui.start();
	}

}
