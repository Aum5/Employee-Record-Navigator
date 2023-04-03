package employeefx;

import content.Employee;
import content.EmployeeFile;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application{
    private final LinkedList<Employee> employeeList = new LinkedList();
    private final Label lblID = new Label(" ID: ");
    private final TextField txtID = new TextField();
    private final Label lblName = new Label(" Name: ");
    private final TextField txtName = new TextField();
    private final Label lblCity = new Label(" City: ");
    private final TextField txtCity = new TextField();
    private final Label lblPos = new Label(" Position: ");
    private final TextField txtPos = new TextField();
    
    private final Button btnFirst = new Button("First");
    private final Button btnNext = new Button("Next");
    private final Button btnpre = new Button("Previous");
    private final Button btnLast = new Button("Last");
    private final Button btnsearch = new Button("Search");
    private final Button btnAdd = new Button("Add");
    private final Button btnDelete = new Button("Delete");
    private final Button btnUp = new Button("Update");
    
     @Override
    public void start(Stage stage) {
        Scene scene = new Scene(employeePane(), 600, 300);
        
        btnAdd.setOnAction(new Addrecord());
        
        stage.setScene(scene);
        stage.show();
    }
   
   private GridPane employeePane() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10));

    // Add navigation buttons to top center
    HBox navButtons = new HBox(50);
    navButtons.setAlignment(Pos.CENTER);
    navButtons.getChildren().addAll(btnFirst, btnNext, btnpre, btnLast);
    grid.add(navButtons, 0, 0, 4, 1);
    
    
    
    // Add labels and text fields
    grid.add(lblID, 0, 1);
    grid.add(txtID, 1, 1);
    grid.add(lblName, 2, 1);
    grid.add(txtName, 3, 1);
    grid.add(lblCity, 0, 2);
    grid.add(txtCity, 1, 2);
    grid.add(lblPos, 2, 2);
    grid.add(txtPos, 3, 2);

    // Add search button below navigation buttons
    HBox searchBox = new HBox(10);
    searchBox.setAlignment(Pos.CENTER);
    searchBox.getChildren().addAll( btnsearch);
    grid.add(searchBox, 0, 3, 4, 1);

    // Add add/delete/update buttons to bottom center
    HBox editButtons = new HBox(50);
    editButtons.setAlignment(Pos.CENTER);
    editButtons.getChildren().addAll(btnAdd, btnDelete, btnUp);
    GridPane.setHalignment(editButtons, HPos.CENTER);
    grid.add(editButtons, 0, 4, 4, 1);

    // Set column constraints
    ColumnConstraints col1 = new ColumnConstraints();
    col1.setHalignment(HPos.RIGHT);
    col1.setPrefWidth(120);
    grid.getColumnConstraints().add(col1);

    ColumnConstraints col2 = new ColumnConstraints();
    col2.setHalignment(HPos.LEFT);
    col2.setPrefWidth(220);
    grid.getColumnConstraints().add(col2);

    ColumnConstraints col3 = new ColumnConstraints();
    col3.setHalignment(HPos.RIGHT);
    col3.setPrefWidth(120);
    grid.getColumnConstraints().add(col3);

    ColumnConstraints col4 = new ColumnConstraints();
    col4.setHalignment(HPos.LEFT);
    col4.setPrefWidth(220);
    grid.getColumnConstraints().add(col4);

    // Set row constraints
    RowConstraints row0 = new RowConstraints();
    row0.setPrefHeight(50);
    grid.getRowConstraints().add(row0);

    RowConstraints row1 = new RowConstraints();
    row1.setPrefHeight(60);
    grid.getRowConstraints().add(row1);

    RowConstraints row2 = new RowConstraints();
    row2.setPrefHeight(60);
    grid.getRowConstraints().add(row2);

    RowConstraints row3 = new RowConstraints();
    row3.setPrefHeight(50);
    grid.getRowConstraints().add(row3);

    RowConstraints row4 = new RowConstraints();
    row4.setPrefHeight(50);
    grid.getRowConstraints().add(row4);
    
    //CSS
    //.setStyle("-fx-background-color: lightblue;");
    lblName.setStyle("-fx-text-fill: red;");
    lblID.setStyle("-fx-text-fill: red;");
    lblCity.setStyle("-fx-text-fill: red;");
    lblPos.setStyle("-fx-text-fill: red;");
    txtID.setStyle("-fx-background-color: lightblue;");
    txtName.setStyle("-fx-background-color: lightblue;");
    txtCity.setStyle("-fx-background-color: lightblue;");
    txtPos.setStyle("-fx-background-color: lightblue;");
    btnsearch.setStyle("-fx-background-color:pink;");
    btnFirst.setStyle("-fx-background-color:greenyellow;");
    btnLast.setStyle("-fx-background-color:greenyellow;");
    btnpre.setStyle("-fx-background-color:greenyellow;");
    btnNext.setStyle("-fx-background-color:greenyellow;");
    btnAdd.setStyle("-fx-background-color:yellow;");
    btnDelete.setStyle("-fx-background-color:yellow;");
    btnUp.setStyle("-fx-background-color:yellow;");
    

    return grid;
}
   private void readFirstRecord() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employee.dat"))) {
            String firstRecord = reader.readLine();
            txtID.setText("First record: " + firstRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   public class Addrecord implements EventHandler<ActionEvent> {
      
       @Override
       
       public void handle(ActionEvent A){
         
        txtID.clear();
        txtName.clear();
        txtCity.clear();
        txtPos.clear();
        txtID.requestFocus();
        
        txtID.setEditable(true);
        txtName.setEditable(true);
        txtCity.setEditable(true);
        txtPos.setEditable(true);
      
        btnAdd.setDisable(true);
        
       btnUp.setOnAction((e) -> {
      try{
    int id = Integer.parseInt(txtID.getText());
        String name = txtName.getText();
        String city = txtCity.getText();
        String  position= txtPos.getText();
        Employee add = new Employee(id,name,city,position);
        
          
    // Display a confirmation dialog box
    Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to add the changes?", ButtonType.OK, ButtonType.CANCEL);
    Optional<ButtonType> result = alert.showAndWait();

    // If the user clicks "OK", add the new Employee object to the employeeList ArrayList
    if (result.get() == ButtonType.OK) {
        int index = employeeList.size();
        employeeList.add(add);
         EmployeeFile.saveStatus(employeeList);
         btnAdd.setDisable(false);
    }
       }catch(FileNotFoundException a) {
                Alert dlgError = new Alert(Alert.AlertType.ERROR);
                dlgError.setHeaderText("Data not saved - Program Ending");
                dlgError.show();
            }
       
    
});
       
       
        }
   }
   
  /* public class Updaterecord implements EventHandler<ActionEvent> {
      
       @Override
       
       public void handle(ActionEvent B){
         try{
    int id = Integer.parseInt(txtID.getText());
        String name = txtName.getText();
        String city = txtCity.getText();
        String  position= txtPos.getText();
        Employee add = new Employee(id,name,city,position);
        
          
    // Display a confirmation dialog box
    Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to add the changes?", ButtonType.OK, ButtonType.CANCEL);
    Optional<ButtonType> result = alert.showAndWait();

    // If the user clicks "OK", add the new Employee object to the employeeList ArrayList
    if (result.get() == ButtonType.OK) {
        int index = employeeList.size();
        employeeList.add(add);
         EmployeeFile.saveStatus(employeeList);
         btnAdd.setDisable(false);
    }
       }catch(FileNotFoundException a) {
                Alert dlgError = new Alert(Alert.AlertType.ERROR);
                dlgError.setHeaderText("Data not saved - Program Ending");
                dlgError.show();
            }
       
        }
   }*/
   
    public static void main(String[] args) {
     Application.launch(args);    
    }    
}
