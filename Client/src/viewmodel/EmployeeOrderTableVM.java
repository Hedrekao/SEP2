package viewmodel;

import javafx.beans.property.*;
import model.Product;

public class EmployeeOrderTableVM
{
    private StringProperty productName;
    private IntegerProperty quantity;
    private StringProperty date;
    private DoubleProperty price;

    public EmployeeOrderTableVM(Product product)
    {
        this.productName = new SimpleStringProperty(product.getProductName());
        this.quantity = new SimpleIntegerProperty(product.getQuantity());
        this.date = new SimpleStringProperty(product.getDate());
        this.price = new SimpleDoubleProperty(product.getPrice());
    }

    public String getProductName()
    {
        return productName.get();
    }

    public StringProperty productNameProperty()
    {
        return productName;
    }

    public int getQuantity()
    {
        return quantity.get();
    }

    public IntegerProperty quantityProperty()
    {
        return quantity;
    }

    public String getDate()
    {
        return date.get();
    }

    public StringProperty dateProperty()
    {
        return date;
    }

    public double getPrice()
    {
        return price.get();
    }

    public DoubleProperty priceProperty()
    {
        return price;
    }
}
