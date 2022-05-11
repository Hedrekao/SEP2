package model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private ShopList shopList;
  private ModelPersistence modelPersistence;
  private UserList userList;

  public ModelManager() throws ClassNotFoundException
  {
    this.modelPersistence = new ModelDatabase(this);

    this.shopList = modelPersistence.loadShops();

    for (Shop shop : getAllShops())
    {
      shop.setOrderList(modelPersistence.loadOrdersFromShop(shop.getAddress()));

    }

    userList = modelPersistence.loadUsers();
    property = new PropertyChangeSupport(this);
  }

  @Override public ArrayList<Product> getAllProducts(String address)
  {
    return shopList.getAllProducts(address);
  }

  @Override public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    return shopList.getProductsByCategory(address,categories);
  }

  @Override public void completeOrder(String address, Order order)
  {
    shopList.getShop(address).addOrder(order);
    modelPersistence.save(address, order);
  }

  @Override public void addItemToOrder(String address, Item item)
  {
    Item item1 = getSpecificItem(address, item.getExpirationDate(), item.getProduct().getProductID());
    item1.setQuantity(item1.getQuantity() - 1);

    property.firePropertyChange("StockUpdate", null, 1);
  }

  @Override public void removeItemFromOrder(String address, Item item,int quantityOfItem)
  {

    Item item1 = getSpecificItem(address, item.getExpirationDate(), item.getProduct().getProductID());
    item1.setQuantity(item1.getQuantity() + quantityOfItem);

    property.firePropertyChange("StockUpdate", null, 1);
  }

  @Override public Product getProduct(String address, int productNumber)
  {
    return shopList.getProduct(address, productNumber);
  }

  @Override public ArrayList<Item> getItemsByProduct(String address,
      Product product)
  {
    return shopList.getItemsByProduct(address, product);
  }

  @Override public double getLowestPriceOfProduct(String address,
      Product product)
  {
    return shopList.getLowestPriceOfProduct(address, product);
  }

  @Override public int getQuantityOfCertainProduct(String address,
      Product product)
  {
    return shopList.getQuantityOfCertainProduct(address, product);
  }

  @Override public Item getSpecificItem(String address, Date expirationDate,
      int productId)
  {
    return shopList.getSpecificItem(address, expirationDate, productId);
  }

  @Override public ArrayList<Shop> getAllShops()
  {
    return shopList.getShops();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  @Override public void addUser(String username, String password)
  {
    userList.addUser(username, password);
  }

  @Override public User getUser(String username, String password)
  {
    return userList.getUser(username, password);
  }

  @Override public void addItem(String address, String productName,
      int productID, double price, Date expirationDate, int quantity,
      ArrayList<Category> categories)
  {
    if (expirationDate.isBefore(new Date()))
    {
      throw new IllegalArgumentException("Expiration date cannot be before today's date");
    }
    Product searchedProduct = getProduct(address,productID);

    if(searchedProduct == null)
    {
      Product product = new Product(productName, productID, categories);
      modelPersistence.save(product);
      Item item = new Item(product, price, expirationDate, quantity);
      modelPersistence.save(item);
      shopList.addItem(address,item,product);
    }
    else
    {
      if (!searchedProduct.getProductName().equals(productName) ||
          !searchedProduct.getCategories().containsAll(categories))
      {
        throw new IllegalStateException("There already exists product with such product id");
      }
      Item searchedItem = getSpecificItem(address,expirationDate, productID);
      if (searchedItem == null)
      {
        Item item = new Item(getProduct(address,productID), price, expirationDate, quantity);
        modelPersistence.save(item);
        shopList.addItem(address,item);
      }
      else
      {
        searchedItem.setCurrentPrice(price);
        searchedItem.setQuantity(quantity);
        modelPersistence.update(searchedItem);
      }
    }

    property.firePropertyChange("StockUpdate", null, 1);

  }


}
