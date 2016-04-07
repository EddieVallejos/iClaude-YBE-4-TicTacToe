import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel implements MouseListener{
	public static final int TILE_SIZE = 100;
	private int value, index;
	private Board board;
	
	public Tile(int index, Board board){
		this.value = 0;
		this.index = index;
		this.board = board;
		this.setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
		this.setBackground(new Color(248, 248, 49));
		
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(value == 0) this.setBackground(new Color(248, 248, 100));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(value == 0) this.setBackground(new Color(248, 248, 49));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(value == 0) this.setBackground(new Color(208, 208, 20));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		Board.setCurrentTile(this.getIndex());
		if(value == 0){
			value = Board.getCurrentPlayer();
			this.repaint();
			Board.changeCurrentPlayer();
		}
		this.setBackground(new Color(45, 89, 164));
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
