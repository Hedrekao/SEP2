package model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private ProductList productList;
  private ArrayList<Order> orderList;
  private PropertyChangeSupport property;
  private ItemList itemList;
  private ModelPersistence modelPersistence;
  private UserList userList;

  public ModelManager() throws ClassNotFoundException
  {
    this.modelPersistence = new ModelDatabase(this);

    this.productList = modelPersistence.loadProducts();
    this.itemList = modelPersistence.loadItems();

    userList = modelPersistence.loadUsers();
    orderList = new ArrayList<>();
    orderList.add(new Order());
    property = new PropertyChangeSupport(this);
  }

  @Override public ArrayList<Product> getAllProducts()
  {
    return productList.getAllProducts();
  }

  @Override public ArrayList<Product> getProductsByCategory(
      ArrayList<String> categories)
  {
    return null;
  }

  @Override public void completeOrder(Order order)
  {

    orderList.get(orderList.size() - 1).completeOrder();
    modelPersistence.save(order);
    orderList.add(new Order());
  }

  @Override public void addItemToOrder(Item item)
  {
    Item item1 = getSpecificItem(item.getExpirationDate(), item.getProduct().getProductID());
    item1.setQuantity(item1.getQuantity() - 1);
    orderList.get(orderList.size() - 1).addItem(item1);
  }

  @Override public void removeItemFromOrder(Item item)
  {
    orderList.get(orderList.size() - 1).removeItem(item);
  }

  @Override public Product getProduct(int productNumber)
  {
    return productList.getProduct(productNumber);
  }

  @Override public ArrayList<Item> getItemsByProduct(Product product)
  {
    return itemList.getItems(product);
  }

  @Override public double getLowestPriceOfProduct(Product product)
  {
    ArrayList<Item> items = itemList.getItems(product);
    double lowestSum;
    if (items.size() != 0)
    {
      lowestSum = items.get(0).getCurrentPrice();

      for (int i = 0; i < items.size(); i++)
      {
        if (items.get(i).getCurrentPrice() < lowestSum)
        {
          lowestSum = items.get(i).getCurrentPrice();
        }
      }
    }
    else
    {
      lowestSum = 0;
    }


    return lowestSum;
  }

  @Override public int getQuantityOfCertainProduct(Product product)
  {
    int sum = 0;
    ArrayList<Item> items = itemList.getItems(product);
    for (int i = 0; i < items.size(); i++)
    {
      sum += items.get(i).getQuantity();
    }
    return sum;
  }

  @Override public Item getSpecificItem(Date expirationDate, int productId)
  {
    ArrayList<Item> items = itemList.getItems(getProduct(productId));
    for (Item item : items)
    {
      if (item.getExpirationDate().equals(expirationDate))
      {
        return item;
      }
    }
    return null;
  }

  @Override public Order getCurrentOrder()
  {
    return orderList.get(orderList.size() - 1);
  }

  @Override public int getQuantityOfItemsInBag()
  {
    Order order = getCurrentOrder();
    int sum = 0;
    for (int quantity : order.getItems().values())
    {
      sum += quantity;
    }
    return sum;
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

  @Override public void addItem(String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories)
  {
    Product product = new Product(productName, productID, categories);
    modelPersistence.save(product);
    productList.addProduct(product);

    // add observers imo w chuj

    // kurwa tez not sure
    Item item = new Item(product, price, expirationDate, quantity);
    modelPersistence.save(product);
    itemList.addItem(item);
    //jebie chujem
  }

}
