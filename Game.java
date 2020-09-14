/*********************************
  
    Christine Kim
    ck2980
    Game class for PokerTest

*********************************/
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	private Player p;
	private Deck cards;
    private Scanner input;
    private int bet;
    private boolean test;
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		p = new Player();
        cards = new Deck();
        input = new Scanner(System.in);
        //creates player's from elements of String[] testHand
        for (int i=0; i<5; i++){
            Card c = new Card(testHand[i]) ;
            p.createTestHand(c);
        }
        test = true;
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
        cards = new Deck();
        p = new Player();
        input = new Scanner(System.in);
        test = false;
	}
	
	public void play(){
        boolean repeat = true;
        if (!test){ //normal game version
            while (repeat){
                bet = p.bets();
                //USER INPUT VALIDATION
                while (bet < 0 || bet > 5 || bet > p.getBankroll()){
                    System.out.println("Oops! Choose a value betweeen 1-5,"
                                      + " or a value less than your bankroll");
                    bet = p.bets();
                }
                //deals cards to hand
                for (int i=0; i<5; i++){
                    Card c= cards.deal();
                    p.addCard(i, c);
                }
                presentHand(0);
                exchange();
                presentHand(1);
                int odds = checkHand(p.getHand());
                p.winnings(odds * bet);
                System.out.println("You now have " + p.getBankroll()
                                  + " tokens.");
                System.out.println("Would you like to play again?"
                                   + " Type 'yes' to continue, "
                                   + "or anything else to stop.");
                String playAgain = input.nextLine();
                if (playAgain.equals("yes") && p.getBankroll() > 0){
                    repeat = true;
                }
                else if (playAgain.equals("yes") && p.getBankroll() <= 0){
                    System.out.println("You have 0 tokens!");
                    repeat = false;
                }
                else {
                    repeat = false;
                }
            }
        }
        else { //test version
            presentHand(0);
            int odds = checkHand(p.getHand());
        }
	}
    
    
    public void presentHand(int sort){
        if (sort == 0){ //pre-exchange
            System.out.println("Your hand is: ");
        }
        else { //post-exchange
            System.out.println("Your NEW hand is: ");
        }
        for (int i= 0; i<5; i++){
            System.out.println("Card " + (i+1) + ": " +
                               p.getHand().get(i).toString());
        }
    }
    
    public void exchange(){
        for (int i=0; i<5; i++){
            System.out.println("Do you want to exchange card " + (i+1)
                               + "? Type 'yes' to exchange, "
                               + "or anything else to continue");
            String exchange = input.nextLine();
            if (exchange.equals("yes")){
                Card c = p.getHand().get(i);
                p.addCard(i, cards.deal());
                p.removeCard(c);
            }
        }
    }
    
	public int checkHand(ArrayList<Card> hand){
        // this method takes an ArrayList of cards as
		// input and then determines what it evaluates to and
		// returns that as an int value of payout multiple
        p.sortHand(); //sorts hand before evaluating
        if (isRoyalFlush()){
            System.out.println("You got a royal flush!");
            return 250;
        }
        else if (isStraightFlush()){
            System.out.println("You got a straight flush!");
            return 50;
        }
        else if (isFourOfAKind()){
            System.out.println("You got four of a kind!");
            return 25;
        }
        else if (isFullHouse()){
            System.out.println("You got a full house!");
            return 6;
        }
        else if (isFlush()){
            System.out.println("You got a flush!");
            return 5;
        }
        else if (isStraight()){
            System.out.println("You got a straight!");
            return 4;
        }
        else if (isThreeOfAKind()){
            System.out.println("You got three of a kind.");
            return 3;
        }
        else if (isTwoPair()){
            System.out.println("You got two pairs.");
            return 2;
        }
        else if (isPair()){
            System.out.println("You got a pair.");
            return 1;
        }
        else {
            System.out.println("You got a high card.");
            return -1;
            //negative multiple will subtract bet from bankroll
        }
    }

    private boolean isRoyalFlush(){
		ArrayList<Card> h = p.getHand();
        boolean royalFlush = false;
        return (isStraightFlush() && h.get(4).getValue() == 12);
        //both straight flush and last card is a King
        //straight accounts for ace case
    }
    
    private boolean isStraightFlush(){
		ArrayList<Card> h = p.getHand();
        return (isFlush() && isStraight());
    }
    
    private boolean isFourOfAKind(){
        ArrayList<Card> h = p.getHand();
        boolean fourOfAKind = false;
        int counter = 0;
        for (int i=0; i<5; i++){
            if (h.get(0).getValue() == h.get(i).getValue()){
                counter++;
            }
        }
        if (counter == 4){
            fourOfAKind = true;
        }
        return fourOfAKind;
    }
    
    private boolean isFullHouse(){
        ArrayList<Card> h = p.getHand();
        boolean fullHouse = false;
        int pairnum = 0;
        for (int i=0; i<4; i++){
            if (h.get(i).getValue() == h.get(i+1).getValue()){
                pairnum++;
            }
        }
        //the third pair is within the threeOfAKind
        if (isThreeOfAKind() && pairnum == 3 ){
            fullHouse = true;
        }
        return fullHouse;
    }
    
    private boolean isFlush(){
        ArrayList<Card> h = p.getHand();
        boolean flush = false;
        int counter = 0;
        for (int i=0; i<5; i++){
            if (h.get(0).getSuit() == h.get(i).getSuit()){
                counter++;
            }
        }
        if (counter == 5) {
            flush = true;
        }
        return flush;
    }
    
    private boolean isStraight(){
        ArrayList<Card> h = p.getHand();
        boolean straight = false;
        boolean pair = false;
        for (int i=0; i<4; i++){
            if (h.get(i).getValue() == h.get(i+1).getValue()){
                pair = true;
            }
        }
        //first and last card has a difference of four
        //and there is no pair within the hand
        if ((h.get(4).getValue() - h.get(0).getValue()) == 4
            && !pair) {
            straight = true;
        }
        //accounts for the ace
        //second and last card has a difference of three
        //first card is ace and there is no pair
        else if (((h.get(4).getValue()-h.get(1).getValue())== 3)
                 && (h.get(0).getValue() == 0) 
                 && (h.get(1).getValue() == 9)
                 && !pair){
            straight = true;
        }
        return straight;
    }
    
    private boolean isThreeOfAKind(){
        ArrayList<Card> h = p.getHand();
        boolean threeOfAKind = false;
        int counter = 0;
        for (int i=0; i<5; i++){
            if (h.get(0).getValue() == h.get(i).getValue()){
                counter++;
            }
        }
        if (counter == 3){
            threeOfAKind = true;
        }
        return threeOfAKind;
    }
    
    private boolean isTwoPair(){
        ArrayList<Card> h = p.getHand();
        boolean twoPair = false;
        int pairnum = 0;
        for (int i=0; i<4; i++){
            if (h.get(i).getValue() == h.get(i+1).getValue()){
                pairnum++;
            }
        }
        if(pairnum == 2){
            twoPair = true;
        }
        return twoPair;
    }
    private boolean isPair(){
        ArrayList<Card> h = p.getHand();
        boolean pair = false;
        int pairnum = 0;
        for (int i=0; i<4; i++){
            if (h.get(i).getValue() == h.get(i+1).getValue()){
                pairnum++;
            }
        }
        if(pairnum == 1){
            pair = true;
        }
        return pair;
    }
	
}
