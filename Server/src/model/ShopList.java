package model;

import java.util.ArrayList;

public class ShopList
{
  private ArrayList<Shop> list;

  public ShopList()
  {
    this.list = new ArrayList<>();
  }

  public ArrayList<Shop> getShops()
  {
    return list;
  }

  public Shop getShop(String address)
  {
    for (Shop s : list)
    {
      if (s.getAddress().equals(address))
      {
        return s;
      }
    }
    return null;
  }

  public void addShop(Shop shop)
  {
    list.add(shop);
  }

  public ArrayList<Product> getAllProducts(String address)
  {
    return getShop(address).getAllProducts();
  }

  public Product getProduct(String address, int productNumber)
  {
    return getShop(address).getProduct(productNumber);
  }

  public ArrayList<Item> getItemsByProduct(String address, Product product)
  {
    return getShop(address).getItemsByProduct(product);
  }

  public double getLowestPriceOfProduct(String address, Product product)
  {
    return getShop(address).getLowestPriceOfProduct(product);
  }

  public int getQuantityOfCertainProduct(String address, Product product)
  {

    return getShop(address).getQuantityOfCertainProduct(product);
  }

  public Item getSpecificItem(String address, Date expirationDate,
      int productId)
  {

    return getShop(address).getSpecificItem(expirationDate, productId);
  }

  public ArrayList<Product> getProductsByCategory(String address,
      ArrayList<String> categories)
  {
    return getShop(address).getProductsByCategory(categories);
  }

  public void addItem(String address, Item item, Product product)
  {
    getShop(address).addItem(item, product);
  }

  public void addItem(String address, Item item)
  {
    getShop(address).addItem(item);
  }
}
