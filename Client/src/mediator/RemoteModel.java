package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<Item, String>
{
  Product getProduct(String address, int productNumber) throws RemoteException;
  ArrayList<Item> getItemsByProduct(String address, Product product) throws RemoteException;
  void removeItemFromOrder(String address, Item item,int quantityOfItem) throws RemoteException;
  ArrayList<Product> getAllProducts(String address) throws RemoteException;
  ArrayList<Product> getProductsByCategory(String address, ArrayList<String> categories) throws RemoteException;
  void addItemToOrder(String address, Item item) throws RemoteException;
  void completeOrder(String address, Order order) throws RemoteException;
  double getLowestPriceOfProduct(String address, Product product) throws RemoteException;
  int getQuantityOfCertainProduct(String address, Product product) throws RemoteException;
  Item getSpecificItem(String address, Date expirationDate, int productId) throws RemoteException;
  void addUser(String username, String password) throws RemoteException;
  User getUser(String username, String password) throws RemoteException;
  void addItem(String address, String productName, int productID, double price, Date expirationDate, int quantity, ArrayList<Category> categories) throws RemoteException;
  ArrayList<Shop> getAllShops() throws RemoteException;

}
