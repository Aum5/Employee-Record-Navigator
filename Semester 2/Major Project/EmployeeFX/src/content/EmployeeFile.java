package content;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class EmployeeFile {


public static void saveStatus(LinkedList<Employee> employeeList)
            throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("Employee.dat");

        for (Employee emp : employeeList) {
            pw.println(emp.getId() + " " + emp.getName() + " " + emp.getCity()+ " "+ emp.getPosition());
        }
        pw.close();
    }    
}
