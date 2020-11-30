package view;

import Controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class View implements FXComponent {
  ControllerImpl controller;

  public View(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {

    BorderPane layout = new BorderPane();
    Insets insets = new Insets(10);

    DisplayBoard board = new DisplayBoard(controller);
    Node boardNode = board.render();
    layout.setCenter(boardNode);
    layout.setMargin(boardNode, insets);


    Tray tray = new Tray(controller);
    Node trayNode = tray.render();
    layout.setRight(trayNode);
    layout.setMargin(trayNode, insets);

    OverheadDisplay display = new OverheadDisplay(controller);
    Node displayNode = display.render();
    layout.setTop(displayNode);
    layout.setMargin(displayNode, insets);


    return layout;
  }
}

