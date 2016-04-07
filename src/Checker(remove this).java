
public class Checker extends Thread{
	public static final int DIAGONAL = 1;
	public static final int VERTICAL = 2;
	public static final int HORIZONTAL = 3;
	private boolean isWon;
	private int mode;
	
	private Board board;
	
	public Checker(int mode, Board board){
		this.mode = mode;
		this.board = board;
		this.isWon = false;
	}
	
	@Override
	public void run() {
		switch(this.mode){
			case 1:	
					break;
			case 2:	
					break;
			case 3:	
					break;
		}
	}

	private boolean diagonalCheck(){
		Tile[] tiles = new Tile[Board.NUM_OF_TILES];
		//tiles (0, 4, 8) && (2, 4, 6)
		
		return false;
	}

	private boolean horizontalCheck(){
		Tile[] tiles = new Tile[Board.NUM_OF_TILES];
		//tiles (0, 1, 2) && (3, 4, 5) && (6, 7, 8)
		
		
		return false;
	}

	private boolean verticalCheck(){
		Tile[] tiles = new Tile[Board.NUM_OF_TILES];
		//tiles (0, 3, 6) && (1, 4, 7) && (2, 5, 8)
		
		return false;
	}
	
	public boolean getIsWon(){
		return this.isWon;
	}
}