import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeckOfCards {
    private final String deckID;
    public DeckOfCards() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            JSONObject obj = (JSONObject) JSONValue.parse(body);
            deckID = (String) obj.get("deck_id");

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Card dealOne(){
        Card card = new Card();
        String stringURI = String.format("https://www.deckofcardsapi.com/api/deck/%s/draw/?count=1", deckID);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(stringURI))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            JSONObject data = (JSONObject) JSONValue.parse(body);
            JSONArray newCardArray = (JSONArray) data.get("cards");
            JSONObject newCard = (JSONObject) newCardArray.get(0);
            card.stringValue = (String) newCard.get("value");
            card.suit = (String) newCard.get("suit");

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    public void shuffleDeck(){
        try {
            String stringURI = String.format("https://www.deckofcardsapi.com/api/deck/%s/shuffle/", deckID);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(stringURI))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());


        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
