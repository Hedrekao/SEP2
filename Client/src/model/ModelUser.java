package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelUser extends UnnamedPropertyChangeSubject
{
  ArrayList<Product> getAllProducts(String address);
  ArrayList<Product> getProductsByCategory(String address, ArrayList<String> categories);
  Order getOrder();
  Product getProduct(String address, int productNumber);
  ArrayList<Item> getItemsByProduct(String address, Product product);
  double getLowestPriceOfProduct(String address, Product product);
  int getQuantityOfCertainProduct(String address, Product product);
  Item getSpecificItem(String address, Date expirationDate, int productId);
  int getQuantityOfItemsInBag();
  ArrayList<Shop> getAllShops();

  void completeOrder(String address);
  void addItemToOrder(String address, Item item);
  void removeItemFromOrder(String address, Item item);

  void setDelivery(String pickUpTime);
  void setDelivery(String addressLinePrimary, String addressLineSecondary, String city, int postalCode);
  void setPayment(String cardName, long cardNumber, int expirationMonth, int expirationYear, int securityCode);
}
