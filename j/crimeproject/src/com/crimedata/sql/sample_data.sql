-- Insert sample data into Crimes table
INSERT INTO Crimes (crime_type, crime_date, crime_time, crime_location, crime_description) VALUES
('Burglary', '2024-11-01', '14:30:00', '123 Elm St, City', 'A break-in at a residential property'),
('Robbery', '2024-11-02', '09:15:00', '456 Oak St, City', 'A person was robbed at gunpoint'),
('Assault', '2024-11-03', '18:00:00', '789 Pine St, City', 'A physical attack in a park');

-- Insert sample data into Offenders table
INSERT INTO Offenders (first_name, last_name, age, gender, prior_offenses_count, risk_level, last_arrest_date) VALUES
('John', 'Doe', 30, 'Male', 2, 'High', '2024-10-15'),
('Jane', 'Smith', 25, 'Female', 0, 'Low', '2024-05-20'),
('Mike', 'Johnson', 40, 'Male', 5, 'Very High', '2024-09-10');

-- Insert sample data into Locations table
INSERT INTO Locations (crime_id, address, latitude, longitude) VALUES
(1, '123 Elm St, City', 40.7128, -74.0060),
(2, '456 Oak St, City', 40.7130, -74.0065),
(3, '789 Pine St, City', 40.7135, -74.0070);

-- Insert sample data into IncidentReports table
INSERT INTO IncidentReports (crime_id, officer_name, case_status, report_notes, report_date) VALUES
(1, 'Officer A', 'Open', 'Victim has been interviewed, suspect is still unknown', '2024-11-01'),
(2, 'Officer B', 'Under Investigation', 'Robbery witnessed by a civilian', '2024-11-02'),
(3, 'Officer C', 'Closed', 'Assault case resolved, suspect arrested', '2024-11-03');

-- Insert sample data into Witnesses table
INSERT INTO Witnesses (crime_id, first_name, last_name, statement, contact_info) VALUES
(1, 'Alice', 'Williams', 'I saw a man wearing a black jacket leave the property', 'alice@example.com'),
(2, 'Bob', 'Miller', 'The suspect was armed with a pistol', 'bob@example.com');

-- Insert sample data into Crime_Offender table
INSERT INTO Crime_Offender (crime_id, offender_id) VALUES
(1, 1),  -- John Doe committed Burglary
(2, 2),  -- Jane Smith committed Robbery
(3, 3);  -- Mike Johnson committed Assault

-- Insert sample data into OfficerActivity table
INSERT INTO OfficerActivity (officer_name, report_id, activity_date, activity_description) VALUES
('Officer A', 1, '2024-11-01', 'Patrolled the area near Elm St after the crime'),
('Officer B', 2, '2024-11-02', 'Followed up with witnesses and reviewed security footage'),
('Officer C', 3, '2024-11-03', 'Arrested the suspect for the assault case');
