package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class EditProductViewModel
{
  private StringProperty productName;
  private LongProperty productNumber;
  private DoubleProperty price;
  private ObjectProperty<LocalDate> expirationDate;
  private IntegerProperty quantity;
  private StringProperty errorLabel;
  private ObservableList<String> pickedCategory;
  private ModelEmployee modelEmployee;
  private UserViewState userViewState;
  private ItemViewState itemViewState;

  public EditProductViewModel(Model model, UserViewState userViewState, ItemViewState itemViewState)
  {
    this.modelEmployee = model;
    this.userViewState = userViewState;
    this.itemViewState = itemViewState;
    productName = new SimpleStringProperty(itemViewState.getItem().getProduct().getProductName());
    productNumber = new SimpleLongProperty(itemViewState.getItem().getProduct().getProductID());
    price = new SimpleDoubleProperty(itemViewState.getItem().getCurrentPrice());
    //expirationDate = new SimpleObjectProperty<>(itemViewState.getItem().getExpirationDate()); tom help
    quantity = new SimpleIntegerProperty(itemViewState.getItem().getQuantity());
    errorLabel = new SimpleStringProperty();
    //pickedCategory = FXCollections.observableArrayList(); tom help
    //clear(); imo no
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

  public void editProduct()
  {
    //removal
    modelEmployee.removeItem(userViewState.getShopAddress(), itemViewState.getItem().getExpirationDate(), itemViewState.getItem().getProduct().getProductID() );

    //adding
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
      modelEmployee.addItem(userViewState.getShopAddress(), productName.get(),
          (int) productNumber.get(), price.get(), date, quantity.get(),
          categories);
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
