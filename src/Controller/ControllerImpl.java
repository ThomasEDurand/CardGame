package Controller;

import com.sun.scenario.effect.Blend.Mode;
import model.Card;
import model.Model;

public class ControllerImpl {
  Model model;

  public ControllerImpl(Model model){
    this.model = model;
  }

  public Card getCard(int row, int col){
    return model.getCard(row, col);
  }

  public void selectCard(Card card){
    model.selectCard(card);
  }

  public void deselectCard(Card card){
    model.deselectCard(card);
  }

  public boolean isSet(){
    return  model.isSet();
  }

  public void addCards(){
    model.addCards();
  }

  public int getWidth() {
    return model.getWidth();
  }

  public Card getSelected(int cardNo){
    return model.getSelected(cardNo);
  }

  public int getCount(){
    return model.getCount();
  }

  public int getSets() {return model.getSets();}

  public void setFound(){
    model.setFound();
  }

}