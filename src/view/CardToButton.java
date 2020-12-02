package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Card;
import model.Card.fill;
import model.Card.shape;

public class CardToButton {
  public static Button createButton (Card card) {
    Button gridButton = new Button();
    GridPane buttonPane = new GridPane();
    buttonPane.setHgap(7);
    buttonPane.setVgap(7);

    Color color = switch (card.getColor()) {
      case RED -> Color.RED;
      case GREEN -> Color.GREEN;
      case PURPLE -> Color.PURPLE;
      case BLANK -> Color.WHITE;
    };


    if (card.getShape() == shape.OVAL){
      for(int k = 0; k <= card.getShapeCount(); k++) {
        Circle circle = new Circle(10);
        if(card.getFill() == fill.SOLID) {
          circle.setFill(color);
          buttonPane.add(circle, k, 0);
        } else if (card.getFill() == fill.HOLLOW){
          circle.setFill(Color.WHITE);
          circle.setStrokeWidth(3);
          circle.setStroke(color);
          buttonPane.add(circle, k, 0);
        } else if (card.getFill() == fill.SHADED){
          circle.setFill(color);
          circle.setStrokeType(StrokeType.INSIDE);
          circle.setStrokeWidth(4);
          circle.setStroke(Color.BLACK);
          buttonPane.add(circle, k, 0);
        }
      }

    } else if (card.getShape() == shape.RHOMBUS){
      for(int k = 0; k <= card.getShapeCount(); k++) {
        Rectangle rectangle = new Rectangle(20, 20);
        if (card.getFill() == fill.SOLID) {
          rectangle.setFill(color);
          buttonPane.add(rectangle, k, 0);
        } else if (card.getFill() == fill.HOLLOW) {
          rectangle.setFill(Color.WHITE);
          rectangle.setStrokeWidth(3);
          rectangle.setStroke(color);
          buttonPane.add(rectangle, k, 0);
        } else if (card.getFill() == fill.SHADED) {
          rectangle.setFill(color);
          rectangle.setStrokeType(StrokeType.INSIDE);
          rectangle.setStrokeWidth(4);
          rectangle.setStroke(Color.BLACK);
          buttonPane.add(rectangle, k, 0);
        }
      }

    } else if (card.getShape() == shape.TRIANGLE) {
      for(int k = 0; k <= card.getShapeCount(); k++) {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
            0.0, 0.0,
            18.0, 0.0,
            10.0, 16.5);
        if (card.getFill() == fill.SOLID) {
          triangle.setFill(color);
          buttonPane.add(triangle, k, 0);
        } else if (card.getFill() == fill.HOLLOW) {
          triangle.setFill(Color.WHITE);
          triangle.setStrokeWidth(3);
          triangle.setStroke(color);
          buttonPane.add(triangle, k, 0);
        } else if (card.getFill() == fill.SHADED) {
          triangle.setFill(color);
          triangle.setStrokeType(StrokeType.INSIDE);
          triangle.setStrokeWidth(3);
          triangle.setStroke(Color.BLACK);
          buttonPane.add(triangle, k, 0);
        }
      }
    }

    gridButton.setMaxHeight(96);
    gridButton.setMinHeight(96);
    gridButton.setMaxWidth(96);
    gridButton.setMinWidth(96);

    buttonPane.setAlignment(Pos.CENTER);
    gridButton.setGraphic(buttonPane);
    return gridButton;
  }

}
