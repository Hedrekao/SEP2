package model;

import java.util.ArrayList;

public interface ModelEmployee
{
  void addUser(String username, String password);
  User getUser(String username, String password);
  void addItem(String address, String productName, int productID, double price, Date expirationDate, int quantity, ArrayList<Category> categories);


  Order getOrder(String shopAddress, int day, int month, int year, int hour, int minute, int second, String deliveryOptions);
  ArrayList<Order> getOrderList(String shopAddress);
  void removeOrder(String shopAddress, int day, int month, int year, int hour, int minute, int second, String deliveryOptions);

}
