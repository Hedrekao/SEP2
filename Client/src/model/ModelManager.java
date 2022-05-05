package model;
import mediator.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements ModelUser, ModelEmployee
{

  private PropertyChangeSupport property;
  private Client client;

  public ModelManager() throws ClassNotFoundException, IOException
  {
    client = new Client();
    property = new PropertyChangeSupport(this);
  }

  @Override public ArrayList<Product> getAllProducts()
  {
    return client.getAllProducts();
  }

  @Override public ArrayList<Product> getProductsByCategory(
      ArrayList<String> categories)
  {
    return client.getProductsByCategory(categories);
  }

  @Override public void completeOrder(Order order)
  {
    client.completeOrder(order);
  }

  @Override public void addItemToOrder(Item item)
  {
    client.addItemToOrder(item);
  }

  @Override public void removeItemFromOrder(Item item)
  {
    client.removeItemFromOrder(item);
  }

  @Override public Product getProduct(int productNumber)
  {
    return client.getProduct(productNumber);
  }

  @Override public ArrayList<Item> getItemsByProduct(Product product)
  {
    return client.getItemsByProduct(product);
  }

  @Override public double getLowestPriceOfProduct(Product product)
  {
    return client.getLowestPriceOfProduct(product);
  }

  @Override public int getQuantityOfCertainProduct(Product product)
  {
    return client.getQuantityOfCertainProduct(product);
  }

  @Override public Item getSpecificItem(Date expirationDate, int productId)
  {
    return client.getSpecificItem(expirationDate, productId);
  }

  @Override public Order getCurrentOrder()
  {
    return client.getCurrentOrder();
  }

  @Override public int getQuantityOfItemsInBag()
  {
    return client.getQuantityOfItemsInBag();
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
    client.addUser(username, password);
  }

  @Override public User getUser(String username, String password)
  {
    return client.getUser(username,password);
  }
}
