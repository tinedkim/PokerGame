READ ME

Christine Kim (ck2980)

                            POKER GAME

INSTRUCTIONS
    The player starts off with 10 tokens and is promted to bet a
    range of 1-5 tokens (or less if their bankroll doesn't allow
    for it). Then the deck class will deal the player 5 randomly
    shuffled cards and the game will then prompt for each card 
    if the player would like to exchange it. After collecting the
    player's responses, the game will exchange the cards with new
    dealt cards from the deck and announce what the player's new
    hand is. Based on what the hand is, the game will give out or
    take away a multiple of what the player betted from their 
    bankroll. Finally, it will prompt the player if they want to 
    play again and repeat the game if yes, by dealing another five
    cards.

DESIGN
    The game class is overloaded; one with no constructor that acts
    as a normal game. The other one prompts for a String[] which a
    test class would provide from the user to see if all the checks
    work. For the test class, I also overloaded the card class so
    that it would take a String element in the style of "suitValue."
    It will also create the player's test hand in the player class
    so that I wouldn't have to deal with the deal method logistics
    and the deck class. To simplify this mode, I had the game class
    only present the hand that the user put in with the toString
    method and then evaluate the hand, printing out the type of hand.
    
    Some design changes I made for the normal game was to have the
    checkHand method return an integer value for the payout multiple
    and instead of returning a string, print out the string instead.
    Additionally, I created a separate exchange method for brevity.
    The exchange method will prompt the user a yes or no question
    whether they want to exchange the card or not. It will then add
    a card to the hand (I added an index parameter so that it would
    preserve the order of the hand). Then it will remove the card 
    after it (which is the original card that the user wanted to 
    remove.) The present method will read out the hand for the user
    to see what their hand is before and after exchanging.
    
    My ace card is marked as the first card of the deck and methods
    that depend on its placement such as isStraight() accounts for
    its placement in relation to cards after value 10. 
    

EXTERNAL SOURCES
    All the wisdom from the Poker Hackathon!
    
