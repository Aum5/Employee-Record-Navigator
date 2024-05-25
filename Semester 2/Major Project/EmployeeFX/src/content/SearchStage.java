/**
Group Member Names: Aum Thakkar and Vandan Patel
Group Member Student Numbers: Aum-991678374 and Vandan-991667487
Final Project
Date- 09/04/2023
*/
package content;

import static employeefx.Main.employeeList;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class SearchStage {

private static VBox resultsBox;

    public static class SrcStg implements EventHandler<ActionEvent> {

        public static final Label lblCity = new Label(" City: ");
        public static final TextField txtCity = new TextField();
        public static final Label lblPos = new Label(" Position: ");
        public static final TextField txtPos = new TextField();
        public static final Button searchbtn = new Button("Search");

        public void handleSearch(ActionEvent event) {
            String city = txtCity.getText().trim();
            String position = txtPos.getText().trim();

            List<Employee> fillList = new ArrayList<>();
            for (Employee employee : employeeList) {
                if (!city.isEmpty() && !position.isEmpty()) {
                    if (employee.getCity().equalsIgnoreCase(city) 
                            && employee.getPosition().equalsIgnoreCase(position)) {
                        fillList.add(employee);
                    }
                } else if (!city.isEmpty()) {
                    if (employee.getCity().equalsIgnoreCase(city)) {
                        fillList.add(employee);
                    }
                } else if (!position.isEmpty()) {
                    if (employee.getPosition().equalsIgnoreCase(position)) {
                        fillList.add(employee);
                    }
                }
            }

            // Clear previous search results, if any
            resultsBox.getChildren().clear();

            // Display search results
            if (!fillList.isEmpty()) {
                for (Employee employee : fillList) {
                    Label lbl = new Label("Id: " + employee.getId() + ", Name: " + employee.getName()
                            + ", City: " + employee.getCity()
                            + ", Position: " + employee.getPosition());
                    resultsBox.getChildren().add(lbl);
                }
            } else {
                Label lbl = new Label("No records found.");
                resultsBox.getChildren().add(lbl);
            }
        }

        @Override
        public void handle(ActionEvent t) {

            Stage searchResults = new Stage();
            searchResults.setTitle("Search Results");

            Scene scene = new Scene(srcPane(searchResults), 500, 400);
            searchResults.setScene(scene);
            searchResults.show();
        }
/*
        srcpane sets layout for the searchstage window 
        and by making it grid i  have set alignments and positioning and
        at last I have executed search button from this only
        */
private GridPane srcPane(Stage searchResults) {
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10));

            grid.add(lblCity, 0, 2);
            grid.add(txtCity, 1, 2);
            grid.add(lblPos, 2, 2);
            grid.add(txtPos, 3, 2);

            HBox searchBox = new HBox(10);
            searchBox.setAlignment(Pos.CENTER);
            searchBox.getChildren().addAll(searchbtn);
            grid.add(searchBox, 0, 3, 4, 1);

            resultsBox = new VBox(10);
            resultsBox.setAlignment(Pos.TOP_LEFT);
            grid.add(resultsBox, 0, 4, 4, 1);

            searchbtn.setOnAction(e -> handleSearch(e));

            return grid;
        }
    }
}
