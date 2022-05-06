package mediator;

import model.*;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements RemoteModel, PropertyChangeListener
{
  private Model model;
  private PropertyChangeHandler<Item, String> property;

  public Server(Model model) throws IOException
  {

    this.model = model;
    property = new PropertyChangeHandler<>(this,true);
    this.model.addListener(this);
    startRegistry();
    startServer();
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this,0);
    Naming.rebind("foodWaste", this);
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("dfafdaf");
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
//    property.firePropertyChange();
  }

  @Override public Product getProduct(int productNumber) throws RemoteException
  {
    return model.getProduct(productNumber);
  }


  @Override public void removeItemFromOrder(Item item) throws RemoteException
  {
    model.removeItemFromOrder(item);
  }

  @Override public ArrayList<Item> getItemsByProduct(Product product)
      throws RemoteException
  {
    return model.getItemsByProduct(product);
  }

  @Override public ArrayList<Product> getAllProducts() throws RemoteException
  {
    return model.getAllProducts();
  }

  @Override public ArrayList<Product> getProductsByCategory(
      ArrayList<String> categories) throws RemoteException
  {
    return model.getProductsByCategory(categories);
  }

  @Override public void addItemToOrder(Item item) throws RemoteException
  {
    model.addItemToOrder(item);
  }

  @Override public void completeOrder(Order order) throws RemoteException
  {
    model.completeOrder(order);
  }

  @Override public double getLowestPriceOfProduct(Product product)
      throws RemoteException
  {
    return model.getLowestPriceOfProduct(product);
  }

  @Override public int getQuantityOfCertainProduct(Product product)
      throws RemoteException
  {
    return model.getQuantityOfCertainProduct(product);
  }

  @Override public Item getSpecificItem(Date expirationDate, int productId)
      throws RemoteException
  {
    return model.getSpecificItem(expirationDate,productId);
  }

  @Override public Order getCurrentOrder() throws RemoteException
  {
    return model.getCurrentOrder();
  }

  @Override public void addUser(String username, String password)
      throws RemoteException
  {
    model.addUser(username,password);
  }

  @Override public User getUser(String username, String password)
      throws RemoteException
  {
    return model.getUser(username,password);
  }

  @Override public void addItem(String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories)
  {
    model.addItem(productName, productID, price, expirationDate, quantity, categories);
  }


  @Override public int getQuantityOfItemsInBag() throws RemoteException
  {
    return model.getQuantityOfItemsInBag();
  }

  @Override public boolean addListener(GeneralListener<Item, String> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Item, String> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener,propertyNames);
  }
}
