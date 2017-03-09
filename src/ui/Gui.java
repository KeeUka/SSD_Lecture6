package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Game;

public class Gui extends JFrame {

	public static final int WINDOW_SIZE = 300;

	private Game game;
	private JLabel currentPlayerText;
	private JPanel mainPanel;

	public Gui() {
		super("SSD - Tic Tac Toe ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		initComponents();
	}

	public void start() {
		game = new Game();
		// TODO: Start the game
		pack();
		setVisible(true);
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
		currentPlayerText = new JLabel();
		add(currentPlayerText, BorderLayout.NORTH);
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
				if(symbol != null) {
					g.drawString(symbol, x, y);
				}
			}
		}
	}
	
	private int squareSize() {
		return WINDOW_SIZE / Game.BOARD_SIZE;
	}
	
	private void showGameOverMessgage() {
		JOptionPane.showMessageDialog(this, game.getWinnerName() + " Win!");
	}
	
	private class MouseHandler extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			int row = e.getY() / squareSize();
			int col = e.getX() / squareSize();
			// TODO: Complete the logic of the game here
		}	
	}
	
	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.start();
	}

}
