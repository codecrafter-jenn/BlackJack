import java.util.ArrayList;
import java.util.List;

public class Player {
    protected int gamesWon = 0;
    protected String name;
    protected List<Card> cards = new ArrayList<>();
    protected int handValue;

    public Player(String name) {
        this.name = name;
    }
    public Player() {
        this.name = "Player";
    }
    public int setHandValue() {
        int handValue = 0;
        boolean ace = false;
        for(Card card: cards) {
            handValue += card.value;
            if(card.stringValue.equals("ACE")){
                ace = true;
            }
        }
        if(ace && handValue <= 11) {
            handValue += 10;
        }
        this.handValue = handValue;
        return this.handValue;
    }

}
