package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade2", "root", "sahan2004");
            PreparedStatement psTm = connection.prepareStatement("Insert Into customer VALUES (?,?,?,?)");

            psTm.setString(1,txtId.getText());
            psTm.setString(2,txtName.getText());
            psTm.setString(3,txtAddress.getText());
            psTm.setDouble(4,Double.parseDouble(txtSalary.getText()));

            if (psTm.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added!!!").show();
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }


        List<Customer> customerList = new ArrayList<>();

    private void loadTable(){
        try {
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade2","root","sahan2004");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from customer");

            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                ));
            }

            ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

           // customerList.forEach(customer -> customerObservableList.add(customer));
           // tblCustomers.setItems(customerObservableList);

            tblCustomers.setItems(FXCollections.observableArrayList(customerList));

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    }
