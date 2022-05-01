package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductList implements Serializable
{
  private ArrayList<Product> products;

  public ProductList()
  {
    this.products = new ArrayList<>();
  }

  public void addProduct(Product product)
  {
    products.add(product);
  }

  public Product getProduct(int productNumber)
  {
    for (int i = 0; i < products.size(); i++)
    {
      if (products.get(i).getProductID() == productNumber)
      {
        return products.get(i);
      }
    }
    return null;
  }

  public ArrayList<Product> getAllProducts()
  {
    return products;
  }

  public ArrayList<Product> getProductsByCategory(String category)
  {
    ArrayList<Product> temp = products;
    for(int i = 0; i < temp.size(); i++)
    {
      if(!temp.get(i).hasCategory(category))
      {
        temp.remove(i);
      }
    }
    return temp;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof ProductList))
    {
      return false;
    }
    ProductList other = (ProductList) obj;
    return this.products.equals(other.products);
  }
}
