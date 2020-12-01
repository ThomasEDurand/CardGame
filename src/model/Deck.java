package model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
  private ArrayList<Card> cards;

  public Deck() {
    cards = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            cards.add(new Card(i, j, k, l));
          }
        }
      }
    }
  }

  public int getCount(){
    return cards.size();
  }

  public Card drawCard(){
    if(cards.size() == 0){
      return new Card(3, 3, 3, 3);
    }

    int randomNum = ThreadLocalRandom.current().nextInt(0, cards.size());
    Card returnCard = cards.get(randomNum);
    cards.remove(randomNum);
    return returnCard;
  }

}
