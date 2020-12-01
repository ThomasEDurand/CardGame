package view;

import Controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
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

      Button gridButton = CardToButton.createButton(card);


      gridButton.setOnMousePressed(
          (MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
              controller.deselectCard(playerNumber, card);
            } // do not want functionality for buttons besides m1
          });

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
