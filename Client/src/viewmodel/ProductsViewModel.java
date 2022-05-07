package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModelUser;
import model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProductsViewModel implements PropertyChangeListener
{
    private ModelUser model;
    private ObservableList<ProductsTableVM> products;
    private ObservableList<String> pickedCategory;
    private StringProperty bagCounter;
    private ItemViewState itemViewState;

    public ProductsViewModel(ModelUser model, ItemViewState itemViewState)
    {
        this.model = model;
        this.itemViewState = itemViewState;
        this.products = FXCollections.observableArrayList();
        this.pickedCategory = FXCollections.observableArrayList();
        this.bagCounter = new SimpleStringProperty();
        this.model.addListener(this);

        update();
        clear();
    }

    public void update()
    {
        products.clear();
        for (Product product : model.getAllProducts())
        {
           add(product);
        }
    }

    public void add(Product product)
    {
        double lowestPrice = model.getLowestPriceOfProduct(product);
        int quantity = model.getQuantityOfCertainProduct(product);
        if (quantity != 0)
        {
            products.add(new ProductsTableVM(product, lowestPrice, quantity));
        }

    }


    public void clear()
    {
        this.bagCounter.set("Bag (" + model.getQuantityOfItemsInBag()+")");
        update();
    }

    public void addCategory(String s)
    {
        pickedCategory.add(s);
    }

    public void removeCategory(String s)
    {
        pickedCategory.remove(s);
    }

    public ObservableList<ProductsTableVM> getProducts()
    {
        return products;
    }

    public ObservableList<String> getPickedCategory()
    {
        return pickedCategory;
    }

    public StringProperty getBagCounterProperty()
    {
        return bagCounter;
    }

    public void remove(Product product)
    {

        for (int i = 0; i < products.size(); i++)
        {
            if (products.get(i).getId() == product.getProductID())
            {
                products.remove(i);
                break;
            }
        }
    }

    public void chooseProduct(ProductsTableVM productsTableVM)
    {
        Product product = model.getProduct(productsTableVM.getId());
        itemViewState.setProduct(product);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("StockUpdate"))
        {
            update();
        }
    }
}
