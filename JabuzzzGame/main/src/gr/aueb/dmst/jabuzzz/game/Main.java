package gr.aueb.dmst.jabuzzz.game;

import java.io.IOException;

import gr.aueb.dmst.jabuzzz.game.view.MainViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	
	public static Stage primaryStage;
	private static AnchorPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("SKABUZZZ");
		Image ico = new Image("/photos/GameIcon.png");
		Main.primaryStage.getIcons().add(ico);
		showMainMenu();
	}
	
	public static void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		BorderPane bp = loader.load();
		Scene scene = new Scene (bp);
		/*MainViewController mainViewController = new MainViewController();
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                mainViewController.handleBuzzer(event.getCode());
            }
        }); */
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showGameSetUp() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GameSetUp.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene (mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showMainMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene (mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
