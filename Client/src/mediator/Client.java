package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client implements RemoteListener<Item, String>, Model
{
    private RemoteModel server;
    private PropertyChangeSupport property;

    public Client() throws IOException
    {
      try
      {
        server = (RemoteModel) Naming.lookup("rmi://localhost:1099/foodWaste");
      }
      catch (Exception e)
      {
        System.out.println("xd");
        e.printStackTrace();
      }

      UnicastRemoteObject.exportObject(this, 0);
      server.addListener(this);
      property = new PropertyChangeSupport(this);
    }



    public void removeItemFromOrder(Item item)
    {
      try
      {
        server.removeItemFromOrder(item);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

  @Override public Product getProduct(String address, int productNumber)
  {
    try
    {
      return server.getProduct(address, productNumber);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public ArrayList<Item> getItemsByProduct(String address,
      Product product)
  {
    try
    {
      return server.getItemsByProduct(address, product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public double getLowestPriceOfProduct(String address,
      Product product)
  {
    try
    {
      return server.getLowestPriceOfProduct(address, product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return 0;
    }
  }

  @Override public int getQuantityOfCertainProduct(String address,
      Product product)
  {
    try
    {
      return server.getQuantityOfCertainProduct(address, product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return 0;
    }
  }

  @Override public Item getSpecificItem(String address, Date expirationDate,
      int productId)
  {
    try
    {
      return server.getSpecificItem(address, expirationDate, productId);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }


  @Override public Order getCurrentOrder()
  {
    try
    {
      return server.getCurrentOrder();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public int getQuantityOfItemsInBag()
  {
    try
    {
      return server.getQuantityOfItemsInBag();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return 0;
    }
  }

  @Override public ArrayList<Shop> getAllShops()
  {
    try
    {
      return server.getAllShops();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public void addItemToOrder(String address, Item item)
    {
      try
      {
        server.addItemToOrder(address,item);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

  @Override public ArrayList<Product> getAllProducts(String address)
  {
    try
    {
      return server.getAllProducts(address);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    try
    {
      return server.getProductsByCategory(address, categories);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public void completeOrder(Order order)
    {
      try
      {
        server.completeOrder(order);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

    @Override public void propertyChange(ObserverEvent<Item, String> event)
        throws RemoteException
    {
      property.firePropertyChange(event.getPropertyName(),event.getValue1(),event.getValue2());
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
    try
    {
      server.addUser(username,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public User getUser(String username, String password)
  {
    try
    {
      return server.getUser(username,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories)
  {
    try
    {
      server.addItem(address, productName, productID, price, expirationDate, quantity, categories);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

}



