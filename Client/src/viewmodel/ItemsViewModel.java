package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Date;
import model.Item;
import model.Model;
import model.Product;

public class ItemsViewModel
{
    private Model model;
    private StringProperty errorProperty;
    private StringProperty productIdProperty;
    private StringProperty productNameProperty;
    private ObservableList<ItemsTableVM> items;
    private StringProperty bagCounter;
    private ItemViewState itemViewState;

    public ItemsViewModel(Model model, ItemViewState itemViewState)
    {
        this.model = model;
        this.itemViewState = itemViewState;
        errorProperty = new SimpleStringProperty();
        items = FXCollections.observableArrayList();
        bagCounter = new SimpleStringProperty();
        productIdProperty = new SimpleStringProperty();
        productNameProperty = new SimpleStringProperty();

        clear();

    }

    public void clear()
    {
        errorProperty.set("");
        bagCounter.set("Bag ("+model.getQuantityOfItemsInBag()+")");
        if (itemViewState.getProduct() != null)
        {
            productIdProperty.set(String.valueOf(itemViewState.getProduct().getProductID()));
            productNameProperty.set(itemViewState.getProduct().getProductName());
            update(itemViewState.getProduct());
        }
    }

    public void update(Product product)
    {
        items.clear();
        for (Item item : model.getItemsByProduct(product))
        {
            add(item);
        }
    }

    public void add(Item item)
    {

        items.add(new ItemsTableVM(item, -1));
    }

    public StringProperty getProductNameProperty()
    {
        return productNameProperty;
    }

    public StringProperty getProductIdProperty()
    {
        return productIdProperty;
    }

    public boolean addToBag(ItemsTableVM selectedItem)
    {
        Item item = model.getSpecificItem(new Date(selectedItem.getDateProperty().get()), itemViewState.getProduct().getProductID());
        model.addItemToOrder(item);
        update(itemViewState.getProduct());
        bagCounter.set("Bag ("+model.getQuantityOfItemsInBag()+")");

        return (item.getQuantity() - 1) == 0;
    }

    public StringProperty getErrorProperty()
    {
        return errorProperty;
    }

    public ObservableList<ItemsTableVM> getItems()
    {
        return items;
    }

    public StringProperty getBagCounterProperty()
    {
        return bagCounter;
    }
}
