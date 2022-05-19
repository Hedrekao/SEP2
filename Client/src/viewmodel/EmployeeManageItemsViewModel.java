package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.util.Map;

public class EmployeeManageItemsViewModel
{
    private ObservableList<ItemsTableVM> itemsTableVM;
    private ClientUserModel clientUserModel;

    public EmployeeManageItemsViewModel(ClientUserModel clientUserModel)
    {
        this.itemsTableVM = FXCollections.observableArrayList();
        this.clientUserModel = clientUserModel;

        //not sure how many models do we need

        update();
    }

    public void update() //CHECK tomaszenko
    {
        itemsTableVM.clear();

        for (Map.Entry<Item, Integer> entry : clientUserModel.getOrder().getItems()
                .entrySet())
        {
            int orderQuantity = entry.getValue();
            add(entry.getKey(), orderQuantity);
        }
    }

    private void add(Item item, int orderQuantity)
    {
        itemsTableVM.add(new ItemsTableVM(item, orderQuantity));
    }

    public void removeItem(ItemsTableVM itemsTableVM)
    {
        Item item = clientUserModel.getSpecificItem(clientUserModel.getOrder().getShopAddress(),
                new Date(itemsTableVM.getDateProperty().get()),
                itemsTableVM.getIdProperty().get());

        clientUserModel.removeItemFromBag(item);
    }

    public ObservableList<ItemsTableVM> getItems()
    {
        return itemsTableVM;
    }

    public void clear()
    {
        //nothing to clear - this method is used in Controller
        update();
    }
}
