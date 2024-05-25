/**
Group Member Names: Aum Thakkar and Vandan Patel
Group Member Student Numbers: Aum-991678374 and Vandan-991667487
Final Project
Date- 09/04/2023
*/
package content;


/*This class works on encapsulation of all the data field
used in the code to get and set as per the requirements*/
public class Employee {
    private int id;
    private String name;
    private String city;
    private String position;
    
    public Employee(int id, String name, String city, String position) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.position = position;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
}
    

