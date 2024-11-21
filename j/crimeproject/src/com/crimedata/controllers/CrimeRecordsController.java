package com.crimedata.controllers;
import com.crimedata.models.Crime;
import com.crimedata.utils.DatabaseUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class CrimeRecordsController {
    @FXML
    private TableView<Crime> crimeTable;
    @FXML
    private TableColumn<Crime, Integer> crimeIdColumn;
    @FXML
    private TableColumn<Crime, String> crimeTypeColumn;
    @FXML
    private TableColumn<Crime, String> crimeLocationColumn;
    @FXML
    private TableColumn<Crime, String> crimeDateColumn;
    @FXML
    private TextField typeField, locationField, dateField;

    public void initialize() {
        // Setting up columns to bind to Crime properties
        crimeIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        crimeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        crimeLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        crimeDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        // Load initial crime data
        loadCrimes();
    }

    private void loadCrimes() {
        crimeTable.getItems().clear();  // Clear existing items
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM crimes";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Crime crime = new Crime(rs.getInt("crime_id"), rs.getString("crime_type"), rs.getString("crime_location"), rs.getString("crime_date"));
                System.out.println("Adding crime: " + crime.getType());  // Debug print
                crimeTable.getItems().add(crime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addCrime() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO crimes (crime_type, crime_location, crime_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, typeField.getText());
            stmt.setString(2, locationField.getText());
            stmt.setString(3, dateField.getText());
            stmt.executeUpdate();

            // Refresh the list after adding a crime
            loadCrimes();

            // Show success alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Crime Record Added");
            alert.setContentText("The crime record has been successfully added.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            
            // Show error alert if something goes wrong
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Add Crime");
            alert.setContentText("An error occurred while adding the crime record. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteCrime() {
        Crime selectedCrime = crimeTable.getSelectionModel().getSelectedItem();
        if (selectedCrime != null) {
            // Show confirmation alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Crime Record");
            confirmationAlert.setContentText("Are you sure you want to delete the selected crime record?");
            
            // Wait for user response
            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try (Connection conn = DatabaseUtil.getConnection()) {
                    // Use the correct column name in the DELETE query
                    String query = "DELETE FROM crimes WHERE crime_id = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, selectedCrime.getId());  // Ensure you are passing the correct 'crime_id'
                    int rowsAffected = stmt.executeUpdate();
                    
                    // If rows are affected, proceed with the deletion
                    if (rowsAffected > 0) {
                        crimeTable.getItems().remove(selectedCrime);
                        // Show success alert
                        Alert successAlert = new Alert(AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText("Crime Record Deleted");
                        successAlert.setContentText("The crime record has been successfully deleted.");
                        successAlert.showAndWait();
                    } else {
                        // If no rows are affected, something went wrong
                        Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText("Failed to Delete Crime");
                        errorAlert.setContentText("No crime record was deleted. Please try again.");
                        errorAlert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    // Show error alert if something goes wrong
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Failed to Delete Crime");
                    errorAlert.setContentText("An error occurred while deleting the crime record. Please try again.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            // Show alert if no item is selected
            Alert noSelectionAlert = new Alert(AlertType.WARNING);
            noSelectionAlert.setTitle("No Selection");
            noSelectionAlert.setHeaderText("No Crime Record Selected");
            noSelectionAlert.setContentText("Please select a crime record to delete.");
            noSelectionAlert.showAndWait();
        }
    }
}
