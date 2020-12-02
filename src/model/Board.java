package model;

import java.util.ArrayList;

public class Board {
  private ArrayList<Card> row1;
  private ArrayList<Card> row2;
  private ArrayList<Card> row3;

  private ArrayList<ArrayList<Card>> board;
  private Deck deck;

  public Board(){
    this.deck = new Deck();

    this.row1 = new ArrayList<>();
    this.row2 = new ArrayList<>();
    this.row3 = new ArrayList<>();

    this.board = new ArrayList<ArrayList<Card>>();
    this.board.add(row1);
    this.board.add(row2);
    this.board.add(row3);

    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 4; j++){
        board.get(i).add(deck.drawCard());
      }
    }
  }

  public void removeCard(Card card){
    for(int i = 0; i < board.size(); i ++){
      for(int j = 0; j < board.get(i).size(); j++){
        if (board.get(i).get(j).isSameCard(card)) {
          board.get(i).set(j, new Card(3, 3, 3, 3));
        }
      }
    }

  }

  public void addCard(){
    if(deck.getCount() == 0){
      reorganizeBoard();
    } else {

      for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board.get(i).size(); j++) {
          if (board.get(i).get(j).isBlankCard()) {
            board.get(i).set(j, deck.drawCard());
            return;
          }
        }
      }

      // Case 1: rows are the same length
      if (board.get(0).size() == board.get(0).size() && board.get(0).size() == board.get(2)
          .size()) {
        board.get(0).add(deck.drawCard());
        // Case 2: row1 is the longest, row2 and row3 are the same length
      } else if ((board.get(0).size() > board.get(1).size()) && (board.get(1).size() == board.get(2)
          .size())) {
        board.get(1).add(deck.drawCard());
        // Case 3: row1 and row2 are the same in size, row3 is shorter
      } else if (board.get(0).size() == board.get(1).size() && (board.get(1).size() > board.get(2)
          .size())) {
        board.get(2).add(deck.drawCard());
      } else {
        throw new IllegalStateException("Error in addCard method");
      }
    }
  }

  public void reorganizeBoard () {

    ArrayList<Card> pile = new ArrayList<>();
    for(int i = 0; i < board.size(); i++){
      for(int j = 0; j < board.get(i).size(); j++){
        if (board.get(i).get(j) != null && !board.get(i).get(j).isBlankCard()) {
          pile.add(board.get(i).get(j));
        }
      }
    }

    row1.clear();
    row2.clear();
    row3.clear();


    int index = 0;
    while (pile.size() > index){
      board.get(index % 3).add(pile.get(index));
      index++;
    }


  }

  public int getCount(){
    return deck.getCount();
  }

  public int getWidth() {return board.get(0).size(); }

  public Card getCard(int row, int col){
    return board.get(row).get(col);
  }
}

