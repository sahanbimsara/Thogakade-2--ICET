package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.*;

public class CustomerFormController {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTable(){
        try {
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade2","root","sahan2004");
            System.out.println(connection);


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from customer");

            while (resultSet.next()) System.out.println(resultSet.getString(4));

            System.out.println(resultSet.next());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}