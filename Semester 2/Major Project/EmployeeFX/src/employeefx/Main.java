package employeefx;

import content.Employee;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

    
    private final LinkedList<Employee> employeelist = new LinkedList();
    private final Label lblID = new Label(" ID: ");
    private final TextField txtID = new TextField();
    private final Label lblName = new Label(" Name: ");
    private final TextField txtName = new TextField();
    private final Label lblcity = new Label(" City: ");
    private final TextField txtcity = new TextField();
    private final Label lblpos = new Label(" Position: ");
    private final TextField txtpos = new TextField();
    
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
       // btnAdd.setOnAction(new AddStudent());
        //stage.setOnCloseRequest(new EndProgram());
        stage.setScene(scene);
        stage.show();
    }
   
    private GridPane employeePane() {
        GridPane pane = new GridPane();
        pane.add(btnFirst, 2, 0);
       pane.add(lblID, 0, 1);
        pane.add(txtID, 1, 1);
        pane.add(lblName, 0, 2);
        pane.add(txtName, 1, 2);
        pane.add(lblcity, 0, 3);
        pane.add(txtcity, 1, 3);
        pane.add(lblpos, 0, 4);
        pane.add(txtpos, 1, 4);
        pane.add(btnAdd, 1, 9);
        return pane;
    }
    
    
    public static void main(String[] args) {
     Application.launch(args);    
    }    
}
