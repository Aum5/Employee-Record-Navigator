/**
Group Member Names: Aum Thakkar and Vandan Patel
Group Member Student Numbers: Aum-991678374 and Vandan-991667487
Final Project
Date- 09/04/2023
*/
package employeefx;

import content.Employee;
import content.EmployeeFile;
import content.SearchStage;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    private Employee currentEmployee;
    public static  LinkedList<Employee> employeeList = new LinkedList();
    public static final Label lblID = new Label(" ID: ");
    public static final TextField txtID = new TextField();
    public static final Label lblName = new Label(" Name: ");
    public static final TextField txtName = new TextField();
    public static final Label lblCity = new Label(" City: ");
    public static final TextField txtCity = new TextField();
    public static final Label lblPos = new Label(" Position: ");
    public static final TextField txtPos = new TextField();
    
    public static final Button btnFirst = new Button("First");
    public static final Button btnNext = new Button("Next");
    public static final Button btnpre = new Button("Previous");
    public static final Button btnLast = new Button("Last");
    public static final Button btnsearch = new Button("Search");
    public static final Button btnAdd = new Button("Add");
    public static final Button btnDelete = new Button("Delete");
    public static final Button btnUp = new Button("Update");
    public static int currentIndex = 0;
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(employeePane(), 350, 300);
        
    employeeList = EmployeeFile.readStatus(employeeList);

    // If the list is not empty, set the current employee to the first employee in the list
    if(!employeeList.isEmpty()) {
    currentEmployee = employeeList.getFirst();
    currentIndex = 0;
    
    // Set the text fields to the values of the first employee
    txtID.setText(String.valueOf(currentEmployee.getId()));
    txtName.setText(currentEmployee.getName());
    txtCity.setText(currentEmployee.getCity());
    txtPos.setText(currentEmployee.getPosition());
}

        btnAdd.setOnAction(new EmployeeFile.Addrecord());
        btnFirst.setOnAction(new EmployeeFile.Firstrecord()); 
        btnLast.setOnAction(new EmployeeFile.Lastrecord()); 
        btnNext.setOnAction(new EmployeeFile.Nextrecord());    
        btnpre.setOnAction(new EmployeeFile.Prevrecord());
        btnDelete.setOnAction(new EmployeeFile.Deleterecord());
        btnsearch.setOnAction(new SearchStage.SrcStg());
        btnUp.setOnAction(new EmployeeFile.UpdateRecord());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
        
            try {
                EmployeeFile.ClearAll();
            EmployeeFile.saveAllStatus(employeeList);
            } catch (Exception e) {
            }
});
    }
   
    private GridPane employeePane() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10));

    HBox navButtons = new HBox(50);
    navButtons.setAlignment(Pos.CENTER);
    navButtons.getChildren().addAll(btnFirst, btnNext, btnpre, btnLast);
    grid.add(navButtons, 0, 0, 4, 1);

    VBox labelBox = new VBox(10);
    labelBox.setAlignment(Pos.CENTER_RIGHT);
    labelBox.getChildren().addAll(lblID, lblName, lblCity, lblPos);
    grid.add(labelBox, 0, 1);

    VBox textBox = new VBox(10);
    textBox.setAlignment(Pos.CENTER_LEFT);
    textBox.getChildren().addAll(txtID, txtName, txtCity, txtPos);
    grid.add(textBox, 1, 1);

    HBox searchBox = new HBox(10);
    searchBox.setAlignment(Pos.CENTER);
    searchBox.getChildren().addAll(btnsearch);
    grid.add(searchBox, 0, 2, 4, 1);

    HBox editButtons = new HBox(50);
    editButtons.setAlignment(Pos.CENTER);
    editButtons.getChildren().addAll(btnAdd, btnDelete, btnUp);
    grid.add(editButtons, 0, 3, 4, 1);

 
    
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
   
   
    public static void main(String[] args) {
    Application.launch(args);
    }
}