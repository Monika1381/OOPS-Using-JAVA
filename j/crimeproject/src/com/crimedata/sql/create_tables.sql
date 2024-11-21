-- Create the CrimeData database (if not already created)
CREATE DATABASE IF NOT EXISTS CrimeData;
-- Use the CrimeData database
USE CrimeData;

-- Crimes Table
CREATE TABLE IF NOT EXISTS Crimes (
    crime_id INT AUTO_INCREMENT PRIMARY KEY,
    crime_type VARCHAR(255),
    crime_date DATE,
    crime_time TIME,
    crime_location VARCHAR(255),
    crime_description TEXT
);

-- Offenders Table
CREATE TABLE IF NOT EXISTS Offenders (
    offender_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    prior_offenses_count INT,
    risk_level VARCHAR(50),
    last_arrest_date DATE
);

-- Locations Table
CREATE TABLE IF NOT EXISTS Locations (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    crime_id INT,
    address VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE,
    FOREIGN KEY (crime_id) REFERENCES Crimes(crime_id)
);

-- Incident Reports Table
CREATE TABLE IF NOT EXISTS IncidentReports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    crime_id INT,
    officer_name VARCHAR(255),
    case_status ENUM('Open', 'Closed', 'Under Investigation'),
    report_notes TEXT,
    report_date DATE,
    FOREIGN KEY (crime_id) REFERENCES Crimes(crime_id)
);

-- Witnesses Table (Optional)
CREATE TABLE IF NOT EXISTS Witnesses (
    witness_id INT AUTO_INCREMENT PRIMARY KEY,
    crime_id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    statement TEXT,
    contact_info VARCHAR(255),
    FOREIGN KEY (crime_id) REFERENCES Crimes(crime_id)
);

-- Crime_Offender Link Table
CREATE TABLE IF NOT EXISTS Crime_Offender (
    crime_id INT,
    offender_id INT,
    PRIMARY KEY (crime_id, offender_id),
    FOREIGN KEY (crime_id) REFERENCES Crimes(crime_id),
    FOREIGN KEY (offender_id) REFERENCES Offenders(offender_id)
);

-- Officer Activity Table
CREATE TABLE IF NOT EXISTS OfficerActivity (
    activity_id INT AUTO_INCREMENT PRIMARY KEY,
    officer_name VARCHAR(255),
    report_id INT,
    activity_date DATE,
    activity_description TEXT,
    FOREIGN KEY (report_id) REFERENCES IncidentReports(report_id)
);
