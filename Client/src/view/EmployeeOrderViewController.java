package Client.src.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;

import java.io.IOException;

public class EmployeeOrderViewController extends ViewController
{
    @FXML private TableView<SomeTableVM> table;
    @FXML private TableColumn<SomeTableVM, String> nameOfProduct;
    @FXML private TableColumn<SomeTableVM, Number> quantity;
    @FXML private TableColumn<SomeTableVM, String> expiration;
    @FXML private TableColumn<SomeTableVM, Number> price;
    @FXML private Label orderLabel;
    @FXML private TextArea textArea;

    private EmployeeOrderViewModel employeeOrderViewModel;

    @Override
    protected void init()
    {
        employeeOrderViewModel = getViewModelFactory().getEmployeeOrderViewModel();

        table.setItems(employeeOrderViewModel.getOrderView());

        nameOfProduct.setCellValueFactory(cellData -> cellData.getValue().getProductName());
        quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
        expiration.setCellValueFactory(cellData -> cellData.getValue().getExpiration());
        price.setCellValueFactory(cellData -> cellData.getValue().getPrice());

        orderLabel.textProperty().bind(employeeOrderViewModel.getOrderLabel());
        textArea.textProperty().bind(employeeOrderViewModel.getOrderDescription());
        textArea.setEditable(false);
    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("EmployeeViewOrderList");

        //goes back to the overview of all the orders registered in the system
    }

    @FXML private void removeButton() throws IOException
    {
        employeeOrderViewModel.removeOrderFromView();
        //same remove method as in view order
    }
    
    @FXML public void reset()
    {
        employeeOrderViewModel.clear();
    }
}
