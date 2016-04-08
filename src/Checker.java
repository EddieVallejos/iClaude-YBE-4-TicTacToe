public class Checker extends Thread{
	public static final int DIAGONAL = 1;
	public static final int VERTICAL = 2;
	public static final int HORIZONTAL = 3;
	private static boolean isWon = false;
	private static int whoWon = 0;
	private int mode;
	
	private Board board;
	
	public Checker(int mode, Board board){
		this.mode = mode;
		this.board = board;
	}
	
	@Override
	public void run() {
		while(!board.isLastRound()){
			if(!Board.getMove() && Checker.isWon) continue;
			switch(this.mode){
				case 1:	Checker.isWon = diagonalCheck();
						break;
				case 2:	Checker.isWon = verticalCheck();
						break;
				case 3:	Checker.isWon = horizontalCheck();
						break;
			}
			Board.isDoneMoving();
		}
	}

	private synchronized boolean diagonalCheck(){
		Tile[] tiles = board.getTiles();
		//tiles (0, 4, 8) && (2, 4, 6)
		if((tiles[0].getValue() == tiles[4].getValue() && tiles[4].getValue() == tiles[8].getValue() && tiles[0].getValue() != 0) ||
				(tiles[2].getValue() == tiles[4].getValue() && tiles[4].getValue() == tiles[6].getValue() && tiles[2].getValue() != 0)){
			Checker.whoWon = tiles[4].getValue();
			return true;
		}
		return false;
	}

	private synchronized boolean horizontalCheck(){
		Tile[] tiles = board.getTiles();
		//tiles (0, 1, 2) && (3, 4, 5) && (6, 7, 8)
		if((tiles[0].getValue() == tiles[1].getValue() && tiles[1].getValue() == tiles[2].getValue()  && tiles[0].getValue() != 0)){
			Checker.whoWon = tiles[0].getValue();
			return true;
		} else if((tiles[3].getValue() == tiles[4].getValue() && tiles[4].getValue() == tiles[5].getValue() && tiles[3].getValue() != 0)){
			Checker.whoWon = tiles[3].getValue();
			return true;
		} else if((tiles[6].getValue() == tiles[7].getValue() && tiles[7].getValue() == tiles[8].getValue() && tiles[6].getValue() != 0)){
			Checker.whoWon = tiles[6].getValue();
			return true;
		}
		
		return false;
	}

	private synchronized boolean verticalCheck(){
		Tile[] tiles = board.getTiles();
		//tiles (0, 3, 6) && (1, 4, 7) && (2, 5, 8)
		if((tiles[0].getValue() == tiles[3].getValue() && tiles[3].getValue() == tiles[6].getValue() && tiles[0].getValue() != 0)){
			Checker.whoWon = tiles[0].getValue();
			return true;
		} else if((tiles[1].getValue() == tiles[4].getValue() && tiles[4].getValue() == tiles[7].getValue() && tiles[1].getValue() != 0)){
			Checker.whoWon = tiles[1].getValue();
			return true;
		} else if((tiles[2].getValue() == tiles[5].getValue() && tiles[5].getValue() == tiles[8].getValue() && tiles[2].getValue() != 0)){
			Checker.whoWon = tiles[2].getValue();
			return true;
		}
		
		return false;
	}
	
	public static void reset(){
		Checker.isWon = false;
		Checker.whoWon = 0;
	}

	public static boolean getIsWon(){
		return Checker.isWon;
	}
	public static int getWhoWon(){
		return Checker.whoWon;
	}
}