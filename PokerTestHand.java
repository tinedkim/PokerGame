import java.util.Scanner;

public class PokerTestHand{
    
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);
        String[] handy = new String[5];
        for (int i=0; i<5; i++){
            System.out.println("What card do you want to test?");
            String card = input.nextLine();
            handy[i] = card;
        }
        
        Game g = new Game(handy);
        g.play();
    }
}
