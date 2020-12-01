package view;

import Controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Card;
import model.Card.color;
import model.Card.fill;
import model.Card.shape;

public class Tray implements FXComponent{
  private ControllerImpl controller;
  private int c;
  private int playerNumber;
  private final int opponentNumber;

  public Tray(ControllerImpl controller, int playerNumber) {
    this.controller = controller;
    this.playerNumber = playerNumber;
    this.opponentNumber = (int) Math.pow(playerNumber - 1, 2);
  }

  @Override
  public Parent render() {
    GridPane gridPane = new GridPane();

    Text sets = new Text("Sets: " + controller.getSets(playerNumber));
    gridPane.add(sets,0, 0);
    Text t = new Text("Selected");
    gridPane.add(t, 0, 1);
    c = 0;


    for(int i = 0; i < 3; i++){
      Card card = controller.getSelected(playerNumber, i);
      if(card == null){
        continue;
      }
      Button gridButton = new Button();


      String buttonShape;
      if (card.getShape() == shape.OVAL.OVAL){
        buttonShape = "O";
      } else if (card.getShape() == shape.RHOMBUS){
        buttonShape = "<>";
      } else if (card.getShape() == shape.SQUIGGLE) {
        buttonShape = "S";
      } else {
        buttonShape = "";
      }


      String buttonString = "";
      for(int k = 0; k <= card.getShapeCount(); k++){
        buttonString += buttonShape;
      }

      switch (card.getFill()){
        case SOLID:
          buttonString += " So";
          break;
        case SHADED:
          buttonString += " Sh";
          break;
        case HOLLOW:
          buttonString += " Cl";
          break;
        case BLANK:
          buttonString = "";
      }

      gridButton.setText(buttonString);

      switch(card.getColor()){
        case RED:
          gridButton.setStyle("-fx-text-fill: red");
          break;
        case GREEN:
          gridButton.setStyle("-fx-text-fill: green");
          break;
        case PURPLE:
          gridButton.setStyle("-fx-text-fill: purple");
          break;
        case BLANK:
          gridButton.setStyle("-fx-text-fill: white");
          break;
      }

      gridButton.setOnMousePressed(
          (MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
              controller.deselectCard(playerNumber, card);
            } // do not want functionality for buttons besides m1
          });

      gridButton.setMaxHeight(96);
      gridButton.setMinHeight(96);
      gridButton.setMaxWidth(96);
      gridButton.setMinWidth(96);
      gridPane.add(gridButton, 0, i + 2);
      c++;
    }

    if(c == 3){
      Button set = new Button();

      set.setOnMousePressed(
          (MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
              if (controller.isSet(playerNumber)) {
                controller.setFound(playerNumber);
              } else {
                System.out.println("Not a set");
              }// do not want functionality for buttons besides m1
            }
          });

      set.setText("set");
      gridPane.add(set,0, c + 2);
    }

    return gridPane;
  }
}
