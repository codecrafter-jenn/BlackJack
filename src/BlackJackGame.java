public class BlackJackGame {
    private final DeckOfCards deck;
    private final Dealer dealer;
    private final BlackJackCLI CLI;
    private final Player player;

    public BlackJackGame(DeckOfCards deck, Dealer dealer, Player player, BlackJackCLI CLI) {
        this.deck = deck;
        this.dealer = dealer;
        this.player = player;
        this.CLI = CLI;
    }

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        DeckOfCards deck = new DeckOfCards();
        BlackJackCLI CLI = new BlackJackCLI();
        String name = CLI.welcomeMsg();
        Player player = new Player(name);
        BlackJackGame game = new BlackJackGame(deck, dealer, player, CLI);
        boolean play = true;
        while(play) {
            play = game.startGame();
            game.resetGame(deck, player, dealer);
        }
    }
    public boolean startGame() {
        dealer.dealCards(deck, dealer, player, CLI);
        while (player.setHandValue() < 21 && CLI.wannaHit()) {
            dealer.hitPlayer(deck, player);
            CLI.displayCards(dealer, player);
        }
        player.setHandValue();
        dealer.setHandValue();
        dealer.hitOrStay(deck, dealer, player);
        evaluateWinner();
        return CLI.playAgain();
    }

    public void evaluateWinner(){
        CLI.displayAllCards(dealer, player);
        CLI.printScores(dealer, player);
        if(player.handValue > 21) {
            CLI.busted(player, dealer);
        } else if (dealer.handValue > 21) {
            CLI.busted(dealer, player);
        } else if (dealer.handValue == player.handValue) {
            CLI.push();
        } else if (player.handValue > dealer.handValue) {
            CLI.announceWinner(player);
        } else {
            CLI.announceWinner(dealer);
        }
        CLI.displayStats(dealer, player);
    }
    public void resetGame(DeckOfCards deck, Player player, Dealer dealer){
        deck.shuffleDeck();
        dealer.cards.clear();
        player.cards.clear();
    }
}