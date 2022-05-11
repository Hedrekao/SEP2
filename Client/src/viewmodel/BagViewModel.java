package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.ModelUser;

import java.util.Map;

public class BagViewModel
{
    private StringProperty errorProperty;
    private StringProperty priceProperty;
    private ObservableList<ItemsTableVM> items;
    private ModelUser model;
    private ShopViewState shopViewState;

    public BagViewModel(ModelUser model, ShopViewState shopViewState)
    {
        this.model = model;
        errorProperty = new SimpleStringProperty();
        priceProperty = new SimpleStringProperty();
        items = FXCollections.observableArrayList();
        this.shopViewState = shopViewState;


        update();
    }

    public void update()
    {
        items.clear();

            for (Map.Entry<Item, Integer> entry : model.getOrder().getItems().entrySet())
            {
                int orderQuantity = entry.getValue();
                add(entry.getKey(), orderQuantity);
            }
        }


    private void add(Item item)
    {
        items.add(new ItemsTableVM(item, -1));
    }

    private void add(Item item, int orderQuantity)
    {
        items.add(new ItemsTableVM(item, orderQuantity));
    }


    public void clear()
    {
        errorProperty.set("");
        priceProperty.set("Total price: " + model.getOrder().getTotalPrice() +"DKK");
        update();
    }

    public void checkout()
    {
        //call method taking two arguments I beg
        model.completeOrder(shopViewState.getShopAddress());
    }

    public StringProperty getErrorProperty()
    {
        return errorProperty;
    }

    public StringProperty priceProperty()
    {
        return priceProperty;
    }

    public ObservableList<ItemsTableVM> getItemsList()
    {
        return items;
    }
}
