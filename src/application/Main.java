package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	Stage window;
	// Note: Tables require at least one column to be created
	TableView<Product> table;
	
	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		window.setTitle("JFXBudget");
		
		// Create columns - they need 1) the type of data stored in the table and 2) the type of data in the specific column
		TableColumn<Product, String> nameColumn = new TableColumn<>("Name"); // The last "Name" string is the column header name
		TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
		TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
		
		// Set column widths - not necessary - can also be done in the FXML file
		nameColumn.setMinWidth(200);
		priceColumn.setMinWidth(200);
		quantityColumn.setMinWidth(200);
		
		// Connect the data (objects - Product) to the TableView/TableColumns
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // The last parameter here - in this case "name" - must match the variable name in the Product class exactly.
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		// Initializing the table
		table = new TableView<>();
		// Load our data into the table - in this case we are using the getProduct method below
		table.setItems(getProduct());
		// Add columns to the table
		table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
		
		
		// Create our layout
		VBox vBox = new VBox();
		// Add the table to the layout
		vBox.getChildren().addAll(table);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
		
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * - This method grabs the data to be added to the products.
	 * - Normally, this would be connected to a database (online
	 * or offline) or a spreadsheet/CSV on your computer.
	 * 
	 * - The way this method is set up is to create the list,
	 * then create and add all of the items to the list, then
	 * return the list. It can be modified to have the "create and add"
	 * step connect to a database and then create + add the items
	 * from that database, instead of having them be hard-coded
	 * in as is done in this example.
	 */
	public ObservableList<Product> getProduct() {
		// This ObservableList can likely be declared outside this method
		ObservableList<Product> productList = FXCollections.observableArrayList();
		// The items that are created and added here can be from a database - just add a database connection here
		productList.add(new Product("Cat", 99.99, 1000000));
		productList.add(new Product("Porpoise", 999.95, 2));
		productList.add(new Product("Eel", 12.00, 123));
		
		return productList;
	}
}
