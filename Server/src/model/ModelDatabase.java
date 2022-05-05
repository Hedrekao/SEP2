package model;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ModelDatabase implements ModelPersistence
{

  private MyDatabasev2 db;
  private static final String DRIVER = "org.postgresql.Driver";
  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USER = "postgres";
  private static final String PASSWORD = "tom2002";
  private ModelUser model;

  public ModelDatabase(ModelUser model) throws ClassNotFoundException
  {
    this.db = new MyDatabasev2(DRIVER, URL, USER, PASSWORD);
    this.model = model;
  }



//  @Override public void clear()
//  {
//    try
//    {
//      String sql = "TRUNCATE TABLE food_waste.order CASCADE;";
//      db.update(sql);
//      sql = "TRUNCATE TABLE food_waste.item CASCADE;";
//      db.update(sql);
//
//    }
//    catch (SQLException e)
//    {
//      e.printStackTrace();
//    }
//  }

  @Override public void save(Order order)
  {
    try
    {
      String sql = "INSERT INTO food_waste.order (total_price, is_delivery, date)"
          + " VALUES (? , ? , ?)";
      Object[] updateResult = db.update(sql, order.getTotalPrice(), false, java.sql.Date.valueOf(order.getDate().getDatabaseFormat()) );

      ResultSet keys = (ResultSet) updateResult[1];

      int orderNumber = -1;
      if (keys.next())
      {
        orderNumber = keys.getInt(1);
      }


      for (Map.Entry<Item,Integer> entry : order.getItems().entrySet())
      {
        sql = "SELECT item.item_number FROM food_waste.item WHERE item.product_number = ? AND item.expiration_date = ?";

        Item item = entry.getKey();
        int quantity = entry.getValue();
        ArrayList<Object[]> results = db.query(sql, item.getProduct().getProductID(), java.sql.Date.valueOf(item.getExpirationDate().getDatabaseFormat()));
        int itemId = (int) results.get(0)[0];
        sql = "INSERT INTO food_waste.order_item (order_number, item_number, quantity_of_item)" + " VALUES (? , ? , ?)";
        db.update(sql, orderNumber, itemId, quantity);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public UserList loadUsers()
  {

    UserList list = new UserList();
    String sql = "SELECT users.username, users.hashPassword, users.isEmployee FROM food_waste.users";

    try
    {
      ArrayList<Object[]> results = db.query(sql);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        list.addUser(new Product((String) result[1], (int) result[0], categories));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return list;
  }


  @Override public ProductList loadProducts()
  {
    ProductList list = new ProductList();
    String sql = "SELECT product.product_number, product.name, product.category FROM food_waste.product";

    try
    {
      ArrayList<Object[]> results = db.query(sql);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category((String) result[2]));
        list.addProduct(new Product((String) result[1], (int) result[0], categories));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return list;
  }

  @Override public ItemList loadItems()
  {
    ItemList list = new ItemList();
    String sql = "SELECT item.product_number,  item.price, item.quantity_in_stock, item.expiration_date FROM food_waste.item";

    try
    {
      ArrayList<Object[]> results = db.query(sql);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        Product product = model.getProduct((int) result[0]);
        list.addItem(new Item(product, ((BigDecimal) result[1]).doubleValue(), new Date((result[3]).toString()), (int) result[2]));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return list;
  }

  @Override public ArrayList<Order> loadOrders()
  {
//    ArrayList<Order> list = new ArrayList<>();
//    String sql = "SELECT order.id, order.totalPrice, order.isDelivery FROM Food_Waste.order";
//
//    try
//    {
//      ArrayList<Object[]> results = db.query(sql);
//
//
//      for (int i = 0; i < results.size(); i++)
//      {
//        Object result = results.get(i);
//        list.add(new Order((int) result[0], (double) result[1], (boolean) result[2]));
//      }
//    }
//    catch (SQLException e)
//    {
//      e.printStackTrace();
//    }
//
//    return list;
    return null;
  }

}
