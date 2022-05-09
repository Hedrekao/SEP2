package model;
import mediator.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements Model, PropertyChangeListener
{

  private PropertyChangeSupport property;
  private Client client;

  public ModelManager() throws ClassNotFoundException, IOException
  {
    client = new Client();
    property = new PropertyChangeSupport(this);
    client.addListener(this);
  }

  @Override public ArrayList<Product> getAllProducts(String address)
  {
    return client.getAllProducts(address);
  }

  @Override public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    return client.getProductsByCategory(address, categories);
  }

  @Override public void completeOrder(Order order)
  {
    client.completeOrder(order);
  }

  @Override public void addItemToOrder(String address,Item item)
  {
    client.addItemToOrder(address,item);
  }

  @Override public void removeItemFromOrder(Item item)
  {
    client.removeItemFromOrder(item);
  }

  @Override public Product getProduct(String address, int productNumber)
  {
    return client.getProduct(address, productNumber);
  }

  @Override public ArrayList<Item> getItemsByProduct(String address, Product product)
  {
    return client.getItemsByProduct(address, product);
  }

  @Override public double getLowestPriceOfProduct(String address, Product product)
  {
    return client.getLowestPriceOfProduct(address, product);
  }

  @Override public int getQuantityOfCertainProduct(String address, Product product)
  {
    return client.getQuantityOfCertainProduct(address, product);
  }

  @Override public Item getSpecificItem(String address, Date expirationDate, int productId)
  {
    return client.getSpecificItem(address, expirationDate, productId);
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

  @Override public void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories)
  {
    client.addItem(address, productName, productID, price, expirationDate, quantity,
        categories);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
  }
}
