//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Container;
//import java.awt.Font;
//import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class Board{
	public static final int NUM_OF_TILES = 9;
	private static int currentPlayer;
	private static int currentTile;
	private int currentRound;
	private int rounds;
	private int p1wins, p2wins;

	private String p1, p2;
	
	private Container cont;
	private JFrame frame;
	private static JPanel playing, status;
	private Tile[] tiles;
	
//	private JButton[] tiles;
	
	public Board(int rounds, String p1, String p2){
		this.rounds = rounds;
		this.currentRound = 0;
		
		this.p1 = p1;
		this.p2 = p2;
		
		this.p1wins = 0;
		this.p2wins = 0;
		
		this.createNewBoard();
	}
	
	private void createNewBoard(){
		if(this.currentRound%2 == 0) 
			Board.currentPlayer = 1;
		else
			Board.currentPlayer = 2;
		
		System.out.println(Board.currentPlayer + " " + this.currentRound);
		this.frame = new JFrame("Tic-Tac-Toe");
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addContents();
		this.frame.pack();
		this.frame.setVisible(true);
	}
	
	private void addContents(){
		JPanel panel = new JPanel(new BorderLayout());
		this.cont = this.frame.getContentPane();
		//this.cont.setBackground(new Color(248, 68, 49));
		
		Board.playing = new JPanel();
		Board.playing.add(new JLabel(p1));
		Board.playing.setBackground(new Color(248, 68, 49));
		
		
		Board.status = new JPanel();
		Board.status.add(new JLabel(p1 + "(P1):" + Integer.toString(this.p1wins)));
		Board.status.add(new JLabel("       " + "Game " + Integer.toString(currentRound + 1)+ " of "+ Integer.toString(rounds) + "       "));
		Board.status.add(new JLabel(p2 + "(P2):" + Integer.toString(this.p2wins)));
		Board.status.setBackground(new Color(248, 68, 49));
		
		
		JPanel board = new JPanel(new GridLayout(3,3,10,10));
		board.setBackground(new Color(248, 68, 49));
		this.generateTiles();
		for(int i = 0; i < Board.NUM_OF_TILES; i++){
			board.add(tiles[i]);
		}

		JPanel filler1 = new JPanel();
		filler1.setBackground(new Color(248, 68, 49));
		JPanel filler2 = new JPanel();
		filler2.setBackground(new Color(248, 68, 49));
		
		panel.add(Board.playing, BorderLayout.PAGE_START);
		panel.add(Board.status, BorderLayout.PAGE_END);
		
		panel.add(filler1, BorderLayout.LINE_START);
		panel.add(filler2, BorderLayout.LINE_END);

		panel.add(board, BorderLayout.CENTER);
		cont.add(panel);
	}
	
	private void generateTiles(){
		this.tiles = new Tile[Board.NUM_OF_TILES];
		for(int i = 0; i < Board.NUM_OF_TILES; i++){
			this.tiles[i] = new Tile(i, this);
		}
	}
	
	public void reset(){
		frame.setVisible(false);
		frame.dispose();
		this.createNewBoard(); 
	}
	
	public void update(){
		Board.updateGameScreen(this.p1, this.p2);
	}
	
	public static void updateGameScreen(String p1, String p2){
		String player;
		if(Board.currentPlayer == 1) player = p1;
		else player = p2;
		System.out.println(player);
		JLabel play = (JLabel)Board.playing.getComponent(0);
		play.setText(player);
		Board.playing.add(play);
	}
	
	/*
	private JButton[] generateTiles(){
		tiles = new JButton[Board.NUM_OF_TILES];
		for(int i = 0; i < Board.NUM_OF_TILES; i++){
			tiles[i] = new JButton(" ");
			ActionListener al = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					if(tile[i]) return;
				}
			};
		}
		
		return tiles;
	}
	*/
	public static int getCurrentPlayer(){
		return Board.currentPlayer;
	}
	public static void setCurrentTile(int index){
		Board.currentTile = index;
	}
	public static void changeCurrentPlayer(){
		if(Board.currentPlayer == 1) Board.currentPlayer = 2;
		else Board.currentPlayer = 1;
	}
	private void addScore(int player){
		if(player == 1) this.p1wins += 1;
		else if(player == 2) this.p2wins += 1;
	}
	private void nextRound(){
		this.currentRound += 1;
	}
	public boolean isLastRound(){
		if(currentRound == rounds-1) return true;
		else return false;
	}
	public boolean checkValue(){
		boolean isDone = false;
		for(int i = 0; i < Board.NUM_OF_TILES; i++){
			if(tiles[i].getValue() != 0) isDone = true;
			else return false;
		}
		this.nextRound();
		this.reset();
		return isDone;
	}
}
