package com.crimedata.controllers;
import com.crimedata.models.IncidentReport;
import com.crimedata.utils.DatabaseUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class IncidentReportsController {

    @FXML
    private TableView<IncidentReport> incidentReportsTable;
    
    @FXML
    private TableColumn<IncidentReport, Integer> reportIdColumn;
    @FXML
    private TableColumn<IncidentReport, Integer> crimeIdColumn;
    @FXML
    private TableColumn<IncidentReport, String> reportDescriptionColumn;
    @FXML
    private TableColumn<IncidentReport, String> reportDateColumn;

    @FXML
    private TextField reportIdField;
    @FXML
    private TextField crimeIdField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField dateField;

    private List<IncidentReport> reportsList = new ArrayList<>();

    @FXML
    public void initialize() {
        // Set up TableView columns
        reportIdColumn.setCellValueFactory(new PropertyValueFactory<>("report_Id"));
        crimeIdColumn.setCellValueFactory(new PropertyValueFactory<>("crime_Id"));
        reportDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("report_notes"));
        reportDateColumn.setCellValueFactory(new PropertyValueFactory<>("report_date"));

        // Load incident reports from the database
        loadReports();
    }

    @FXML
    private void handleSubmitReport() {
        try {
            // Get input values
            int reportId = Integer.parseInt(reportIdField.getText());
            int crimeId = Integer.parseInt(crimeIdField.getText());
            String description = descriptionField.getText();
            String date = dateField.getText();

            // Insert the new report into the database
            addReportToDatabase(reportId, crimeId, description, date);

            // Clear the input fields after submission
            reportIdField.clear();
            crimeIdField.clear();
            descriptionField.clear();
            dateField.clear();

            // Reload reports from the database
            loadReports();

            // Optionally, show success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Report Submitted");
            alert.setHeaderText("Incident Report Added");
            alert.setContentText("The incident report has been successfully added.");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric for ID fields)
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Input Error");
            alert.setContentText("Please enter valid numbers for Report ID and Crime ID.");
            alert.showAndWait();
        }
    }

    private void addReportToDatabase(int reportId, int crimeId, String description, String date) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // SQL query to insert the report into the database
            String query = "INSERT INTO incidentreports (report_id, crime_id, report_notes, report_date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reportId);
            stmt.setInt(2, crimeId);
            stmt.setString(3, description);
            stmt.setString(4, date);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Error Adding Report");
            alert.setContentText("There was an error adding the incident report to the database.");
            alert.showAndWait();
        }
    }

    private void loadReports() {
        reportsList.clear(); // Clear the list before adding updated data
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM incidentreports";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Loop through the result set and populate the reports list
            while (rs.next()) {
                IncidentReport report = new IncidentReport(
                        rs.getInt("report_id"),
                        rs.getInt("crime_id"),
                        rs.getString("report_notes"),
                        rs.getString("report_date")
                );
                reportsList.add(report);
            }

            // Bind the list to the TableView
            incidentReportsTable.getItems().setAll(reportsList);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Error Loading Reports");
            alert.setContentText("There was an error loading the incident reports from the database.");
            alert.showAndWait();
        }
        
    }
}
