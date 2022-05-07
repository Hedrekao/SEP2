package model;
import java.util.ArrayList;

public interface ModelPersistence
{
//  void clear();
  void save(Order order);
  void save(Item item);
  void save(Product product);
  void update(Item item);
  UserList loadUsers();
  ProductList loadProducts();
  ItemList loadItems();
  ArrayList<Order> loadOrders();
}
