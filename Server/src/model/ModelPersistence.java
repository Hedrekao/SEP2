package model;
import java.util.ArrayList;

public interface ModelPersistence
{
//  void clear();
  void save(Order order);
  UserList loadUsers();
  ProductList loadProducts();
  ItemList loadItems();
  ArrayList<Order> loadOrders();
}
