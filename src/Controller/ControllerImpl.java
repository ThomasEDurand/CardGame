package Controller;

import com.sun.scenario.effect.Blend.Mode;
import model.Card;
import model.Model;

public class ControllerImpl {
  Model[] models;

  public ControllerImpl(Model[] model){
    this.models = model;
  }

  public Card getCard(int model, int row, int col){
    return models[model].getCard(row, col);
  }

  public void selectCard(int modelNo, Card card){
    models[modelNo].selectCard(card);
  }

  public void deselectCard(int modelNo, Card card){
    models[modelNo].deselectCard(card);
  }

  public boolean isSet(int modelNo){ return  models[modelNo].isSet();
  }

  public void addCards(){
    models[0].addCards();
  }

  public int getWidth() {
    return models[0].getWidth();
  }

  public Card getSelected(int modelNo, int cardNo){
    return models[modelNo].getSelected(cardNo);
  }

  public int getCount(){
    return models[0].getCount();
  }

  public int getSets(int modelNo) {
    return models[modelNo].getSets();
  }

  public void setFound(int playerNo){
    models[(int) Math.pow(playerNo - 1 , 2)].opponentFoundSet(models[playerNo].setFound());
  }


  public int getPlayerCount(){
    return models.length;
  }

}