package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	// This line is a test
//	DBConnection connection;
	
	@Override
	public void start(Stage primaryStage) {
		
		// These lines are a test
//		connection = new DBConnection();
//		connection.connectToDatabase();
//		connection.printTableDataToConsole();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// These lines are a test
//		DBConnection connection = new DBConnection();
//		connection.connectToDatabase();
//		connection.printTableDataToConsole();
		
		// These lines are a separate test
		
		
		launch(args);
	}
}
