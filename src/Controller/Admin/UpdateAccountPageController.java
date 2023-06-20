/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Accounts;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdateAccountPageController implements Initializable {
private Accounts oldAccount;
    @FXML
    private TextField usernameTF;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateUser(ActionEvent event) throws Exception {
        AccountsJpaController controller = new AccountsJpaController(PersistenceManager.getInstance().getEntityManagerFactory());
       
        this.oldAccount = Controller.Admin.AccountsManagmentController.selectedUserToUpdate;
         String newUsername = usernameTF.getText();
           this.oldAccount.setUsername(newUsername);
           
        
        controller.edit(this.oldAccount);
        
         Controller.Admin.UsersManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelCreation(ActionEvent event) {
    }
    
}
