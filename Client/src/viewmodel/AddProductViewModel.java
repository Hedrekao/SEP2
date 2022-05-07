package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import model.Category;
import model.Date;
import model.Model;
import model.ModelEmployee;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddProductViewModel
{
    private StringProperty productName;
    private LongProperty productNumber;
    private DoubleProperty price;
    private ObjectProperty<LocalDate> expirationDate;
    private IntegerProperty quantity;
    private StringProperty errorLabel;
    private ObservableList<String> pickedCategory;
    private ModelEmployee modelEmployee;

    public AddProductViewModel(Model model)
    {
        this.modelEmployee = model;
        productName = new SimpleStringProperty();
        productNumber = new SimpleLongProperty();
        price = new SimpleDoubleProperty();
        expirationDate = new SimpleObjectProperty<>();
        quantity = new SimpleIntegerProperty();
        errorLabel = new SimpleStringProperty();
        pickedCategory = FXCollections.observableArrayList();
        clear();
    }

    public void clear()
    {
        productName.set("");
        productNumber.set(0);
        price.set(0);
        quantity.set(0);
        errorLabel.set("");
        expirationDate.set(null);
    }

    public void addCategory(String s)
    {
        pickedCategory.add(s);
    }

    public void removeCategory(String s)
    {
        pickedCategory.remove(s);
    }

    public void addProduct()
    {
        LocalDate localdate = expirationDate.get();
        Date date = new Date(localdate.getDayOfMonth(), localdate.getMonthValue(),
            localdate.getYear());

        ArrayList<Category> categories = new ArrayList<>();
        for(String s: pickedCategory)
        {
            categories.add(new Category(s));
        }
        try
        {
            modelEmployee.addItem(productName.get(), (int) productNumber.get(), price.get(),date, quantity.get(),  categories);
            clear();
        }
        catch (Exception e)
        {
            errorLabel.set(e.getMessage());
        }
    }

    public StringProperty getProductNameProperty()
    {
        return productName;
    }

    public LongProperty getProductNumberProperty()
    {
        return productNumber;
    }

    public DoubleProperty getPriceProperty()
    {
        return price;
    }

    public ObjectProperty<LocalDate> getExpirationDateProperty()
    {
        return expirationDate;
    }

    public IntegerProperty getQuantityProperty()
    {
        return quantity;
    }

    public StringProperty getErrorLabelProperty()
    {
        return errorLabel;
    }

    public ObservableList<String> getPickedCategoryProperty()
    {
        return pickedCategory;
    }
}
