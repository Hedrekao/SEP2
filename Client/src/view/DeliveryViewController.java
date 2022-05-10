package Client.src.view;

import javafx.fxml.FXML;
import view.ViewController;

import java.io.IOException;

public class DeliveryViewController extends ViewController
{
    private DeliveryViewModel deliveryViewModel;
    @Override
    protected void init()
    {
        deliveryViewModel = getViewModelFactory().getDeliveryViewModel();
    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("Bag");
    }

    @FXML private void deliveredButton() throws IOException
    {
        getViewHandler().openView("Address");
    }

    @FXML private void pickUpButton() throws IOException
    {
        getViewHandler().openView("Payment");
    }





}
