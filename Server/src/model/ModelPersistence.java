package model;
import java.util.ArrayList;

public interface ModelPersistence
{
//  void clear();
  void save(String address, Order order);
  void save(String address, Item item);
  void save(Product product);
  void update(String address, Item item);
  void updateCompletedOrder(Order order);
  UserList loadUsers();
  ShopList loadShops();
  ArrayList<Order> loadOrdersFromShop(String address);

}
