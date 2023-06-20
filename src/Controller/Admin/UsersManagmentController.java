/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Controller.Admin.exceptions.NonexistentEntityException;
import Model.User;
import Model.Users;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class UsersManagmentController implements Initializable {

    // 2 static vars to transfer data from this stage controller to the update stage controller
    public static Users selectedUserToUpdate;
    public static Stage updateStage;
    ////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewUserBtn;
    @FXML
    private Button showAllUsersBtn;
    @FXML
    private Button updateSelectedUserBtn;
    @FXML
    private Button deleteSelectedUserBtn;
    @FXML
    private TableView<Users> usersTableView;
    @FXML
    private TableColumn<Users, Integer> idCol;
    @FXML
    private TableColumn<Users, String> usernameCol;
    @FXML
    private TableColumn<Users, String> passwordCol;
    @FXML
    private TableColumn<Users, String> emailCol;
    @FXML
    private TableColumn<Users, String> genderCol;
    @FXML
    private TableColumn<Users, String> roleCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///////////////////////////////////////////////////////////////////////////
        // make maping between TableView column's and user object attributes .   //
        // TableView { idCol,usernameCol,passwordCol,emailCol,genderCol,roleCol }//
        //              |       |           |           |       |        |       //
        // UserObject{ id   ,username   ,password   ,email   ,gender   ,role    }//
        ///////////////////////////////////////////////////////////////////////////
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory("password"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        roleCol.setCellValueFactory(new PropertyValueFactory("role"));
    }
    
    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
       
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    
    @FXML
    private void showOperationsPage(ActionEvent event) {
      
        
    }

    
    @FXML
    private void showUserCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateUser();
    }
    
    @FXML
    private void showAllUsers(ActionEvent event) throws SQLException, ClassNotFoundException {
        usersTableView.getItems().clear();
        ObservableList<Users> usersList = FXCollections.observableArrayList(User.getAllUsers());
        usersTableView.setItems(usersList);
    }

    
    @FXML
    private void updateSelectedUser(ActionEvent event) throws IOException {
        if (usersTableView.getSelectionModel().getSelectedItem() != null) {   
            selectedUserToUpdate = usersTableView.getSelectionModel().getSelectedItem();
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateUserPage.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updateUserScene = new Scene(rootUpdate);
            updateStage = new Stage();
            updateStage.setScene(updateUserScene);
            updateStage.setTitle("Update user " + selectedUserToUpdate.getUsername());
            updateStage.show();
        }
    }
    
    @FXML
    private void deleteSelectedUser(ActionEvent event) throws SQLException {
        if (usersTableView.getSelectionModel().getSelectedItem() != null) {
            Users selectedUser = usersTableView.getSelectionModel().getSelectedItem();

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    UsersJpaController controller = new UsersJpaController(PersistenceManager.getInstance().getEntityManagerFactory());

                    Integer id = selectedUser.getId();
                    try {
                        controller.destroy(id);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("User deleted");
                    deletedSuccessAlert.setContentText("User deleted");
                    deletedSuccessAlert.show();
                }
            });
            
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an user");
            warnAlert.setContentText("Please select an user from the table view");
            warnAlert.show();
        }
    }
    
}
