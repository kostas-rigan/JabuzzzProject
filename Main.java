package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class Main extends Application {
	
	Label label01, label02;
	String team = "";

	
	@Override
	public void start(Stage primaryStage) {
		Stage window= primaryStage;
		Scene gamesetup;
		Scene gamesetup2;
		Scene mainmenu;
		
		window.setTitle("J@buzzz");
		
		label01 = new Label ("Choose");
		label02 = new Label();
		VBox vbox = new VBox(6);
		
		
		vbox.getChildren().add(label01);
		vbox.setAlignment(Pos.CENTER);
		
		Button button01 = new Button();
		button01.setText("Play");
		button01.setStyle("-fx-font: 50 arial; -fx-base: #b6a7c9;"); 
		
		Button button02 = new Button();
		button02.setText("Info");
		button02.setStyle("-fx-font: 50 arial; -fx-base: #b6e7c9;");
		
		Button button03 = new Button();
		button03.setText("Exit");
		button03.setStyle("-fx-font: 50 arial; -fx-base: #b6a7c9;");
		
		vbox.getChildren().add(button01);
		vbox.getChildren().add(button02);
		vbox.getChildren().add(button03);
		
		//Button button04 = new Button();
		//button04.setText("Go back");
		//button04.setStyle("-fx-font: 50 arial; -fx-base: #b6a7c9;"); 
		
		StackPane layout03 = new StackPane();
		layout03.getChildren().add(label02);
		StackPane layout02 = new StackPane ();
		//layout02.getChildren().add(button04);
		layout02.getChildren().add(label01);
		
		
		mainmenu = new Scene(vbox, 400, 400);
		gamesetup = new Scene (layout02, 400, 400);
		gamesetup2 = new Scene(layout03, 400, 400);

		button01.setOnAction(e -> window.setScene(gamesetup));
		//button04.setOnAction(e -> window.setScene(mainmenu));
		button03.setOnAction(e -> System.exit(0));
		
		gamesetup.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				switch(event.getCode()){
				case A:
					//System.out.println("Hello A");
					team = "Team A";
					break;
				case L:
					//System.out.println("Hello L");
					team = "Team B";
					break;
				case ESCAPE:
					System.exit(0);
					break;
				default:
					break;
				}		
				
				label02.setText(team + " is playing");
				window.setScene(gamesetup2);
			}	
		});
		
		gamesetup2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ESCAPE:
					System.exit(0);
					break;
				case B:
					window.setScene(gamesetup);
					break;
				default:
					break;
				}
			}
		});
		
		window.setScene(mainmenu);
		
		window.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
