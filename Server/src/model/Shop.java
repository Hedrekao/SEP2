package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable
{
  private String name;
  private String address;
  private ProductList productList;
  private ItemList itemList;


  public Shop(String name, String address, ItemList itemList, ProductList productList)
  {
   this.address = address;
   this.name = name;
   this.productList = productList;
   this.itemList = itemList;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public ProductList getProductList()
  {
    return productList;
  }

  public ItemList getItemList()
  {
    return itemList;
  }

  public ArrayList<Product> getAllProducts()
  {
    return productList.getAllProducts();
  }

  public ArrayList<Product> getProductsByCategory(
      ArrayList<String> categories)
  {
    return null;
  }

  public Product getProduct(int productNumber)
  {
    return productList.getProduct(productNumber);
  }

  public ArrayList<Item> getItemsByProduct(Product product)
  {
    return itemList.getItems(product);
  }

  public double getLowestPriceOfProduct(Product product)
  {
    ArrayList<Item> items = itemList.getItems(product);
    double lowestSum;
    if (items.size() != 0)
    {
      lowestSum = items.get(0).getCurrentPrice();

      for (int i = 0; i < items.size(); i++)
      {
        if (items.get(i).getCurrentPrice() < lowestSum)
        {
          lowestSum = items.get(i).getCurrentPrice();
        }
      }
    }
    else
    {
      lowestSum = 0;
    }
    return lowestSum;
  }

  public int getQuantityOfCertainProduct(Product product)
  {

    int sum = 0;
    ArrayList<Item> items = itemList.getItems(product);
    for (int i = 0; i < items.size(); i++)
    {
      sum += items.get(i).getQuantity();
    }
    return sum;
  }

  public Item getSpecificItem(Date expirationDate, int productId)
  {
    ArrayList<Item> items = itemList.getItems(getProduct(productId));
    for (Item item : items)
    {
      if (item.getExpirationDate().equals(expirationDate))
      {
        return item;
      }
    }
    return null;
  }

  public void addItem(Item item, Product product)
  {
    itemList.addItem(item);
    productList.addProduct(product);
  }

  public void addItem(Item item)
  {
    itemList.addItem(item);
  }

  public int getQuantityOfAllItemsInShop()
  {
    return itemList.getQuantityOfAllItems();
  }
}
