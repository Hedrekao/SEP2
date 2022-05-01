package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable
{
  private String productName;
  private int productID;
  private ArrayList<Category> categories;

  public Product(String productName, int productID, ArrayList<Category> categories)
  {
    this.productID = productID;
    this.productName = productName;
    this.categories = categories;
  }

  @Override public String toString()
  {
    return productName;
  }

  public int getProductID()
  {
    return productID;
  }

  public String getProductName()
  {
    return productName;
  }

  public ArrayList<Category> getCategories()
  {
    return categories;
  }


  public boolean hasCategory(String category)
  {
    boolean cond = false;
    for(Category c : categories)
    {
      if(c.getName().equals(category))
      {
        cond = true;
      }
    }
    return cond;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Product))
    {
      return false;
    }
    Product other = (Product) obj;
    return this.productName.equals(other.productName) && this.productID == other.productID;
  }
}
