package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Order;

public class AddressViewModel
{
    private StringProperty address1;
    private StringProperty address2;
    private StringProperty city;
    private IntegerProperty postalCode;
    private StringProperty email;
    private Model model;

    public AddressViewModel(Model model, Order order)
    {
        this.model = model;
        address1 = new SimpleStringProperty(order.getAddress1());
        address2 = new SimpleStringProperty(order.getAddress2());
        city = new SimpleStringProperty(order.getCity());
        postalCode = new SimpleIntegerProperty(order.getPostalCode());
        email = new SimpleStringProperty(order.getEmail());
    }

    public void submit()
    {
        model.submit();
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

    public IntegerProperty getPostalCode()
    {
        return postalCode;
    }

    public StringProperty getEmail()
    {
        return email;
    }
}
