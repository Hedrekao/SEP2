package viewmodel;

import javafx.beans.property.*;
import model.Order;

public class OrderTableVM
{
    private StringProperty productName;
    private IntegerProperty quantity;
    private StringProperty date;
    private DoubleProperty price;

    public OrderTableVM(Order order)
    {
        productName = new SimpleStringProperty(order.getName());
        quantity = new SimpleIntegerProperty(order.getQuantity());
        date = new SimpleStringProperty(order.getDate().toString());
        price = new SimpleDoubleProperty(order.getTotalPrice());
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
