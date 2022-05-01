package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  ArrayList<Product> getAllProducts();
  ArrayList<Product> getProductsByCategory(ArrayList<String> categories);
  void completeOrder(Order order);
  void addItemToOrder(Item item);
  void removeItemFromOrder(Item item);
  Product getProduct(int productNumber);
  ArrayList<Item> getItemsByProduct(Product product);
  double getLowestPriceOfProduct(Product product);
  int getQuantityOfCertainProduct(Product product);
  Item getSpecificItem(Date expirationDate, int productId);
  Order getCurrentOrder();
  int getQuantityOfItemsInBag();

}
