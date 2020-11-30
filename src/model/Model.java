package model;

import java.util.ArrayList;

public class Model {
  private Board board;
  private Card card1;
  private Card card2;
  private Card card3;

  private int sets;

  ArrayList<ModelObserver> modelObservers;

  public Model(){
    modelObservers = new ArrayList<>();
    sets = 0;
    this.card1 = null;
    this.card2 = null;
    this.card3 = null;

    this.board = new Board();
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
    if(card1 == null && !card.isBlankCard()){
      card1 = card;
    } else if (card2 == null && !card.isBlankCard() && card != card1){
      card2 = card;
    } else if (card3 == null && !card.isBlankCard() && card != card1 && card != card2){
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

  public void setFound (){
    removeCards();
    addCards();
    notifyObs();

    card1 = card2 = card3 = null;
    sets++;
    notifyObs();
  }

  public void removeCards(){
    board.removeCard(card1);
    board.removeCard(card2);
    board.removeCard(card3);
  }

  public void addCards(){
    int addCount = 3;
    if (board.getCount() < 3){
      addCount = board.getCount();
    }
    for(int i = 0; i < addCount; i++){
      board.addCard();
    }
    notifyObs();
  }

  public int getCount(){
    return board.getCount();
  }

  public int getSets() {return sets; }

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
