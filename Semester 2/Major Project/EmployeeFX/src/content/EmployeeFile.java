package content;

import employeefx.Main;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class EmployeeFile extends Main{
    
    
    
    public static class Addrecord implements EventHandler<ActionEvent> {
      
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
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to add the changes?", ButtonType.OK, ButtonType.CANCEL);
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


public static void saveStatus(LinkedList<Employee> employeeList)
            throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("Employee.dat");

        for (Employee emp : employeeList) {
            pw.println(emp.getId() + " " + emp.getName() + " " + emp.getCity()+ " "+ emp.getPosition());
        }
        pw.close();
    }



    }