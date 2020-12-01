package view;

import Controller.ControllerImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Board;
import model.Model;

public class AppLauncher extends Application {

  @Override
  public void start(Stage stage) {
    int playerCount = 2;
    Board board = new Board();


    Model[] models = new Model[playerCount];
    for(int i = 0; i < playerCount; i++){
      models[i] = new Model(board);
    }
    ControllerImpl controller = new ControllerImpl(models);
    stage.setTitle("Set");
    View view = new View(controller);

    Scene scene = new Scene(view.render(), 600, 400);
    stage.setScene(scene);

    for(int i = 0; i < playerCount; i++) {
      models[i].addObserver(
          (Model m) -> {
            stage.setScene(new Scene(view.render()));
          });
    }

    stage.show();
  }
}
