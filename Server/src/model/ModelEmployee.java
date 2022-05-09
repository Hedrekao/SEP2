package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelEmployee
{
  void addUser(String username, String password);
  User getUser(String username, String password);
  void addItem(String address, String productName, int productID, double price,
      Date expirationDate, int quantity, ArrayList<Category> categories);}
