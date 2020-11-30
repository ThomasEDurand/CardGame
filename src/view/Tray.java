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
import model.Card.shapeCount;

public class Tray implements FXComponent{
  private ControllerImpl controller;
  private int c;

  public Tray(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane gridPane = new GridPane();
    Text t = new Text("Selected");
    gridPane.add(t, 0, 0);
    c = 0;


    for(int i = 0; i < 3; i++){
      Card card = controller.getSelected(i);
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
              controller.deselectCard(card);
            } // do not want functionality for buttons besides m1
          });


      gridButton.setMaxHeight(96);
      gridButton.setMinHeight(96);
      gridButton.setMaxWidth(96);
      gridButton.setMinWidth(96);
      gridPane.add(gridButton, 0, i + 1);
      c++;
    }

    if(c == 3){
      Button set = new Button();

      set.setOnMousePressed(
          (MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
              if (controller.isSet()) {
                // TODO
                controller.setFound();
              } else {
                System.out.println("Not a set");
              }// do not want functionality for buttons besides m1
            }
          });

      set.setText("set");
      gridPane.add(set,0, c + 1);
    }

    return gridPane;
  }
}
