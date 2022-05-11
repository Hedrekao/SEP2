package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Order;
import model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderListViewModel implements PropertyChangeListener

{
    private ObservableList<OrderListTableVM> orders;
    private Model model;

    public OrderListViewModel(Model model)
    {
        this.model = model;
        orders = FXCollections.observableArrayList();
    }

    public void remove(Order order)
    {
        for (int i = 0; i < orders.size(); i++)
        {
            if (orders.get(i).getId() == order.getProductID())
            {
                orders.remove(i);
                break;
            }
        }
    }

    public void update()
    {
        // so sad tomaszonko dont be mad pls
    }

    public ObservableList<OrderListTableVM> getOrders()
    {
        return orders;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

    }
}
