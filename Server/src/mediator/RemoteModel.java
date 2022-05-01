package mediator;

import model.Date;
import model.Item;
import model.Order;
import model.Product;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<Item, String>
{
  Product getProduct(int productNumber) throws RemoteException;
  void removeItemFromOrder(Item item) throws RemoteException;
  ArrayList<Item> getItemsByProduct(Product product) throws RemoteException;
  ArrayList<Product> getAllProducts() throws RemoteException;
  ArrayList<Product> getProductsByCategory(ArrayList<String> categories) throws RemoteException;
  void addItemToOrder(Item item) throws RemoteException;
  void completeOrder(Order order) throws RemoteException;
  double getLowestPriceOfProduct(Product product) throws RemoteException;
  int getQuantityOfCertainProduct(Product product) throws RemoteException;
  Item getSpecificItem(Date expirationDate, int productId) throws RemoteException;
  Order getCurrentOrder() throws RemoteException;
  int getQuantityOfItemsInBag() throws RemoteException;
}
