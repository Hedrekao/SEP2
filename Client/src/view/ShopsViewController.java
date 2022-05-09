package Client.src.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.ViewController;
import viewmodel.ProductsTableVM;
import viewmodel.ShopsTableVM;

import javax.swing.table.TableColumn;
import javax.swing.text.TabableView;
import java.io.IOException;

public class ShopsViewController extends ViewController
{
    @FXML private TabableView<ShopsTableVM> table;
    @FXML private TableColumn<ShopsTableVM, String> shopName;
    @FXML private TableColumn<ShopsTableVM, String> addressName;
    @FXML private TableColumn<ShopsTableVM, Number> availableProducts;
    @FXML private Button bagButton;

    private ShopsViewModel shopsViewModel;

    @Override
    protected void init()
    {
        shopsViewModel = getViewModelFactory().getShopsViewModel();
        shopName.setCellValueFactory(cellData -> cellData.getValue().getName());
        addressName.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        availableProducts.setCellValueFactory(cellData -> cellData.getValue().getAvailableProducts());

        table.setItems(shopsViewModel.getShops());
        bagButton.textProperty().bindBidirectional(shopsViewModel.getBagCounter());
        
//         selected shop method does not exist in the view model, copy the method from products view model 

        table.setOnMouseClicked(event -> {

            ShopsTableVM selectedShop = table.getSelectionModel().getSelectedItem();
            if (selectedShop != null)
            {
                shopsViewModel.chooseShop(selectedShop);
                try
                {
                    getViewHandler().openView("Product");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    @FXML private void checkBagStatus() throws IOException
    {
        getViewHandler().openView("Bag");
    }

//    @FXML private void loginButton() throws IOException
//    {
//        getViewHandler().openView("LoginUser");
//    }

//     public void reset()
//     {
//         shopsViewModel.clear();
//         table.setItems(shopsViewModel.getProducts());
//
//          (reset method does not exist in the view model)
//     }
}
