/*********************************
  
    Christine Kim
    ck2980
    Card class for PokerTest

*********************************/

public class Card implements Comparable<Card>{
	
	private int suit; //use integers 1-4 to encode the suit
	private int value; //use integers 1-13 to encode the rank
	
	public Card(int v, int s){
		//make a card with suit s and value v
		value = v;
        suit = s;
        
	}

    public Card(String suitValue){
        //overloaded Card constructor for testHand Card
        if (suitValue.charAt(0) == 'c'){
            suit = 0;
        }
        else if (suitValue.charAt(0) == 'd'){
            suit = 1;
        }
        else if (suitValue.charAt(0) == 'h'){
            suit = 2;
        }
        else if (suitValue.charAt(0) == 's'){
            suit = 3;
        }
        else{
            System.out.println("Your hand is invalid. "
                             + "Please enter a valid card.");
            suit = -1;
        }
        
        if (suitValue.length() == 2){
            value = Integer.parseInt(suitValue.substring(1,2)) - 1;
        }
        else if (suitValue.length() == 3){
            value = Integer.parseInt(suitValue.substring(1,3)) - 1;
        }
        else {
            System.out.println("Your hand is invalid. "
                             + "Please enter a valid card.");
            value = -1;
        }
    }
    
	
	public int compareTo(Card c){
		//use this method to compare cards so they 
		//may be easily sorted
        if (this.value > c.value){
            return 1;
        }
        else if (this.value < c.value){
            return -1;
        }
        else {
            if (this.suit > c.suit){
                return 1;
            }
            else if (this.suit < c.suit){
                return -1;
            }
            else {
                return 0;
            }
        }
	}
    
    //returns int of suit (1:clubs, 2:diamonds, ...)
    public int getSuit(){
        return suit;
    }
    //returns int of value (1:ace, 2:2, ..., 13:King);
    public int getValue(){
        return value;
    }
	
	public String toString(){
		//use this method to easily print a Card object
		String cardInfo;
        String[] suitString = {"clubs" , "diamonds", "hearts", "spades"};
        String[] valueString = {"Ace", "2", "3", "4", "5", "6", "7", "8",
                               "9", "10", "Jack", "Queen", "King"};
        cardInfo = valueString[value] + " of " + suitString[suit];
        return cardInfo;
		
	}
	//add some more methods here if needed

}
