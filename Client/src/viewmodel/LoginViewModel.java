package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ModelEmployee;

public class LoginViewModel
{
    private StringProperty username;
    private StringProperty password;
    private StringProperty errorLabel;
    private ModelEmployee model;

    public LoginViewModel(ModelEmployee model)
    {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty();
    }

    public void clear()
    {
        username.set("");
        password.set("");
        errorLabel.set("");
    }

    public StringProperty getUsernameProperty()
    {
        return username;
    }

    public StringProperty getPasswordProperty()
    {
        return password;
    }

    public StringProperty getErrorLabelProperty()
    {
        return errorLabel;
    }

    public boolean login()
    {
        try
        {
            model.getUser(username.get(), password.get());
            return true;
        }
        catch (Exception e)
        {
            errorLabel.set(e.getMessage());
            return false;
        }
    }
}
