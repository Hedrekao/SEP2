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
  private ModelUser modelUser;
  private ModelEmployee modelEmployee;
  private PropertyChangeHandler<Item, String> property;

  public Server(ModelUser modelUser, ModelEmployee modelEmployee) throws IOException
  {

    this.modelUser = modelUser;
    this.modelEmployee = modelEmployee;
    property = new PropertyChangeHandler<>(this,true);
    this.modelUser.addListener(this);
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
    return modelUser.getProduct(productNumber);
  }


  @Override public void removeItemFromOrder(Item item) throws RemoteException
  {
    modelUser.removeItemFromOrder(item);
  }

  @Override public ArrayList<Item> getItemsByProduct(Product product)
      throws RemoteException
  {
    return modelUser.getItemsByProduct(product);
  }

  @Override public ArrayList<Product> getAllProducts() throws RemoteException
  {
    return modelUser.getAllProducts();
  }

  @Override public ArrayList<Product> getProductsByCategory(
      ArrayList<String> categories) throws RemoteException
  {
    return modelUser.getProductsByCategory(categories);
  }

  @Override public void addItemToOrder(Item item) throws RemoteException
  {
    modelUser.addItemToOrder(item);
  }

  @Override public void completeOrder(Order order) throws RemoteException
  {
    modelUser.completeOrder(order);
  }

  @Override public double getLowestPriceOfProduct(Product product)
      throws RemoteException
  {
    return modelUser.getLowestPriceOfProduct(product);
  }

  @Override public int getQuantityOfCertainProduct(Product product)
      throws RemoteException
  {
    return modelUser.getQuantityOfCertainProduct(product);
  }

  @Override public Item getSpecificItem(Date expirationDate, int productId)
      throws RemoteException
  {
    return modelUser.getSpecificItem(expirationDate,productId);
  }

  @Override public Order getCurrentOrder() throws RemoteException
  {
    return modelUser.getCurrentOrder();
  }

  @Override public void addUser(String username, String password)
      throws RemoteException
  {
    modelEmployee.addUser(username, password);
  }

  @Override public User getUser(String username, String password)
      throws RemoteException
  {
    return modelEmployee.getUser(username,password);
  }

  @Override public int getQuantityOfItemsInBag() throws RemoteException
  {
    return modelUser.getQuantityOfItemsInBag();
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
