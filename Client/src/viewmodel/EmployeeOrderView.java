package viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Item;
import model.Model;
import model.ModelEmployee;
import model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EmployeeOrderView implements PropertyChangeListener
{
    private StringProperty address1;
    private StringProperty address2;
    private StringProperty city;
    private StringProperty postalCode;
    private StringProperty email;
    private ObservableList<EmployeeOrderTableVM> employeeOrders;
    private LongProperty orderNo;
    private Model model;
    private ModelEmployee modelEmployee;

    public EmployeeOrderView(Model model, ModelEmployee modelEmployee)
    {
        this.address1 = new SimpleStringProperty();
        this.address2 = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.postalCode = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.employeeOrders = FXCollections.observableArrayList();
        this.orderNo = new SimpleLongProperty();
        this.model = model;
        this.modelEmployee = modelEmployee;
        //Should i put sth into "()"??
        clear();
        update();
    }

    public void remove(Product product)
    {
        for (int i = 0; i < employeeOrders.size(); i++)
        {
            if (employeeOrders.get(i).getProductName() == product.getProductName())
            {
                employeeOrders.remove(i);
                break;
            }
        }
    }

    public void clear()
    {
        address1.set("");
        address2.set("");
        city.set("");
        postalCode.set("");
        email.set("");
        orderNo.set(0);
    }

    public void update()
    {
        employeeOrders.clear();
        for (Product product : model.getItemsByProduct(product))
        {
            add(item);
        }
        //sth like this tomaszenko dont be mad im trying
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        //idk
    }

    public void add(Product product)
    {
        employeeOrders.add(new EmployeeOrderTableVM(product));
    }

    public StringProperty getAddress1()
    {
        return address1;
    }

    public StringProperty getAddress2()
    {
        return address2;
    }

    public StringProperty getCity()
    {
        return city;
    }

    public StringProperty getPostalCode()
    {
        return postalCode;
    }

    public StringProperty getEmail()
    {
        return email;
    }

    public ObservableList<EmployeeOrderTableVM> getEmployeeOrders()
    {
        return employeeOrders;
    }

    public LongProperty getOrderNo()
    {
        return orderNo;
    }
}
