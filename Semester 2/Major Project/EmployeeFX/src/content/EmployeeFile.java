/**
Group Member Names: Aum Thakkar and Vandan Patel
Group Member Student Numbers: Aum-991678374 and Vandan-991667487
Final Project
Date- 09/04/2023
*/
package content;

import employeefx.Main;
import static employeefx.Main.btnNext;
import static employeefx.Main.employeeList;
import static employeefx.Main.txtCity;
import static employeefx.Main.txtID;
import static employeefx.Main.txtName;
import static employeefx.Main.txtPos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class EmployeeFile extends Main{
    private static int currentIndex;
    private static String currentName;
    private static String currentCity;
    private static String currentPosition;
    
    
   
        

    
    
public static class Deleterecord implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent E) {
        //The current index should be updated to accommodate the employeeList size.
        currentIndex = (currentIndex + employeeList.size()) % employeeList.size();
        
        Employee two = null;
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to add the changes?", ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){
            try {
                int id = Integer.parseInt(txtID.getText());
                for(int i = 0; i < employeeList.size();i++) {
                    if(employeeList.get(i).getId() == id) {
                        //Update the current index and remove the employee from the employeeList.
                        employeeList.remove(i);
                        currentIndex = i - 1; 
                        break;
                    }
                }
            } catch (NumberFormatException e) {
            // Handle the exception here
            }
        btnNext.fire();
        }
    }
}


  public static class Nextrecord implements EventHandler<ActionEvent> {
    
    @Override
    public void handle(ActionEvent E) {
        //System.out.println(currentIndex);
             
        currentIndex = currentIndex + 1;   
        
        //If the current index reaches the end of the employeeList, disable the "Next" button.

        if (currentIndex == employeeList.size() - 1) {
            btnNext.setDisable(true);
        }
        
        btnpre.setDisable(false);
       
        Employee employee = employeeList.get(currentIndex);
        
        // Fill in the appropriate text fields with the employee's information.
        txtID.setText(String.valueOf(employee.getId()));
        txtName.setText(employee.getName());
        txtCity.setText(employee.getCity());
        txtPos.setText(employee.getPosition());
        
    }
}

public static class Prevrecord implements EventHandler<ActionEvent> {
    
    @Override
    public void handle(ActionEvent F) {
        currentIndex = (currentIndex - 1 + employeeList.size()) % employeeList.size();
        
        if (currentIndex == 0) {
            btnpre.setDisable(true);
          }
        
        btnNext.setDisable(false);
        
        Employee employee = employeeList.get(currentIndex);

        txtID.setText(String.valueOf(employee.getId()));
        txtName.setText(employee.getName());
        txtCity.setText(employee.getCity());
        txtPos.setText(employee.getPosition());
    }
}


    public static class Addrecord implements EventHandler<ActionEvent> {

    private String previousID;
    private String previousName;
    private String previousCity;
    private String previousPosition;

    @Override
    public void handle(ActionEvent A){

        // Store the previous values of the text fields
        previousID = txtID.getText();
        previousName = txtName.getText();
        previousCity = txtCity.getText();
        previousPosition = txtPos.getText();

        // Clear the text fields
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
        try {
        int id = 0;
        String name = txtName.getText();
        String city = txtCity.getText();
        String position = txtPos.getText();

        
        // Check if the ID field contains only numeric characters
        if (!txtID.getText().matches("[0-9]+")) {
        throw new IllegalArgumentException("Invalid format: ID field should only contain digits");
        }
  
        id = Integer.parseInt(txtID.getText());

        if (name.matches(".\\d.")){
            throw new IllegalArgumentException("Invalid format: Name fields should not contain digits");
        }
        else if ( city.matches(".\\d.") ) {
            throw new IllegalArgumentException("Invalid format: City fields should not contain digits");
        }
        else if ( position.matches(".\\d.")) {
            throw new IllegalArgumentException("Invalid format: Position fields should not contain digits");
        }
       
        Employee add = new Employee(id, name, city, position);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to add the changes?", ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            int index = employeeList.size();
            employeeList.add(add);
            EmployeeFile.saveStatus(employeeList);
            btnAdd.setDisable(false);
        } else {
            
            btnAdd.setDisable(false);
            txtID.setText(previousID);
            txtName.setText(previousName);
            txtCity.setText(previousCity);
            txtPos.setText(previousPosition);
        }
        } catch (FileNotFoundException e1) {
        Alert dlgError = new Alert(Alert.AlertType.ERROR);
        dlgError.setHeaderText("Data not saved - Program Ending");
        dlgError.show();
        } catch (IOException e2) {
        Alert dlgError = new Alert(Alert.AlertType.ERROR);
        dlgError.setHeaderText("Data not saved - Program Ending");
        dlgError.show();
        } catch (NumberFormatException e3){
        Alert dlgError = new Alert(Alert.AlertType.ERROR);
        dlgError.setHeaderText("Please enter a number in the ID field!");
        dlgError.show();
        } catch (IllegalArgumentException e4) {
        Alert dlgError = new Alert(Alert.AlertType.ERROR);
        dlgError.setHeaderText(e4.getMessage());
        dlgError.show();
    }
}   );

    }

}

    public static class UpdateRecord implements EventHandler<ActionEvent>{
    private String preID=txtID.getText();
    private String preName=txtName.getText();
    private String preCity= txtCity.getText();
    private String prePosition= txtPos.getText();

    @Override
    public void handle(ActionEvent t) {
        
        try {
            String name = txtName.getText();
            String city = txtCity.getText();
            String position = txtPos.getText();

    String idString = txtID.getText();
    for (int i = 0; i < idString.length(); i++) {
    char c = idString.charAt(i);
    if (!Character.isDigit(c)) {
        throw new IllegalArgumentException("Invalid format: ID field should only contain digits");
        }
    }
    int id = Integer.parseInt(idString);
    
    for (int i = 0; i < name.length(); i++) {
    char c = name.charAt(i);
    if (Character.isDigit(c)) {
        throw new IllegalArgumentException("Invalid format: Name fields should not contain digits");
        }
    }

    for (int i = 0; i < city.length(); i++) {
    char c = city.charAt(i);
    if (Character.isDigit(c)) {
        throw new IllegalArgumentException("Invalid format: City fields should not contain digits");
        }
    }

    for (int i = 0; i < position.length(); i++) {
    char c = position.charAt(i);
    if (Character.isDigit(c)) {
        throw new IllegalArgumentException("Invalid format: Position fields should not contain digits");
        }
    }
    
    Employee upEmployee = new Employee(id, name, city, position);

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to update the record?", ButtonType.OK, ButtonType.CANCEL);
    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
    int index = -1;
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).getId() == upEmployee.getId()) {
                    index = i;
                    break;
                    }
                }
         
                    employeeList.set(index, upEmployee);
                    EmployeeFile.saveStatus(employeeList);
               
                    txtID.setText(Integer.toString(upEmployee.getId()));
                    txtName.setText(upEmployee.getName());
                    txtCity.setText(upEmployee.getCity());
                    txtPos.setText(upEmployee.getPosition());
           } 
            else {
                txtID.setText(preID);
                txtName.setText(preName);
                txtCity.setText(preCity);
                txtPos.setText(prePosition);
                
                
   
            }
        } catch (FileNotFoundException e1) {
            Alert dlgError = new Alert(Alert.AlertType.ERROR);
            dlgError.setHeaderText("Data not saved - Program Ending");
            dlgError.show();
        } catch (IOException e2) {
            Alert dlgError = new Alert(Alert.AlertType.ERROR);
            dlgError.setHeaderText("Data not saved - Program Ending");
            dlgError.show();
        } catch (NumberFormatException e3){
            Alert dlgError = new Alert(Alert.AlertType.ERROR);
            dlgError.setHeaderText("Please enter a number in the ID field!");
            dlgError.show();
        } catch (IllegalArgumentException e4) {
            Alert dlgError = new Alert(Alert.AlertType.ERROR);
            dlgError.setHeaderText(e4.getMessage());
            dlgError.show();
        }
    }
}
    
    public static class Lastrecord implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent C) {
    
        if(employeeList.isEmpty()){
            employeeList = EmployeeFile.readStatus(employeeList);
        }
        
        if(employeeList.isEmpty()){
            System.out.println("The employee list is empty");
        }else{
            Employee lstEmployee = employeeList.getLast();
            displayLstEmployee(lstEmployee);
            currentIndex = employeeList.size() - 1;
            
            btnpre.setDisable(false);
            btnNext.setDisable(true);
        }
    }
}

private static void displayLstEmployee(Employee emp) {
    txtID.setText(String.valueOf(emp.getId()));
    txtName.setText(emp.getName());
    txtCity.setText(emp.getCity());
    txtPos.setText(emp.getPosition());
}

public static class Firstrecord implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent D) {
        if(employeeList.isEmpty()){
            employeeList = EmployeeFile.readStatus(employeeList);
        }
        if(employeeList.isEmpty()){
            System.out.println("The employee list is empty");
        }else{
            Employee FirstEmployee = employeeList.getFirst();
            displayFirEmployee(FirstEmployee);
            currentIndex = 0;
            
            btnpre.setDisable(true);
            btnNext.setDisable(false);
        }
    }
}

private static void displayFirEmployee(Employee emp) {
    txtID.setText(String.valueOf(emp.getId()));
    txtName.setText(emp.getName());
    txtCity.setText(emp.getCity());
    txtPos.setText(emp.getPosition());
}

public static void saveStatus(LinkedList<Employee> employeeList) throws IOException {
    FileWriter fw = new FileWriter("employee.dat"); // append data to the file
    PrintWriter pw = new PrintWriter(fw);

    Employee lastEmployee = employeeList.getLast();
    pw.println(lastEmployee.getId() + " , " + lastEmployee.getName() + " , " + lastEmployee.getCity() + " , " + lastEmployee.getPosition());

    pw.close();
    fw.close();
}
    public static void saveAllStatus(LinkedList<Employee> employeeList) throws IOException {
        FileWriter fw = new FileWriter("employee.dat", false); // append data to the file
        PrintWriter pw = new PrintWriter(fw);

        //    Employee lastEmployee = employeeList.getLast();
        for(int i = 0; i < employeeList.size(); i++) {
                pw.println(employeeList.get(i).getId() + " , " + employeeList.get(i).getName() + " , " + employeeList.get(i).getCity() + " , " + employeeList.get(i).getPosition());

        }

        pw.close();
        fw.close();
    }
    
    public static void ClearAll() throws IOException {
        FileWriter fw;
        PrintWriter pw;
        try {
            fw = new FileWriter("employee.dat", false); // append data to the file
            pw = new PrintWriter(fw);
            pw.println();
        pw.close();
        fw.close();
        } catch (Exception e) {
        }
        
        
    }
 
   
    public static LinkedList<Employee> readStatus(LinkedList<Employee> employeeList) {
    File file = new File("employee.dat");
    try {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            if(!tokens[0].isEmpty()){
            int id = Integer.parseInt(tokens[0].trim());
            String name = tokens[1].trim();
            String city = tokens[2].trim();
            String position = tokens[3].trim();
            Employee emp = new Employee(id, name, city, position);
            employeeList.add(emp);
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("Error: File not found.");
        
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        
    }
            return employeeList;

}
}