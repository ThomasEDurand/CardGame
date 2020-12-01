package view;

import Controller.ControllerImpl;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Card;
import model.Card.fill;
import model.Card.shape;

public class DisplayBoard implements FXComponent {
  private final ControllerImpl controller;
  private final int height;
  private final int width;

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

        Card card = controller.getCard(0, i, j);
        Button gridButton = CardToButton.createButton(card);


        gridButton.setOnMousePressed(
            (MouseEvent event) -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                controller.selectCard(0, card);
              } else if (event.getButton() == MouseButton.SECONDARY) {
                controller.selectCard(1, card);
              } // do not want functionality for buttons besides m1 and m2
            });

        gridPane.add(gridButton, j, i);
      }
    }

    return gridPane;
  }
}

