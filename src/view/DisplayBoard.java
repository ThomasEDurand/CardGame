package view;

import Controller.ControllerImpl;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Card;
import model.Card.color;
import model.Card.fill;
import model.Card.shape;

public class DisplayBoard implements FXComponent {
  private ControllerImpl controller;
  private int height;
  private int width;

  public DisplayBoard(ControllerImpl controller) {
    this.controller = controller;
    this.height = 3;
    this.width = controller.getWidth();
  }

  @Override
  public Parent render() {
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5); // set gap in pixels

    gridPane.setVgap(5); // set gap in pixels

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        // required for lambda
        int finalI = i;
        int finalJ = j;

        Card card = controller.getCard(0,finalI, finalJ);
        Button gridButton = new Button();

        String buttonShape;
        if (card.getShape() == shape.OVAL){
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

        if (card.getFill() == fill.SOLID) {
          buttonString += " So";
        } else if (card.getFill() == fill.SHADED) {
          buttonString += " Sh";
        } else if (card.getFill() == fill.HOLLOW) {
          buttonString += " Cl";
        } else {
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
                controller.selectCard(0, card);
              } else if (event.getButton() == MouseButton.SECONDARY) {
                controller.selectCard(1, card);
              } // do not want functionality for buttons besides m1 and m2
            });


        gridButton.setMaxHeight(96);
        gridButton.setMinHeight(96);
        gridButton.setMaxWidth(96);
        gridButton.setMinWidth(96);
        gridPane.add(gridButton, finalJ, finalI);
      }
    }

    return gridPane;
  }
}

