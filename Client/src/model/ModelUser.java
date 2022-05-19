package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelUser extends UnnamedPropertyChangeSubject
{

  ArrayList<Product> getProductsByCategory(String address, ArrayList<String> categories);

  Product getProduct(String address, int productNumber);
  ArrayList<Item> getItemsByProduct(String address, Product product);
  double getLowestPriceOfProduct(String address, Product product);
  int getQuantityOfCertainProduct(String address, Product product);
  Item getSpecificItem(String address, Date expirationDate, int productId);
  int getQuantityOfItemsInBag();
  ArrayList<Shop> getAllShops();
  ArrayList<Product> getAllProducts(String address);

  void completeOrder(String address, Order order);
  void addItemToOrder(String address, Item item);
  void removeItemFromOrder(String address, Item item,int quantityOfItem);


}
