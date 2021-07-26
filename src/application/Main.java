package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			Parent root = (Parent)loader.load();
			
//			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			
			MainUIController controller = (MainUIController)loader.getController();
			
//			controller.transactionsTable.prefWidthProperty().bind(primaryStage.widthProperty());
//			controller.transactionsTable.prefHeightProperty().bind(primaryStage.heightProperty());
			
//			controller.transactionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
			primaryStage.setTitle("JFXBudget");
			primaryStage.setScene(new Scene(root, 1000, 600));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
