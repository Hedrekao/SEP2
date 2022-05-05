package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.text.View;
import java.io.IOException;
import java.time.LocalDate;

public class AddProductViewController extends ViewController
{
    @FXML private CheckBox frozen;
    @FXML private CheckBox seafood;
    @FXML private CheckBox diary;
    @FXML private CheckBox bakery;
    @FXML private CheckBox beverages;
    @FXML private CheckBox snacks;
    @FXML private CheckBox fruits;
    @FXML private CheckBox vegetables;
    @FXML private TextField productNameField;
    @FXML private TextField productNumberField;
    @FXML private TextField priceField;
    @FXML private DatePicker dateField;
    @FXML private TextField quantityField;

    private AddProductViewModel addProductViewModel;


    protected void init()
    {
        addProductViewModel = getViewModelFactory().getAddProductViewModel();

        CheckBox [] checkBox = {frozen, seafood, diary, bakery, beverages, snacks, fruits, vegetables};

        for (int i =0; i < 8; i++)
        {

            CheckBox currentCheckBox = checkBox[i];

            EventHandler<ActionEvent> event = actionEvent -> {

                if (currentCheckBox.isSelected())
                {
                    addProductViewModel.addCategory(currentCheckBox.getText());
                }
                else
                {
                    addProductViewModel.removeCategory(currentCheckBox.getText());
                }
            };

            currentCheckBox.setOnAction(event);
        }

        productNameField.textProperty().bindBidirectional(addProductViewModel.getProductNameFieldProperty());
        productNumberField.textProperty().bindBidirectional(addProductViewModel.getProductNumberFieldProperty());
        priceField.textProperty().bindBidirectional(addProductViewModel.getPriceFieldProperty());
        quantityField.textProperty().bindBidirectional(addProductViewModel.getQuantityFieldProperty());

        DatePicker datePicker = new DatePicker();

        dateField.setOnAction(actionEvent ->
        {
            LocalDate date = datePicker.getValue();

        });
    }

    public void reset()
    {
        addProductViewModel.clear();
    }

    @FXML private void addProductButton()
    {
        reset();
        //should update the database and update the user window when viewing the products
    }
}
