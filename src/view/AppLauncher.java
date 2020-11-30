package view;

import Controller.ControllerImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

public class AppLauncher extends Application {

  @Override
  public void start(Stage stage) {
    Model model = new Model();
    ControllerImpl controller = new ControllerImpl(model);
    stage.setTitle("Set");
    View view = new View(controller);

    Scene scene = new Scene(view.render(), 600, 400);
    stage.setScene(scene);

    model.addObserver(
        (Model m) -> {
          stage.setScene(new Scene(view.render()));
        });

    stage.show();
  }
}
