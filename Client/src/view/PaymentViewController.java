package Client.src.view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;

import java.io.IOException;

public class PaymentViewController extends ViewController
{
    @FXML private Label titleLabel;
    @FXML private TextField nameField;
    @FXML private TextField numberField;
    @FXML private ComboBox<Number> monthField;
    @FXML private ComboBox<Number> yearField;
    @FXML private TextField securityField;
    @FXML private Label errorLabel;
    @FXML private Button payButton;

    private PaymentViewModel paymentViewModel;

    @Override
    protected void init()
    {
        paymentViewModel = getViewModelFactory().getPaymentViewModel();

        nameField.textProperty().bindBidirectional(paymentViewModel.getName());
        Bindings.bindBidirectional(numberField.textProperty(), paymentViewModel.getNumber(), new NumberStringConverter());
        Bindings.bindBidirectional(securityField.textProperty(), paymentViewModel.getSecurity(), new NumberStringConverter());
        errorLabel.textProperty().bind(paymentViewModel.getErrorLabel());

        monthField.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        yearField.getItems().addAll(2022, 2023, 2024, 2025, 2026, 2027, 2028);
        // limited choice on year selection, since there is only 2-3 years given for credit cards expiration

        monthField.valueProperty().bindBidirectional(paymentViewModel.getMonthField());
        yearField.valueProperty().bindBidirectional(paymentViewModel.getYearField());

        payButton.textProperty().bindBidirectional(paymentViewModel.getPaymentCount());
    }

    @FXML private void clickToPayButton() throws IOException
    {
        getViewHandler().openView("Order");
    }

    public void reset()
    {
        paymentViewModel.clear();
    }


}
