package com.crimedata.controllers;
import com.crimedata.utils.DatabaseUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DashboardController {
    @FXML
    private Label totalCrimes;
    @FXML
    private Label totalOffenders;
    @FXML
    private Label openCases;
    public void initialize() {
        Connection conn = DatabaseUtil.getConnection();
        if (conn != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed!");
        }
        loadStatistics();
    }
    private void loadStatistics() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Define the queries
            String crimeQuery = "SELECT COUNT(*) FROM crimes";
            String offenderQuery = "SELECT COUNT(*) FROM offenders";
            String openCasesQuery = "SELECT COUNT(*) FROM incidentreports WHERE case_status = 'Open'";

            // Execute the queries using PreparedStatements
            try (PreparedStatement crimeStmt = conn.prepareStatement(crimeQuery);
                 PreparedStatement offenderStmt = conn.prepareStatement(offenderQuery);
                 PreparedStatement openCasesStmt = conn.prepareStatement(openCasesQuery)) {

                // Get results for crimes count
                ResultSet crimeResult = crimeStmt.executeQuery();
                if (crimeResult.next()) {
                    totalCrimes.setText(String.valueOf(crimeResult.getInt(1)));
                }

                // Get results for offenders count
                ResultSet offenderResult = offenderStmt.executeQuery();
                if (offenderResult.next()) {
                    totalOffenders.setText(String.valueOf(offenderResult.getInt(1)));
                }

                // Get results for open cases count
                ResultSet openCasesResult = openCasesStmt.executeQuery();
                if (openCasesResult.next()) {
                    openCases.setText(String.valueOf(openCasesResult.getInt(1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();  // Catch other exceptions as well
        }
    }
}
