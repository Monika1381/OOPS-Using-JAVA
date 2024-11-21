/*package com.crimedata.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Crime {

    private IntegerProperty id;
    private StringProperty type;
    private StringProperty location;
    private StringProperty date;

    public Crime(int id, String type, String location, String date) {
        this.id = new SimpleIntegerPropety(id);
        this.type = new SimpleStringProperty(type);
        this.location = new SimpleStringProperty(location);
        this.date = new SimpleStringProperty(date);
    }

    // Getters and Setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}*/
package com.crimedata.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Crime {

    private IntegerProperty id;
    private StringProperty type;
    private StringProperty location;
    private StringProperty date;

    public Crime(int id, String type, String location, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.location = new SimpleStringProperty(location);
        this.date = new SimpleStringProperty(date);
    }

    // Getters and Setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    // Property methods for binding
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty dateProperty() {
        return date;
    }
}

