package view;

import Controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class View implements FXComponent {
  ControllerImpl controller;
  private final int players;

  public View(ControllerImpl controller) {
    this.controller = controller;
    this.players = controller.getPlayerCount();
  }

  @Override
  public Parent render() {

    BorderPane layout = new BorderPane();
    Insets insets = new Insets(10);




    Tray leftTray = new Tray(controller, 0);
    Node leftTrayNode = leftTray.render();
    layout.setLeft(leftTrayNode);
    layout.setMargin(leftTrayNode, insets);

    if(players == 2){
      Tray rightTray = new Tray(controller, 1);
      Node rightTrayNode = rightTray.render();
      layout.setRight(rightTrayNode);
      layout.setMargin(rightTrayNode, insets);
    }

    DisplayBoard board = new DisplayBoard(controller);
    Node boardNode = board.render();
    layout.setCenter(boardNode);
    layout.setMargin(boardNode, insets);

    OverheadDisplay display = new OverheadDisplay(controller);
    Node displayNode = display.render();
    layout.setTop(displayNode);
    layout.setMargin(displayNode, insets);


    return layout;
  }
}

