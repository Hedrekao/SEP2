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

    @Override public Product getProduct(int productNumber)
    {
      try
      {
        return server.getProduct(productNumber);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
        return null;
      }
    }

  @Override public ArrayList<Item> getItemsByProduct(Product product)
  {
    try
    {
      return server.getItemsByProduct(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public double getLowestPriceOfProduct(Product product)
  {
    try
    {
      return server.getLowestPriceOfProduct(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return -1;
    }
  }

  @Override public int getQuantityOfCertainProduct(Product product)
  {
    try
    {
      return server.getQuantityOfCertainProduct(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return -1;
    }
  }

  @Override public Item getSpecificItem(Date expirationDate, int productId)
  {
    try
    {
      return server.getSpecificItem(expirationDate,productId);
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

  public ArrayList<Product> getAllProducts()
    {
      try
      {
        return server.getAllProducts();
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
        return null;
      }
    }

    public ArrayList<Product> getProductsByCategory(ArrayList<String> categories)
    {
      try
      {
        return server.getProductsByCategory(categories);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
        return null;
      }
    }

    public void addItemToOrder(Item item)
    {
      try
      {
        server.addItemToOrder(item);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
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
}



