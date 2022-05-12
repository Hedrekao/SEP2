package viewmodel;

import javafx.beans.property.*;
import model.Order;

public class OrderListTableVM
{
    private LongProperty orderNo;
    private StringProperty date;
    private DoubleProperty totalPrice;

    public OrderListTableVM(Order order)
    {
        orderNo = new SimpleLongProperty(order.getOrderNo());
        date = new SimpleStringProperty(order.getDate());
        totalPrice = new SimpleDoubleProperty(order.getTotalPrice);
    }

    public LongProperty getOrderNo()
    {
        return orderNo;
    }

    public StringProperty getDate()
    {
        return date;
    }

    public DoubleProperty getTotalPrice()
    {
        return totalPrice;
    }
}
