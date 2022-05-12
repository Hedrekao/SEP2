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
  private Model model;

  public ModelDatabase(Model model) throws ClassNotFoundException
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
      String sql =
          "INSERT INTO food_waste.order (total_price, is_delivery, date)"
              + " VALUES (? , ? , ?)";
      Object[] updateResult = db.update(sql, order.getTotalPrice(), false,
          java.sql.Date.valueOf(order.getDate().getDatabaseFormat()));

      ResultSet keys = (ResultSet) updateResult[1];

      int orderNumber = -1;
      if (keys.next())
      {
        orderNumber = keys.getInt(1);
      }

      for (Map.Entry<Item, Integer> entry : order.getItems().entrySet())
      {
        sql = "SELECT item.item_number FROM food_waste.item WHERE item.product_number = ? AND item.expiration_date = ?";

        Item item = entry.getKey();
        int quantity = entry.getValue();
        ArrayList<Object[]> results = db.query(sql,
            item.getProduct().getProductID(), java.sql.Date.valueOf(
                item.getExpirationDate().getDatabaseFormat()));
        int itemId = (int) results.get(0)[0];
        sql =
            "INSERT INTO food_waste.order_item (order_number, item_number, quantity_of_item)"
                + " VALUES (? , ? , ?)";
        db.update(sql, orderNumber, itemId, quantity);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void save(Item item)
  {
    try
    {
      String sql =
          "INSERT INTO food_waste.item (quantity_in_stock, price, expiration_date, product_number)"
              + " VALUES (? , ? , ? , ?)";
      Object[] updateResult = db.update(sql, item.getQuantity(),
          item.getCurrentPrice(),
          java.sql.Date.valueOf(item.getExpirationDate().getDatabaseFormat()),
          item.getProduct().getProductID());

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void save(Product product)
  {
    try
    {
      String sql = "SELECT product.product_number, product.name, product.category FROM food_waste.product WHERE product.product_number = ?";

      ArrayList<Object[]> results = db.query(sql, product.getProductID());

      if (results.size() == 0)
      {
        sql =
            "INSERT INTO food_waste.product (product_number, category, name)"
                + " VALUES (? , ? , ? )";
        Object[] updateResult = db.update(sql, product.getProductID(),
            product.getCategories().get(0).getName(), product.getProductName());
      }

      //change shit with categories
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void update(Item item)
  {
    String sql = "UPDATE food_waste.item SET quantity_in_stock = ? , price = ? WHERE product_number = ? AND expiration_date = ?";

    try
    {
      db.update(sql, item.getQuantity(), item.getCurrentPrice(),
          item.getProduct().getProductID(),
          java.sql.Date.valueOf(item.getExpirationDate().getDatabaseFormat()));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public UserList loadUsers()
  {

    UserList list = new UserList();
    String sql = "SELECT employee.username, employee.hash_password, employee.shop_address FROM food_waste.employee";

    try
    {
      ArrayList<Object[]> results = db.query(sql);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        User user = new User((String) result[0], (int) result[1]);
        user.setShopAddress((String) result[2]);
        list.addUser(user);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return list;
  }

  @Override public ShopList loadShops()
  {
    ShopList shopList = new ShopList();

    String sql = "SELECT shop.name, shop.address FROM food_waste.shop";

    try
    {
      ArrayList<Object[]> results = db.query(sql);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        Object[] itemAndProductList = loadItemsAndProductsFromShop((String) result[1]);
        ProductList productList = (ProductList) itemAndProductList[1];
        ItemList itemList = (ItemList) itemAndProductList[0];
        Shop shop = new Shop((String) result[0], (String) result[1], itemList,
            productList);
        shopList.addShop(shop);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return shopList;
  }

  private Product loadProduct(int productNumber)
  {
    Product product = null;
    String sql = "SELECT product.product_number, product.name, product.category FROM food_waste.product WHERE product.product_number = ?";

    try
    {
      ArrayList<Object[]> results = db.query(sql, productNumber);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category((String) result[2]));
        product = new Product((String) result[1], (int) result[0], categories);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return product;
  }

  private Object[] loadItemsAndProductsFromShop(String address)
  {
    ItemList itemList = new ItemList();
    ProductList productList = new ProductList();

    String sql = "SELECT item.product_number,  item.price, item.quantity_in_stock, item.expiration_date FROM food_waste.item WHERE item.shop_address = ?";

    try
    {
      ArrayList<Object[]> results = db.query(sql, address);

      for (int i = 0; i < results.size(); i++)
      {
        Object[] result = results.get(i);
        Product product = loadProduct((int) result[0]);
        productList.addProduct(product);
        itemList.addItem(
            new Item(product, ((BigDecimal) result[1]).doubleValue(),
                new Date((result[3]).toString()), (int) result[2]));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return new Object[] {itemList, productList};
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
