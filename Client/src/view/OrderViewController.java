package Client.src.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import view.ViewController;

import java.io.IOException;

public class OrderViewController extends ViewController
{
    @FXML private TableView<OrderVM, String> table;
    @FXML private TableColumn<OrderVM, String> nameOfProduct;
    @FXML private TableColumn<OrderVM, Number> quantity;
    @FXML private TableColumn<OrderVM, Number> expiration;
    @FXML private TableColumn<OrderVM, Number> price;
    @FXML private Label orderLabel;

    private OrderViewModel orderViewModel;

    @Override
    protected void init()
    {
        orderViewModel = getViewModelFactory().getOrderViewModel();

        orderLabel.textProperty().bindBidirectional(orderViewModel.getOrderLabel());

        quantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        nameOfProduct.setCellValueFactory(cellData -> cellData.getValue().nameOfProductProperty());
        expiration.setCellValueFactory(cellData -> cellData.getValue().expirationProperty());

        table.setItems(orderViewModel.getOrder());
    }

    @FXML private void homeButton() throws IOException
    {
        getViewHandler().openView("Shop");
    }

    public void reset()
    {
        orderViewModel.clear();
    }
}
