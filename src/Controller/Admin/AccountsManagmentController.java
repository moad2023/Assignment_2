/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import static Controller.Admin.UsersManagmentController.selectedUserToUpdate;
import static Controller.Admin.UsersManagmentController.updateStage;
import Model.Accounts;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AccountsManagmentController implements Initializable {

    public static Accounts selectedUserToUpdate;
    public static Stage updateStage;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableColumn<Accounts, Integer> AccIdCol;
    @FXML
    private TableColumn<Accounts, Integer> AccNumberCol;
    @FXML
    private TableColumn<Accounts, String> UsernameCol;
    @FXML
    private TableColumn<Accounts, String> currencyCol;
    @FXML
    private TableColumn<Accounts, Double> BalanceCol;
    @FXML
    private TableColumn<Accounts, Date> creationDateCol;
    @FXML
    private TableView<Accounts> accountsTableView;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AccIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        AccNumberCol.setCellValueFactory(new PropertyValueFactory("account_number"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        currencyCol.setCellValueFactory(new PropertyValueFactory("currency"));
        BalanceCol.setCellValueFactory(new PropertyValueFactory("balance"));
        creationDateCol.setCellValueFactory(new PropertyValueFactory("creation_date"));
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
        accountsTableView.getItems().clear();
        List<Accounts> allAccounts = Accounts.getAllAccounts();
        ObservableList<Accounts> list = FXCollections.observableArrayList(allAccounts);
        accountsTableView.setItems(list);

    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {

    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }

}
