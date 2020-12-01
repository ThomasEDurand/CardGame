package view;

import Controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class OverheadDisplay implements FXComponent{
  private ControllerImpl controller;
  private int playerCount;

  public OverheadDisplay(ControllerImpl controller){
    this.controller = controller;
    this.playerCount = controller.getPlayerCount();
  }


  @Override
  public Parent render() {
    GridPane gridPane = new GridPane();
    Text cardsRemaining = new Text("Cards in deck remaining: " + controller.getCount());
    gridPane.add(cardsRemaining,0, 0);

    Text filler1 = new Text("        " );
    gridPane.add(filler1,1, 0);


    Text filler2 = new Text("        ");
    gridPane.add(filler2, 3, 0);

    if(controller.getCount() > 0) {
      Button drawThree = new Button();
      drawThree.setOnMousePressed(
          (MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
              controller.addCards();
            }
          });

      drawThree.setText("Draw 3");
      gridPane.add(drawThree, 4, 0);
    } else {
      Button endGame = new Button();
      if(playerCount == 1) {
        endGame.setOnMousePressed(
            (MouseEvent event) -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                // Text endText = new Text("You got " + controller.getSets()+ " sets");
                System.out.println("You got " + controller.getSets(0) + " sets");
              }
            });
      } else {
        endGame.setOnMousePressed(
            (MouseEvent event) -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                // Text endText = new Text("You got " + controller.getSets()+ " sets");
                System.out.println("Player one got " + controller.getSets(0) + " sets");
                System.out.println("Player one got " + controller.getSets(1) + " sets");
              }
            });
      }
      endGame.setText("End");
      gridPane.add(endGame, 4, 0);
    }

    return gridPane;
  }
}
