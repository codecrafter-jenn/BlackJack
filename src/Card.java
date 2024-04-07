public class Card {
    String suit;
    int value;
    String stringValue;

    public void convertValue(){
        switch (stringValue) {
            case "ACE":
                this.value = 1;
                break;
            case "JACK":
            case "QUEEN":
            case "KING":
                this.value = 10;
                break;
            default :
                this.value = Integer.parseInt(stringValue);
                break;
        }
    }

    @Override
    public String toString() {
        String cardString = stringValue;
        switch (suit) {
            case "SPADES":
                cardString += "♠";
                break;
            case "HEARTS":
                cardString += "♥";
                break;
            case "CLUBS":
                cardString += "♣";
                break;
            case "DIAMONDS":
                cardString += "♦";
                break;

        }
        return cardString;
    }
}
