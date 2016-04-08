import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class Tile extends JPanel implements MouseListener{
	public static final Color IDLE_TILE = new Color(255, 221, 70);
	public static final Color HOVER_TILE = new Color(255, 228, 104);
	public static final Color CLICKED_TILE = new Color(255, 215, 34);
	public static final Color SELECTED_TILE = new Color(137, 96, 207);
	
	public static final int TILE_SIZE = 100;
	private int value, index;			//value is 1 if tile is "O", value is 2 if tile is "X"
	private Board board;
	
	public Tile(int index, Board board){
		this.value = 0;
		this.index = index;
		this.board = board;
		this.setLayout(new BorderLayout());
		this.setBackground(Tile.IDLE_TILE);
		this.setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
		
		addMouseListener(this);
	}
	
	/*METHODS*/
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) { //tile changes color when hovered
		if(value == 0) this.setBackground(Tile.HOVER_TILE);
	}

	@Override
	public void mouseExited(MouseEvent e) { //tile changes to original color 
		if(value == 0) this.setBackground(Tile.IDLE_TILE);
	}

	@Override
	public void mousePressed(MouseEvent e) { //tile changes color when clicked
		if(value == 0) this.setBackground(Tile.CLICKED_TILE);
	}
	@Override
	public void mouseReleased(MouseEvent e) { // Displays X or O if tile is empty
		JLabel label;
		if(value == 0){
			value = Board.getCurrentPlayer();
			//this.repaint();
			if(Board.getCurrentPlayer() == 1) label = new JLabel("O", SwingConstants.CENTER);
			else label = new JLabel("X", SwingConstants.CENTER);
			label.setFont(new Font("Arial", Font.BOLD, 30));
			this.add(label, BorderLayout.CENTER);
			Board.isMoving();
			Board.changeCurrentPlayer();
		}
		this.setBackground(Tile.SELECTED_TILE);
		board.update(board.getPlayer1(), board.getPlayer2());
	}
	
	/*GETTERS*/

	public int getValue(){
		return this.value;
	}
	public int getIndex(){
		return this.index;
	}
	
}
