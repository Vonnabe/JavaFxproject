/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

//Making a class Workers in order to transfer the database date into and object of the class Workers
public class Workers {
    //Making properties for the data can be store in a tableview of the JavaFX programm
    private SimpleIntegerProperty nameId;
    private SimpleStringProperty name;
    private SimpleStringProperty position;
    private SimpleStringProperty task;
    private SimpleStringProperty area;

    //Making a Workers Function
    public Workers(int nameId, String string, String string2, String string3, String string4) {
        this.nameId =new SimpleIntegerProperty(nameId);
        this.name = new SimpleStringProperty(string);
        this.position = new SimpleStringProperty(string2);
        this.task = new SimpleStringProperty(string3);
        this.area = new SimpleStringProperty(string4);
    }

    //Making getters and setter for the variables
    public int getnameId() {
        return nameId.get();
    }

    public void setnameId(int nameId) {
        this.nameId = new SimpleIntegerProperty(nameId);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String string) {
        this.name = new SimpleStringProperty(string);
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(String position) {
        this.position = new SimpleStringProperty(position);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task = new SimpleStringProperty(task);
    }

    public String getArea() {
        return area.get();
    }

    public void setArea(String area) {
        this.area = new SimpleStringProperty(area);
    }
    
    
    
    
    
}
