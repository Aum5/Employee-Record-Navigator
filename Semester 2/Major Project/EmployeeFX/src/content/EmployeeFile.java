package content;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.stage.Stage;
import javax.swing.JFrame;

public class EmployeeFile {


public static void saveStatus(LinkedList<Employee> employeeList)
            throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("Employee.dat");

        for (Employee emp : employeeList) {
            pw.println(emp.getId() + " " + emp.getName() + " " + emp.getCity()+ " "+ emp.getPosition());
        }
        pw.close();
    }


//first 
//public class EmployeeNavigation extends JFrame {
//    private ArrayList<Employee> employeeList;
//    private final int currentIndex;
//        private Object txtID;
//    
//    public EmployeeNavigation() {
//        super("Employee Navigation");
//        employeeList = new ArrayList<Employee>();
//        currentIndex = 0;
//        loadDataFromFile("Employee.dat");
//        displayEmployee(currentIndex);
//    }
//
//private void loadDataFromFile(String fileName) {
//        BufferedReader reader;
//        try {
//            reader = new BufferedReader(new FileReader(fileName));
//            String line = reader.readLine();
//            while (line != null) {
//                String[] fields = line.split(" ");
//                Employee employee = new Employee(fields[0], fields[1], fields[2], fields[3]);
//                employeeList.add(employee);
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//private void displayEmployee(int index) {
//        Employee employee = employeeList.get(index);
//        txtID.setText(employee.getId());
//        txtName.setText(employee.getName());
//        txtCity.setText(employee.getCity());
//        txtPos.setText(employee.getPosition());
//    }
//    }
//}

// NEW FIRST BTN
//
//public void start(Stage primaryStage){
//btnFirst.setOnAction((ActionEvent e) -> {
//            try {
//                FileInputStream fis = new FileInputStream("Employee.dat");
//                DataInputStream dis = new DataInputStream(fis);
//
//                int id = dis.readInt();
//                byte[] nameBytes = new byte[20];
//                dis.readFully(nameBytes);
//                String name = new String(nameBytes, "UTF-8").trim();
//                byte[] cityBytes = new byte[20];
//                dis.readFully(cityBytes);
//                String city = new String(cityBytes, "UTF-8").trim();
//                byte[] positionBytes = new byte[20];
//                dis.readFully(positionBytes);
//                String position = new String(positionBytes, "UTF-8").trim();
//
//                dis.close();
//                fis.close();
//
//                txtID.setText(Integer.toString(id));
//                txtName.setText(name);
//                txtCity.setText(city);
//                txtPos.setText(position);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//          }
//  
//}
//        }
}