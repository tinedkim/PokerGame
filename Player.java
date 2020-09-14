/*********************************
  
    Christine Kim
    ck2980
    Player class for PokerTest

*********************************/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
		
	private ArrayList<Card> hand; //the player's cards
	private int bankroll;
    private int bet;
    private Scanner input;

		
	public Player(){		
	    //create a player here
	    hand = new ArrayList<Card>(5);
        bankroll = 10; //player starts off with 10 tokens
        input = new Scanner(System.in);
	}

    //hand for the test version of the game
    public void createTestHand(Card c){
            hand.add(c); 
    }
    
    //sorts hand using compareTo
    public void sortHand(){
        ArrayList<Card> temp = new ArrayList<Card>(5);
        for (int i=0; i<5; i++){
            temp.add(hand.get(i));
        }
        Collections.sort(temp);
        for (int i=0; i<5; i++){
            hand.set(i,temp.get(i));
        }
        
    }
    
	public void addCard(int index, Card c){
	    //add the card c to the player's hand at index       
	    hand.add(index, c);
	}

	public void removeCard(Card c){
	    //remove the card c from the player's hand
	    hand.remove(c);
        }
		
    public int bets(){
        //player makes a bet
        System.out.println("You currently have " + bankroll + " tokens.");
        System.out.println("You can bet from 0 to 5 tokens. " +
                           "How many tokens would you like to bet?");
        bet = input.nextInt();
        return bet;
    }
    
    public void winnings(int odds){
        //adjust bankroll if player wins
        bankroll += odds;
    }

    public int getBankroll(){
        //return current balance of bankroll
        return bankroll;
    }
    
    public ArrayList<Card> getHand(){
        //return hand ArrayList
        return hand;
    }
    
}


