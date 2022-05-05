package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginViewController extends ViewController
{
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label errorLabel;

    private LoginViewModel loginViewModel;

    protected void init()
    {
        usernameField.textProperty().bindBidirectional(loginViewModel.usernameProperty());
        passwordField.textProperty().bindBidirectional(loginViewModel.passwordProperty());
        errorLabel.textProperty().bind(loginViewModel.errorProperty());
    }

    public void reset()
    {
        loginViewModel.clear();
    }

    @FXML private void clickLogin() throws IOException
    {
        getViewHandler().openView("ProductView");
    }


}
