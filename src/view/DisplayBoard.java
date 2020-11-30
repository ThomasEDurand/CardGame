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
import model.Card.shapeCount;

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

        Card card = controller.getCard(finalI, finalJ);
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

        int count;
        if (card.getShapeCount() == shapeCount.ONE){
          count = 1;
        } else if (card.getShapeCount() == shapeCount.TWO){
          count = 2;
        } else if (card.getShapeCount() == shapeCount.THREE) {
          count = 3;
        } else {
          count = 0;
        }

        String buttonString = "";
        for(int k = 0; k < count; k++){
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

        if (card.getColor() == color.RED) {
          gridButton.setStyle("-fx-text-fill: red");
        } else if (card.getColor() == color.GREEN) {
          gridButton.setStyle("-fx-text-fill: green");
        } else if (card.getColor() == color.PURPLE) {
          gridButton.setStyle("-fx-text-fill: purple");
        } else {
          gridButton.setStyle("-fx-text-fill: white");
        }


        gridButton.setOnMousePressed(
            (MouseEvent event) -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                controller.selectCard(card);
              } else if (event.getButton() == MouseButton.SECONDARY) {
                controller.deselectCard(card);
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

