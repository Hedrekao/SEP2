package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelEmployee
{
  void addUser(String username, String password);
  User getUser(String username, String password);
  void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories);
  Item getSpecificItem(String address, Date expirationDate, int productId);

  Order getOrder(String shopAddress, int day, int month, int year, int hour, int minute, int second, String deliveryOptions);
  ArrayList<Order> getOrderList(String shopAddress);
  void removeOrder(String shopAddress, int day, int month, int year, int hour, int minute, int second, String deliveryOptions);
  void removeItem(String address, Date expirationDate, int productID);
  ArrayList<Item> getAllItemsFromShop(String address);

  void updateItem(String shopAddress, String previousDate, int previousNumber,
      Date date, ArrayList<Category> categories, long newNumber, String newName, double newPrice,
      int newQuantity);
}
