public class Dealer extends Player {

    public Dealer() {
        this.name = "Dealer Joe";
    }
    public void dealCards(DeckOfCards deck, Dealer dealer, Player player, BlackJackCLI CLI){
        hitPlayer(deck, player);
        hitDealer(deck, dealer);
        hitPlayer(deck, player);
        hitDealer(deck, dealer);
        CLI.displayCards(dealer, player);
    }

    public void hitDealer(DeckOfCards deck, Dealer dealer){
        Card card = deck.dealOne();
        card.convertValue();
        dealer.cards.add(card);
    }

    public void hitPlayer(DeckOfCards deck, Player player){
        Card card = deck.dealOne();
        card.convertValue();
        player.cards.add(card);
    }

    public void hitOrStay(DeckOfCards deck, Dealer dealer, Player player){
        if(player.setHandValue() <= 21) {
            while(dealer.setHandValue() < 17) {
                dealer.hitDealer(deck, dealer);
            }
        }
    }

}
