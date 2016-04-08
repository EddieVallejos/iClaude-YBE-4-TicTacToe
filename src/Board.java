import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Board extends Thread{
	public static final int NUM_OF_TILES = 9;
	public static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
	
	private static boolean move;
	private static int currentPlayer;
	private int currentRound;
	private int rounds;
	private int p1wins, p2wins;

	private String p1, p2;
	
	private Container cont;
	private JFrame frame;
	private static JPanel board, playing, status;
	private Tile[] tiles = new Tile[Board.NUM_OF_TILES];
	
	/*CONSTRUCTOR*/
	public Board(int rounds, String p1, String p2){
		this.rounds = rounds;
		this.currentRound = 0;
		
		this.p1 = p1;
		this.p2 = p2;
		
		this.p1wins = 0;
		this.p2wins = 0;
		
		this.generateTiles();
		this.createGameWindow();
	}
	
	/*METHODS*/
	
	/* Creates game window*/
	private void createGameWindow(){
		if(this.currentRound%2 == 0) 
			Board.currentPlayer = 1;
		else
			Board.currentPlayer = 2;
		this.frame = new JFrame("Tic-Tac-Toe");
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addContents();
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
	
	/* Adds contents to game window*/
	private void addContents(){
		JPanel panel = new JPanel(new BorderLayout());
		this.cont = this.frame.getContentPane();
		//this.cont.setBackground(new Color(248, 68, 49));
		
		this.createHeader();
		this.createGameBoard();
		this.createStatus();
		
		
		panel.add(Board.playing, BorderLayout.PAGE_START);
		panel.add(Board.status, BorderLayout.PAGE_END);
		
		panel.add(Board.createFiller(100, 300), BorderLayout.LINE_START);
		panel.add(Board.createFiller(100, 300), BorderLayout.LINE_END);

		panel.add(board, BorderLayout.CENTER);
		cont.add(panel);
	}
	
	/* Generates tiles*/
	private void generateTiles(){
		for(int i = 0; i < Board.NUM_OF_TILES; i++){
			this.tiles[i] = new Tile(i, this);
		}
	}
	
	/* Creates header depending on the current player */
	private void createHeader(){
		Board.playing = new JPanel();
		if(currentPlayer == 1) Board.playing.add(new JLabel(p1));
		else Board.playing.add(new JLabel(p2));
		Board.playing.setBackground(Board.BACKGROUND_COLOR);
	}
	
	/* Creates status that displays current round and player scores */
	private void createStatus(){
		Board.status = new JPanel();
		Board.status.add(new JLabel(p1 + "(P1):" + Integer.toString(this.p1wins)));
		Board.status.add(new JLabel("  " + "Game " + Integer.toString(currentRound + 1)+ " of "+ Integer.toString(rounds) + "  "));
		Board.status.add(new JLabel(p2 + "(P2):" + Integer.toString(this.p2wins)));
		Board.status.setBackground(Board.BACKGROUND_COLOR);
	}
	
	/* Creates a 3X3 grid of tiles */
	private void createGameBoard(){
		Board.board = new JPanel(new GridLayout(3,3,10,10));
		Board.board.setBackground(Board.BACKGROUND_COLOR);
		this.generateTiles();
		for(int i = 0; i < Board.NUM_OF_TILES; i++){
			Board.board.add(tiles[i]);
		}
	}
	
	/* Creates filler panels */
	public static JPanel createFiller(int x, int y){
		JPanel filler = new JPanel();
		filler.setPreferredSize(new Dimension(x, y));
		filler.setBackground(Board.BACKGROUND_COLOR);
		
		return filler;
	}
	
	
	/* Resets game window */
	public void reset(){
		frame.setVisible(false);
		frame.dispose();
		this.createGameWindow(); 
		Checker.reset();
	}
	
	/* Updates status panel*/
	
	public void update(String p1, String p2){
		String player;
		if(Board.currentPlayer == 1) player = p1;
		else player = p2;
		JLabel play = (JLabel)Board.playing.getComponent(0);
		play.setText(player);
		Board.playing.add(play);
	}

	@Override
	public void run(){
		Boolean isDone = false;
		Board.move = false;
		do{
			try{
				Thread.sleep(10);
			}catch(Exception e){}
			isDone = this.checkBoard();
		
			if(isDone){   // checks if game is done
				if(!Checker.getIsWon()){  
					this.addScore(1);
					this.addScore(2);
				}
				else this.addScore(Checker.getWhoWon());
				this.nextRound();
				this.reset();
			}
		}while(!(this.isLastRound()));
		this.frame.dispose();
		int playAgain = this.createWinnerPrompt();
		if(playAgain == 0){
			World.newGame();
		} else System.exit(0);
	}

	public synchronized boolean checkBoard(){
		Boolean isDone = false;
		
		if(Checker.getIsWon()) return true;
		for(int i = 0; i < Board.NUM_OF_TILES; i++){
			if(this.tiles[i].getValue() == 0) return false;
			else isDone = true;
		}
		return isDone;
	}
	
	/* Creates window that displays winner and gives option to start a new game */
	private int createWinnerPrompt(){
		String winner, message;
		Object[] option = {
				"New Game",
				"Exit"
			};
		
		winner = this.getWinner();
		if(winner == null){
			message = "It's a tie!";
		}else{
			message = winner + " won!";
		}
		
		return JOptionPane.showOptionDialog(null, message, "Congratulations" , JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
	}
		
	
	/*GETTERS*/
	public static int getCurrentPlayer(){
		return Board.currentPlayer;
	}

	public Tile[] getTiles(){
		return this.tiles;
	}
	
	public boolean isLastRound(){
		if(currentRound == rounds) return true;
		else return false;
	}
	
	public static boolean getMove(){
		return Board.move;
	}

	public String getWinner(){
		if(this.p1wins == this.p2wins) return null;
		return this.p1wins>this.p2wins? p1:p2;
	}
	public String getPlayer1(){
		return this.p1;
	}
	public String getPlayer2(){
		return this.p2;
	}
	
	/*SETTERS*/
	public static void changeCurrentPlayer(){
		if(Board.currentPlayer == 1) Board.currentPlayer = 2;
		else Board.currentPlayer = 1;
	}
	
	public static void isMoving(){
		Board.move = true;
	}

	public static void isDoneMoving(){
		Board.move = false;
	}

	private void addScore(int player){
		if(player == 1) this.p1wins += 1;
		else if(player == 2) this.p2wins += 1;
	}
	
	private void nextRound(){
		this.currentRound += 1;
	}
}
