package gr.aueb.dmst.jabuzzz.game;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage primaryStage;
	private static AnchorPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("SKABUZZZ");
		showMainMenu();
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
