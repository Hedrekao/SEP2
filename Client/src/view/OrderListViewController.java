package Client.src.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import view.ViewController;

import java.io.IOException;

public class OrderListViewController extends ViewController
{
    @FXML private TableView<OrderListTableVM> table;
    @FXML private TableColumn<OrderListTableVM, Number> orderNumber;
    @FXML private TableColumn<OrderListTableVM, Number> totalPrice;
    @FXML private TableColumn<OrderListTableVM, String> dateOfOrder;

    private OrderListViewModel orderListViewModel;

    @Override
    protected void init()
    {
        orderListViewModel = getViewModelFactory().getOrderListViewModel();

        table.setItems(orderListViewModel.getOrderList());
        orderNumber.setCellValueFactory(cellData -> cellData.getValue().getOrderNo());
        totalPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice());
        dateOfOrder.setCellValueFactory(cellData -> cellData.getValue().getDateOfOrder());

    }

    @FXML private void goBackButton() throws IOException
    {
        getViewHandler().openView("Employee");
    }

    @FXML private void removeButton() throws IOException
    {
        orderListViewModel.removeOrder();
    }

    @FXML private void viewButton() throws IOException
    {
        getViewHandler().openView("EmployeeOrderOverview");

        //to view the separate order in another gui
    }

    public void reset()
    {
        orderListViewModel.clear();
    }
}
