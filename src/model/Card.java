package model;

public class Card {

  public enum shape {
    OVAL,
    RHOMBUS,
    SQUIGGLE,
    BLANK
  }

  public enum color {
    RED,
    GREEN,
    PURPLE,
    BLANK
  }

  public enum fill {
    HOLLOW,
    SHADED,
    SOLID,
    BLANK
  }


  private final shape cardShape;
  private final color cardColor;
  private final fill cardFill;
  private final int cardShapeCount;

  public Card(int shapeI, int colorI, int fillI, int shapeCountI){
    this.cardShape = shape.values()[shapeI];
    this.cardColor = color.values()[colorI];
    this.cardFill = fill.values()[fillI];
    this.cardShapeCount = shapeCountI;
  }

  public shape getShape(){
    return cardShape;
  }

  public color getColor(){
    return cardColor;
  }

  public fill getFill(){
    return cardFill;
  }

  public int getShapeCount(){
    return cardShapeCount;
  }

  public boolean isBlankCard(){
    return ((this.cardColor == color.BLANK) && (this.cardFill == fill.BLANK) && (this.cardShapeCount == 3) && (this.cardShape == shape.BLANK));
  }

  public boolean isSameCard(Card other){
    return ((this.cardShape == other.cardShape) && (this.cardColor == other.cardColor) && (this.cardFill == other.cardFill) && (this.cardShapeCount == other.cardShapeCount));
  }


}
