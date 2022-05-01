package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Model;

import java.util.Map;

public class BagViewModel
{
    private StringProperty errorProperty;
    private StringProperty priceProperty;
    private ObservableList<ItemsTableVM> items;
    private Model model;

    public BagViewModel(Model model)
    {
        this.model = model;
        errorProperty = new SimpleStringProperty();
        priceProperty = new SimpleStringProperty();
        items = FXCollections.observableArrayList();


        update();
    }

    public void update()
    {
        items.clear();

            for (Map.Entry<Item, Integer> entry : model.getCurrentOrder().getItems().entrySet())
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
        priceProperty.set("Total price: " + model.getCurrentOrder().getTotalPrice() +"DKK");
        update();
    }

    public void checkout()
    {
        model.completeOrder(model.getCurrentOrder());
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
