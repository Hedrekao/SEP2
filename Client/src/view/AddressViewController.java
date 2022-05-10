package Client.src.view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.ViewController;

import java.io.IOException;

public class AddressViewController extends ViewController
{
    @FXML private TextField addressPrimaryField;
    @FXML private TextField addressSecondaryField;
    @FXML private TextField cityField;
    @FXML private TextField postalCodeField;
    @FXML private TextField emailField;

    private AddressViewModel addressViewModel;

    @Override
    protected void init()
    {
        addressViewModel = getViewModelFactory().getAddressViewModel();

        addressPrimaryField.textProperty().bindBidirectional(addressViewModel.getAddress());
        addressSecondaryField.textProperty().bindBidirectional(addressViewModel.getAddressSecond());
        cityField.textProperty().bindBidirectional(addressViewModel.getCity());

        Bindings.bindBidirectional(postalCodeField.textProperty(), addressViewModel.getPostCode(), new NumberStringConverter());
        emailField.textProperty().bindBidirectional(addressViewModel.getEmail());

        //Not sure how to bind email, since it can contain numbers inside
    }

    @FXML
    private void submitButton() throws IOException
    {
        getViewHandler().openView("Payment");
    }

    public void reset()
    {
        addressViewModel.clear();
    }

}
