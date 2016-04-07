
public class World {

	public static void main(String[] args) {
		
		Board board = new Board(5/*rounds here(int)*/, "Player 1 name here", "Player 2 name here");
		while(!board.isLastRound()){
			try{							//fixes threading error
				Thread.sleep(10);
			}catch(Exception e){}
			board.checkValue();
		}
		
	}

}
