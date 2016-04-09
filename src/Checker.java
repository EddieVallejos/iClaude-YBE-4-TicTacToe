public class Checker extends Thread{
	public static final int DIAGONAL = 1;
	public static final int VERTICAL = 2;
	public static final int HORIZONTAL = 3;
	private static boolean isWon = false;
	private static boolean vDone = false;
	private static boolean hDone = false;
	private static boolean dDone = false;
	private static int whoWon = 0;
	private int mode;
	
	private Board board;
	
	public Checker(int mode, Board board){
		this.mode = mode;
		this.board = board;
	}
	
	
	/*METHODS*/
	@Override
	public void run() {
		while(!board.isLastRound()){
			if(board.getTiles() == null) continue;
			try{
				this.sleep(10);
			}catch(Exception e){}
			if(!Board.getMove() || Checker.isWon) continue; // checks if user moved and if winner exists
			switch(this.mode){
				case 1:	Checker.isWon = diagonalCheck(); //checks diagonal tiles
						break;
				case 2:	Checker.isWon = verticalCheck(); //checks vertical tiles
						break;
				case 3:	Checker.isWon = horizontalCheck(); //checks horizontal tiles
						break;
			}
			if(!Checker.isDoneChecking()) continue;

			Checker.resetDone();
			Board.isDoneMoving();
		}
	}

	/* Checks diagonal tiles*/
	private synchronized boolean diagonalCheck(){
		Tile[] tiles = board.getTiles();
		Checker.dDone = true;
		if((tiles[0].getValue() == tiles[4].getValue() && tiles[4].getValue() == tiles[8].getValue() && tiles[0].getValue() != 0) ||
				(tiles[2].getValue() == tiles[4].getValue() && tiles[4].getValue() == tiles[6].getValue() && tiles[2].getValue() != 0)){
			Checker.whoWon = tiles[4].getValue();
			return true;
		}
		return false;
	}

	/* Checks horizontal tiles */
	private synchronized boolean horizontalCheck(){
		Tile[] tiles = board.getTiles();
		Checker.hDone = true;
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

	/* Checks vertical tiles */
	private synchronized boolean verticalCheck(){
		Tile[] tiles = board.getTiles();
		Checker.vDone = true;
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
	
	/* Resets checkers */
	public static void reset(){
		Checker.isWon = false;
		Checker.whoWon = 0;
	}
	public static void resetDone(){
		Checker.vDone = false;
		Checker.hDone = false;
		Checker.dDone = false;
	}
	
	/* GETTERS */
	public static boolean isDoneChecking(){
		if(Checker.vDone && Checker.hDone && Checker.dDone) return true;
		return false;
	}	
	public static boolean getIsWon(){
		return Checker.isWon;
	}
	public static int getWhoWon(){
		return Checker.whoWon;
	}
}
