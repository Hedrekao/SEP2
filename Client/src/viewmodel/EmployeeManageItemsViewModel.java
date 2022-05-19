package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class EmployeeManageItemsViewModel implements PropertyChangeListener
{
    private ObservableList<ItemsTableVM> itemsTableVM;
    private ModelEmployee modelEmployee;
    private ModelUser modelUser;
    private UserViewState userViewState;

    public EmployeeManageItemsViewModel(Model model, UserViewState userViewState)
    {
        this.itemsTableVM = FXCollections.observableArrayList();
        this.modelEmployee = model;
        this.userViewState = userViewState;
        model.addListener(this);

    }

    public void update()
    {
        itemsTableVM.clear();

        for (Item item : modelEmployee.getAllItemsFromShop(userViewState.getShopAddress()))
        {
            add(item);
        }
    }

    private void add(Item item)
    {
        itemsTableVM.add(new ItemsTableVM(item, -1));
    }

    public void removeItem(ItemsTableVM itemsTableVM)
    {
        modelEmployee.removeItem(userViewState.getShopAddress(), new Date(itemsTableVM.getDateProperty().get()) ,itemsTableVM.getIdProperty().get());
    }

    public ObservableList<ItemsTableVM> getItems()
    {
        return itemsTableVM;
    }

    public void clear()
    {
        update();
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("RemoveItemFromShop"))
        {
            Platform.runLater(this::update);

        }
    }
}
