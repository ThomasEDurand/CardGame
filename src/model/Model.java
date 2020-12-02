package model;

import java.util.ArrayList;

public class Model {
  private Board board;
  private Card card1;
  private Card card2;
  private Card card3;

  private int sets;

  ArrayList<ModelObserver> modelObservers;

  public Model(Board board){
    modelObservers = new ArrayList<>();
    sets = 0;
    this.card1 = null;
    this.card2 = null;
    this.card3 = null;

    this.board = board;
  }

  public void addObserver(ModelObserver observer) {
    modelObservers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    modelObservers.remove(observer);
  }

  public Card getSelected(int cardNo){
    if (cardNo == 0){
      return card1;
    } else if (cardNo == 1){
      return card2;
    } else {
      return card3;
    }
  }

  public void selectCard(Card card){
    if(card.isBlankCard()){

    } else if(card1 == null){
      card1 = card;
    } else if (card2 == null && card != card1){
      card2 = card;
    } else if (card3 == null && card != card1 && card != card2){
      card3 = card;
    }
    notifyObs();
  }

  public void deselectCard(Card card){
    if(card1 != null && card1.isSameCard(card)){
      card1 = null;
    } else if (card2 != null && card2.isSameCard(card)){
      card2 = null;
    } else if (card3 != null && card3.isSameCard(card)){
      card3 = null;
    }
    notifyObs();
  }

  public boolean isSet(){
    if (card1 == null || card2 == null || card3 == null){
      return false;
    }
    if(card1.getShape() == card2.getShape() && card2.getShape() == card3.getShape()){

    } else if (card1.getShape() != card2.getShape() && card2.getShape() != card3.getShape()) {

    } else {
      return false;
    }

    if(card1.getColor() == card2.getColor() && card2.getColor() == card3.getColor()){
    } else if (card1.getColor() != card2.getColor() && card2.getColor() != card3.getColor()) {
    } else {
      return false;
    }

    if(card1.getFill() == card2.getFill() && card2.getFill() == card3.getFill()){
    } else if (card1.getFill() != card2.getFill() && card2.getFill() != card3.getFill()) {
    } else {
      return false;
    }

    if(card1.getShapeCount() == card2.getShapeCount() && card2.getShapeCount() == card3.getShapeCount()){
    } else if (card1.getShapeCount() != card2.getShapeCount() && card2.getShapeCount() != card3.getShapeCount()) {
    } else {
      return false;
    }
    return true;
  }

  public Card[] setFound (){
    removeCards(); // from board
    addCards(false); // from board

    Card c1 = card1;
    Card c2 = card2;
    Card c3 = card3;

    Card[] cards = new Card[]{c1, c2, c3};
    card1 = card2 = card3 = null;
    sets++;
    return cards;
  }

  public void opponentFoundSet(Card[] cards){
    for (Card card : cards) {
      if (card1 != null && card != null && card1.isSameCard(card)) {
        card1 = null;
      }
      if (card2 != null && card != null && card2.isSameCard(card)) {
        card2 = null;
      }
      if (card3 != null && card != null && card3.isSameCard(card)) {
        card3 = null;
      }
    }
    notifyObs();
  }


  public void removeCards(){
    board.removeCard(card1);
    board.removeCard(card2);
    board.removeCard(card3);
    notifyObs();
  }

  public void addCards(Boolean rerender){
    if (getCount() == 0) {
        board.addCard();
    } else {
      for (int i = 0; i < 3; i++) {
        board.addCard();
      }
    }
    if (rerender) {
      notifyObs();
    }
  }

  public int getCount(){
    return board.getCount();
  }

  public int getSets() {
    return sets;
  }

  public int getWidth() {return board.getWidth(); }

  public Card getCard(int row, int col){
    return  board.getCard(row, col);
  }

  public void notifyObs() {
    for (ModelObserver modelObserver : modelObservers) {
      modelObserver.update(this);
    }
  }

}
