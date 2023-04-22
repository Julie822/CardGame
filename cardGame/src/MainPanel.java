package CardGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel implements ActionListener{
	
	//initialize all panels
    JPanel mainPanel = new JPanel();
    JPanel dealerPanel = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel rpCardBtnPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel infoPanel = new JPanel();
    
    //initialize cards class
    Cards cards = new Cards();
    
    //initialize image labels of decks 
    public JLabel label_Image1 = new JLabel();
    public JLabel label_Image2 = new JLabel();
    public JLabel label_Image3 = new JLabel();
    public JLabel label_Image4 = new JLabel();
    public JLabel label_Image5 = new JLabel();
    public JLabel label_Image6 = new JLabel();

    //imitialize images (back of the cards)
    ImageIcon Image1 = new ImageIcon("Images/card_back.gif");
    ImageIcon Image2 = new ImageIcon("Images/card_back.gif");
    ImageIcon Image3 = new ImageIcon("Images/card_back.gif");
    ImageIcon Image4 = new ImageIcon("Images/card_back.gif");
    ImageIcon Image5 = new ImageIcon("Images/card_back.gif");
    ImageIcon Image6 = new ImageIcon("Images/card_back.gif");

    //initialize all replace card buttons
    JButton btn_rpcard1 = new JButton("Replace Card 1");
    JButton btn_rpcard2 = new JButton("Replace Card 2");
    JButton btn_rpcard3 = new JButton("Replace Card 3");
    
    //initialize all the information required as lables or buttons
    public JButton btn_result = new JButton("Result");
    public JLabel label_bet = new JLabel("Bet: $");
    public JLabel label_info = new JLabel("Please place your bet!");
    public JLabel label_current = new JLabel("Your current bet is: $");
    public int money = 100;
    public JLabel label_money = new JLabel("Amount of money you have: $" + money);
    public JLabel label_betMoney = new JLabel();
    public JTextField txt_inputBet = new JTextField(10);
    public int betMoney;
    public JButton btn_start = new JButton("Start");
    public int rpCount1;
    public int rpCount2;
    public int rpCount3;
    public int cardValue1;
    public int cardValue2;
    public int cardValue3;
    public int cardValue4;
    public int cardValue5;
    public int cardValue6;
    
    //initialize game states for updating game status
	public static final int START_GAME_STATE = 0;
	public static final int GAME_PLAYING_STATE = 1;
	public static final int GAME_LOCK_STATE = 2;
	public int gameState = START_GAME_STATE;

	//add all components to the main panel
    public MainPanel() {
    	dealerPanel.add(label_Image1);
	    dealerPanel.add(label_Image2);
	    dealerPanel.add(label_Image3);
	    playerPanel.add(label_Image4);
	    playerPanel.add(label_Image5);
	    playerPanel.add(label_Image6);
		label_Image1.setIcon(Image1);
        label_Image2.setIcon(Image2);
        label_Image3.setIcon(Image3);
        label_Image4.setIcon(Image4);
        label_Image5.setIcon(Image5);
        label_Image6.setIcon(Image6);

        rpCardBtnPanel.add(btn_rpcard1);
        rpCardBtnPanel.add(btn_rpcard2);
        rpCardBtnPanel.add(btn_rpcard3);

        buttonPanel.add(label_bet);
        buttonPanel.add(txt_inputBet);
        buttonPanel.add(btn_start);
        buttonPanel.add(btn_result);

        mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.add(dealerPanel);
        mainPanel.add(playerPanel);
        mainPanel.add(rpCardBtnPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(infoPanel);
       
        setVisible(true);
        resetGame();
    }
    
    //initialize the panel on the terminal
    public static void main(String[] args) {
        MainPanel mainPanel = new MainPanel();
        mainPanel.GameFrame();
    }
  
    //set the frame of the game space
    public void GameFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setTitle("A Simple Card Game");
        frame.setSize(400, 700);
        frame.setVisible(true);
        
        //activate all buttons
        btn_start.addActionListener(this);
        btn_result.addActionListener(this);
        btn_rpcard1.addActionListener(this);
        btn_rpcard2.addActionListener(this);
        btn_rpcard3.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {		//responsible all the actions taken by the player
		
		if (e.getSource() == btn_start) {
			gameState = GAME_PLAYING_STATE;
			
            String betInput = txt_inputBet.getText();
            // Perform actions with the betInput, e.g. update label_money or perform game logic
            label_betMoney.setText(betInput);
            betMoney = Integer.parseInt(label_betMoney.getText());
            
			//update the info panel for instruction
	    	infoPanel.remove(label_info);
	    	infoPanel.remove(label_money);
	    	infoPanel.add(label_current);
			infoPanel.add(label_betMoney);
			infoPanel.add(label_money);
            
            //generate cards from the top ot the deck and set them to the according labels
			cardValue4 = cards.getCard();
			cardValue5 = cards.getCard();
			cardValue6 = cards.getCard();
			
			ImageIcon cardPath4 = new ImageIcon("Images/card_" + cardValue4 + ".gif");
			ImageIcon cardPath5 = new ImageIcon("Images/card_" + cardValue5 + ".gif");
			ImageIcon cardPath6 = new ImageIcon("Images/card_" + cardValue6 + ".gif");
			
            label_Image4.setIcon(cardPath4);
            label_Image5.setIcon(cardPath5);
            label_Image6.setIcon(cardPath6);
            
            playerPanel.add(label_Image4);
            playerPanel.add(label_Image5);
            playerPanel.add(label_Image6);
            
            updateButtonState();
        
            //if replace buttons are pressed, generate a new card from the top of the deck
        } else if(e.getSource() == btn_rpcard1 && rpCount1 > 0) {
        	cardValue4 = cards.getCard();
			ImageIcon cardPath4 = new ImageIcon("Images/card_" + cardValue4 + ".gif");
            label_Image4.setIcon(cardPath4);
            playerPanel.add(label_Image4);
            rpCount1--;
        } else if(e.getSource() == btn_rpcard2 && rpCount2 > 0) {
        	cardValue5 = cards.getCard();
			ImageIcon cardPath5 = new ImageIcon("Images/card_" + cardValue5 + ".gif");
            label_Image5.setIcon(cardPath5);
            playerPanel.add(label_Image5);
            rpCount2--;
        } else if(e.getSource() == btn_rpcard3 && rpCount3 > 0) {
        	cardValue6 = cards.getCard();
			ImageIcon cardPath6 = new ImageIcon("Images/card_" + cardValue6 + ".gif");
            label_Image6.setIcon(cardPath6);
            playerPanel.add(label_Image6);
            rpCount3--;
        } else if(e.getSource() == btn_result) {	//if result button is pressed, draw dealer's cards from the top of the deck and show results
        	gameState = GAME_LOCK_STATE;
        	cardValue1 = cards.getCard();
			cardValue2 = cards.getCard();
			cardValue3 = cards.getCard();
			
			ImageIcon cardPath1 = new ImageIcon("Images/card_" + cardValue1 + ".gif");
			ImageIcon cardPath2 = new ImageIcon("Images/card_" + cardValue2 + ".gif");
			ImageIcon cardPath3 = new ImageIcon("Images/card_" + cardValue3 + ".gif");
			
            label_Image1.setIcon(cardPath1);
            label_Image2.setIcon(cardPath2);
            label_Image3.setIcon(cardPath3);
            
            dealerPanel.add(label_Image1);
            dealerPanel.add(label_Image2);
            dealerPanel.add(label_Image3);
            
            ImageIcon cardPath4 = new ImageIcon("Images/card_" + cardValue4 + ".gif");
			ImageIcon cardPath5 = new ImageIcon("Images/card_" + cardValue5 + ".gif");
			ImageIcon cardPath6 = new ImageIcon("Images/card_" + cardValue6 + ".gif");
			
            label_Image4.setIcon(cardPath4);
            label_Image5.setIcon(cardPath5);
            label_Image6.setIcon(cardPath6);
            
            playerPanel.add(label_Image4);
            playerPanel.add(label_Image5);
            playerPanel.add(label_Image6);
            
            updateButtonState();
            showResult();
            
            if(money <= 0) {
            	JOptionPane.showMessageDialog(mainPanel, "GameOver!\nYou have no more money!\nPlease start a new game!");
            	btn_start.setEnabled(false);
    	        btn_rpcard1.setEnabled(false);
    	        btn_rpcard2.setEnabled(false);
    	        btn_rpcard3.setEnabled(false);
    	        btn_result.setEnabled(false);
            }
        }
		
	}
	


	private void showResult() {
		int playerScore = (cardValue4 + cardValue5 + cardValue6) % 10;
		int dealerScore = (cardValue1 + cardValue2 + cardValue3) % 10;
		int playerSpecialCards = cards.isSpecialCard(cardValue4) + cards.isSpecialCard(cardValue5) + cards.isSpecialCard(cardValue6);
		int dealerSpecialCards = cards.isSpecialCard(cardValue1) + cards.isSpecialCard(cardValue2) + cards.isSpecialCard(cardValue3);
		
		if(playerSpecialCards == dealerSpecialCards) {
			if(playerScore > dealerScore) {
				JOptionPane.showMessageDialog(mainPanel, "Congratulations! You win this round!");
				money += betMoney;
			
			} else if(playerScore < dealerScore) {
				JOptionPane.showMessageDialog(mainPanel, "Sorry! The Dealer wins this round!");
				money -= betMoney;
			}
		} else {
			if(playerSpecialCards > dealerSpecialCards) {
				JOptionPane.showMessageDialog(mainPanel, "Congratulations! You win this round!");
				money += betMoney;
			} else {
				JOptionPane.showMessageDialog(mainPanel, "Sorry! The Dealer wins this round!");
				money -= betMoney;
			}
		}
		label_money.setText("Amount of money you have: $" + money);

		resetGame();
	}

	//reset the game every round 
	public void resetGame() {
	    // Reset variables
	    rpCount1 = 1;
	    rpCount2 = 1;
	    rpCount3 = 1;
	    
	    // reset the game state to start the game and update button status
	    gameState = START_GAME_STATE;
	    updateButtonState();

	    // Reset card images
	    label_Image1.setIcon(Image1);
	    label_Image2.setIcon(Image2);
	    label_Image3.setIcon(Image3);
	    label_Image4.setIcon(Image4);
	    label_Image5.setIcon(Image5);
	    label_Image6.setIcon(Image6);
	    
	    infoPanel.remove(label_current);
    	infoPanel.remove(label_betMoney);
    	infoPanel.remove(label_money);
    	infoPanel.add(label_info);
        infoPanel.add(label_money);
	    
	    // Clear bet input field
	    txt_inputBet.setText("");
	    label_betMoney.setText("");
	}

	private void updateButtonState() {
	    if (gameState == START_GAME_STATE) {
	        btn_start.setEnabled(true);
	        btn_rpcard1.setEnabled(false);
	        btn_rpcard2.setEnabled(false);
	        btn_rpcard3.setEnabled(false);
	        btn_result.setEnabled(false);

	    } else if (gameState == GAME_PLAYING_STATE) {
	        btn_start.setEnabled(false);
	        btn_rpcard1.setEnabled(true);
	        btn_rpcard2.setEnabled(true);
	        btn_rpcard3.setEnabled(true);
	        btn_result.setEnabled(true);
	    } else if (gameState == GAME_LOCK_STATE) {
	        btn_start.setEnabled(false);
	        btn_rpcard1.setEnabled(false);
	        btn_rpcard2.setEnabled(false);
	        btn_rpcard3.setEnabled(false);
	        btn_result.setEnabled(false);
	    }
	}
}
