package viewmodel;

import javafx.beans.property.*;
import model.Model;

import javax.swing.*;

public class PaymentViewModel
{
    private StringProperty name;
    private LongProperty cardNo;
    private IntegerProperty securityCode;
    private Model model;

    public PaymentViewModel(Model model)
    {
        this.model  = model;
        name = new SimpleStringProperty();
        cardNo = new SimpleLongProperty();
        securityCode = new SimpleIntegerProperty();
    }

    public StringProperty getName()
    {
        return name;
    }
    public LongProperty getCardNo()
    {
        return cardNo;
    }

    public IntegerProperty getSecurityCode()
    {
        return securityCode;
    }
}
