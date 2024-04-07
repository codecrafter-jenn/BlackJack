import java.util.Scanner;

public class BlackJackCLI {

    Scanner input = new Scanner(System.in);
    public String welcomeMsg() {
        System.out.println("Welcome to the BlackJack Table. What is your name?");
        String name = input.nextLine();
        System.out.println("Hi " + name + ". Let's Play!");
        return name;
    }
    public void displayCards(Dealer dealer, Player player){
        System.out.println("*****************");
        System.out.println("Your cards: ");
        for (Card card: player.cards) {
            System.out.println(card);
        }
        System.out.println("Your current score is: " + player.setHandValue());

        System.out.println("*****************");
        System.out.println(dealer.name + " is showing: \n" + dealer.cards.get(0));
        System.out.println("*****************");
    }
    public void displayAllCards(Dealer dealer, Player player){
        System.out.println("*****************");
        System.out.println("Your cards: ");
        for (Card card: player.cards) {
            System.out.println(card);
        }
        System.out.println("*****************");
        System.out.println(dealer.name + "'s Cards:");
        for (Card card: dealer.cards) {
            System.out.println(card);
        }
    }
    public boolean wannaHit(){
        while(true) {
            System.out.println("Would you like another card? (Y/N)");
            String response = input.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")){
                return false;
            } else {
                System.out.println("Please choose a valid response");
            }
        }
    }

    public void busted(Player busted, Player winner){
        winner.gamesWon++;
        System.out.println(busted.name + " busted! " + winner.name + " Wins!");

    }
    public void push(){
        System.out.println("It's a push!");
    }
    public void announceWinner(Player winner){
        winner.gamesWon++;
        System.out.println(winner.name + " Wins!");
    }
    public void printScores(Dealer dealer, Player player) {
        System.out.println("*****************");
        if(dealer.setHandValue() == 21 || player.setHandValue() == 21){
            System.out.println("\uD83C\uDCA1BLACKJACK!\uD83C\uDCA1");
        }
        System.out.println(dealer.name + " has " + dealer.handValue);
        System.out.println(player.name + " has " + player.handValue);
        System.out.println("*****************");
    }
    public void displayStats(Dealer dealer, Player player) {
        System.out.println("You have won " + player.gamesWon + " games");
        System.out.println(dealer.name + " has won " + dealer.gamesWon + " games");
    }
    public boolean playAgain(){
        while(true) {
            System.out.println("*****************");
            System.out.println("Would you like to play again? (Y/N)");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Please choose a valid response");
            }
        }
    }

}
