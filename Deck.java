/*********************************
  
    Christine Kim
    ck2980
    Deck class for PokerTest

*********************************/

import java.util.Arrays;
import java.util.Random;
Random rand = new Random();

public class Deck {
	
	private Card[] cards;
	private int top; //the index of the top of the deck
	
	public Deck(){
		//make a 52 card deck here
		cards = new Card[52];
        top = 0;
        int counter = 0;
        for (int i=0; i<4; i++){  //for suits
            for (int j=0; j<13; j++){ //for values
                Card temp = new Card(j, i);
                cards[counter]= temp;
                counter++;
            }
        }
        shuffle();
	}
    
    //shuffles the deck
	public void shuffle(){
        for(int i=0; i<cards.length; i++){
            int next = rand.nextInt(52);
            Card temp = cards[i];
            cards[i] = cards[next];
            cards[next]= temp;
        }
	}
	
	public Card deal(){
		//deal the top card in the deck
		top++;
        return cards[top-1];
	}
   	
	//add more methods here if needed

}
