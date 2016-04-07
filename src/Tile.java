import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
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
		this.setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
		this.setBackground(Tile.IDLE_TILE);
		
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(value == 0) this.setBackground(Tile.HOVER_TILE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(value == 0) this.setBackground(Tile.IDLE_TILE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(value == 0) this.setBackground(Tile.CLICKED_TILE);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//Board.setCurrentTile(this.getIndex());
		if(value == 0){
			value = Board.getCurrentPlayer();
			this.repaint();
			Board.changeCurrentPlayer();
		}
		this.setBackground(Tile.SELECTED_TILE);
		board.update();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 30)); 
        if(value == 1)g.drawString("O", 38, 60);
        else if(value == 2) g.drawString("X", 38, 60);
        else return;
    }

	public int getValue(){
		return this.value;
	}
	public int getIndex(){
		return this.index;
	}
	
}
