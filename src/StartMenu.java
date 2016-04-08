import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;

import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

public class StartMenu {
	private static JFrame frame;
	private static JPanel menu;
	private static JTextField name1, name2, rounds;
	private static Container cont;
	private static	JButton start, cancel;
	
	public StartMenu(){
		StartMenu.frame = new JFrame();
		StartMenu.frame.setResizable(false);
		StartMenu.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.createMenuWindow();
		
		StartMenu.frame.pack();
		StartMenu.frame.setLocationRelativeTo(null);
		StartMenu.frame.setVisible(true);
	}
	
	private void createMenuWindow(){
		JPanel panel = new JPanel(new GridLayout(4,4,10,10));
		
		StartMenu.cont = StartMenu.frame.getContentPane();
		StartMenu.menu = new JPanel(new BorderLayout());
		
		
		JLabel pl1 = new JLabel("Player 1: ");
		JLabel pl2 = new JLabel("Player 2: ");
		JLabel turns = new JLabel("Best of: ");
		
		this.createButtons();
		this.createTextFields();
		panel.add(pl1);
		panel.add(StartMenu.name1);
		panel.add(pl2);
		panel.add(StartMenu.name2);
		panel.add(turns);
		panel.add(StartMenu.rounds);
		panel.add(StartMenu.start);
		panel.add(StartMenu.cancel);
		

		panel.setBackground(Board.BACKGROUND_COLOR);
		

		StartMenu.menu.add(panel, BorderLayout.CENTER);
		StartMenu.menu.add(Board.createFiller(0, 30), BorderLayout.PAGE_START);
		StartMenu.menu.add(Board.createFiller(0, 30), BorderLayout.PAGE_END);
		StartMenu.menu.add(Board.createFiller(20, 0), BorderLayout.LINE_START);
		StartMenu.menu.add(Board.createFiller(20, 0), BorderLayout.LINE_END);
		StartMenu.cont.add(StartMenu.menu);
		//String a = rounds.getText();
		//round = Integer.parseInt(a);
	}
	
	private void createButtons(){
		StartMenu.start = new JButton("START");
		StartMenu.start.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!(StartMenu.start.isEnabled())) return;
				String player1,player2;
				int rounds = 0;
				player1 = StartMenu.name1.getText();
				player2 = StartMenu.name2.getText();
				try{
					rounds = Integer.parseInt(StartMenu.rounds.getText());
				}catch(Exception e){System.exit(0);}
				
				World.createBoard(rounds, player1, player2);
				StartMenu.frame.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				StartMenu.isButtonAvail();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		StartMenu.start.setEnabled(false);
		
		StartMenu.cancel = new JButton("CANCEL");
		StartMenu.cancel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				StartMenu.isButtonAvail();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}
	
	private void createTextFields(){
		StartMenu.name1 = new JTextField("Enter your name here..");
		StartMenu.name2 = new JTextField("Enter your name here..");
		StartMenu.rounds = new JTextField("Must be odd number..");

		StartMenu.name1.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	if(name1.getText().equals("Enter your name here..")) name1.setText("");
		    	StartMenu.isButtonAvail();
		    }

		    public void focusLost(FocusEvent e) {
		    	if(name1.getText().equals("")) name1.setText("Enter your name here..");
		    	StartMenu.isButtonAvail();
		    }
		});
		
		StartMenu.name2.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	if(name2.getText().equals("Enter your name here..")) name2.setText("");
		    	StartMenu.isButtonAvail();
		    }
		    public void focusLost(FocusEvent e) {
		    	if(name2.getText().equals("")) name2.setText("Enter your name here..");
		    	StartMenu.isButtonAvail();
		    }
		});
		
		StartMenu.rounds.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	if(rounds.getText().equals("Must be odd number..")) rounds.setText("");
		    	StartMenu.isButtonAvail();
		    }

		    public void focusLost(FocusEvent e) {
		    	if(rounds.getText().equals("")) rounds.setText("Must be odd number..");
		    	StartMenu.isButtonAvail();
		    }
		});
		
	}
	
	private static void isButtonAvail(){
		int x = 0;
		try{
			x = Integer.parseInt(rounds.getText())%2;
		}catch(Exception e){
			StartMenu.start.setEnabled(false); 
			return;
		}
		
		if(name1.getText().equals("") || 
				name1.getText().equals("Enter your name here..") || 
					name2.getText().equals("") || 
						name2.getText().equals("Enter your name here..") || 
							x%2 == 0)
			StartMenu.start.setEnabled(false);
		else StartMenu.start.setEnabled(true);
	}
	
	
}
