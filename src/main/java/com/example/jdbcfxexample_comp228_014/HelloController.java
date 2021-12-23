package com.example.jdbcfxexample_comp228_014;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class HelloController {
    @FXML
    private Button dropButton;
    @FXML
    private Button createButton;
    @FXML
    private TextField studentIdField;
    @FXML
    private TextField studentNameField;
    @FXML
    private Button addButton;
    @FXML
    private TableView table;
    @FXML
    private TableColumn studentIdColumn;
    @FXML
    private TableColumn studentNameColumn;
    @FXML
    private Button deleteButton;

    public void initialize() throws SQLException{

        populateData();
    }

    public void onDrop(ActionEvent actionEvent) throws SQLException {
        DBUtil.dropTable("COMP228_014");
    }

    public void onCreate(ActionEvent actionEvent) throws SQLException{
        DBUtil.createTable("COMP228_014");
    }

    public void onAdd(ActionEvent actionEvent) throws SQLException{
        DBUtil.insertData("COMP228_014", parseInt(studentIdField.getText()), studentNameField.getText());
        populateData();
    }

    public void onDelete(ActionEvent actionEvent) throws SQLException{
        Student student = (Student) table.getSelectionModel().getSelectedItem();
        DBUtil.deleteData("COMP228_014", student.getS_id());
        populateData();
    }

    public void populateData() throws SQLException{
        ResultSet rs = DBUtil.query("COMP228_014", "SELECT * FROM");
        //create list of objects that needs to go to the table
        ObservableList<Student> students = FXCollections.observableArrayList();

        while (rs.next()){
            Student student = new Student(rs.getInt("s_id"), rs.getString("s_name"));
            students.add(student);
            System.out.println(rs.getString("s_id")+ rs.getString("s_name"));
        }
        // assign each attribute of our class to each column of the table
        studentIdColumn.setCellValueFactory(new PropertyValueFactory("s_id"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory("s_name"));
        // clear the table
        table.getItems().clear();

        // add the list to the table

        table.getItems().addAll(students);
        //sort data
        studentIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(studentIdColumn);
        table.sort();
    }
}