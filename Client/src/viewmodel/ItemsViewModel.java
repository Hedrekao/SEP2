package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Date;
import model.Item;
import model.ModelUser;
import model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ItemsViewModel implements PropertyChangeListener
{
    private ModelUser model;
    private StringProperty errorProperty;
    private StringProperty productIdProperty;
    private StringProperty productNameProperty;
    private ObservableList<ItemsTableVM> items;
    private StringProperty bagCounter;
    private ItemViewState itemViewState;
    private ShopViewState shopViewState;

    public ItemsViewModel(ModelUser model, ItemViewState itemViewState, ShopViewState shopViewState)
    {
        this.model = model;
        this.shopViewState = shopViewState;
        this.itemViewState = itemViewState;
        errorProperty = new SimpleStringProperty();
        items = FXCollections.observableArrayList();
        bagCounter = new SimpleStringProperty();
        productIdProperty = new SimpleStringProperty();
        productNameProperty = new SimpleStringProperty();
        this.model.addListener(this);

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
        for (Item item : model.getItemsByProduct(shopViewState.getShopAddress(),product))
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
        Item item = model.getSpecificItem(shopViewState.getShopAddress(),new Date(selectedItem.getDateProperty().get()), itemViewState.getProduct().getProductID());
        model.addItemToOrder(shopViewState.getShopAddress(), item);
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

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("StockUpdate"))
        {
            update(itemViewState.getProduct());
        }
    }
}
