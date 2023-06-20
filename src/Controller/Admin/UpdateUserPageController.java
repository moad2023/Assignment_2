/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.User;
import Model.Users;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class UpdateUserPageController implements Initializable {

    private Users oldUser;
    
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private RadioButton userRadio;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private RadioButton adminRadio;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        this.oldUser = Controller.Admin.UsersManagmentController.selectedUserToUpdate;
        
        usernameTF.setText(oldUser.getUsername());
        emailTF.setText(oldUser.getEmail());
        passwordTF.setText(oldUser.getPassword());
        
        if (oldUser.getGender().equals("female")) {
            genderGroup.selectToggle(femaleRadio);
        }
        
        if (oldUser.getRole().equals("admin")) {
            roleGroup.selectToggle(adminRadio);
        }
    }    

    @FXML
    private void updateUser(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String email = emailTF.getText();
        String gender = ((RadioButton)genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton)roleGroup.getSelectedToggle()).getText();
         UsersJpaController controller = new UsersJpaController(PersistenceManager.
                 getInstance().getEntityManagerFactory());
         

        this.oldUser.setUsername(username);
       this.oldUser.setPassword(password);
        this.oldUser.setEmail(email);
        this.oldUser.setGender(gender);
        this.oldUser.setRole(role);
        
         controller.edit(this.oldUser);
         
        Controller.Admin.UsersManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelCreation(ActionEvent event) {
       Controller.Admin.UsersManagmentController.updateStage.close(); 
    }
    
}
