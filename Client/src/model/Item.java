package model;

import java.io.Serializable;

public class Item implements Serializable
{
  private double defaultPrice;
  private double currentPrice;
  private Date expirationDate;
  private Product product;
  private int quantity;

  public Item(Product product, double price, Date expirationDate, int quantity)
  {
    if(product == null)
    {
      throw new IllegalArgumentException();
    }
    this.product = product;
    this.defaultPrice = price;
    this.currentPrice = price;
    this.expirationDate = expirationDate;
    this.quantity = quantity;
    updatePrice();
  }

  public double getDefaultPrice()
  {
    return defaultPrice;
  }

  public void setDefaultPrice(double defaultPrice)
  {
    this.defaultPrice = defaultPrice;
    updatePrice();
  }

  public void setCurrentPrice(double currentPrice)
  {
    this.currentPrice = currentPrice;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public void setExpirationDate(Date expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public Product getProduct()
  {
    return product;
  }

  public void updatePrice()
  {
    double temp = 0.5;
    if(expirationDate.daysBetween(new Date()) <= 10)
    {
      temp = (double) expirationDate.daysBetween(new Date()) /20;
    }

    this.currentPrice = Math.round((defaultPrice * (0.5 + temp)) * 100.0) / 100.0;
  }

  public double getCurrentPrice()
  {
    return currentPrice;
  }

  public Date getExpirationDate()
  {
    return expirationDate;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Item))
    {
      return false;
    }
    Item other = (Item) obj;
    return this.expirationDate.equals(other.expirationDate) && product.equals(other.product);
  }

  @Override public int hashCode()
  {
    return product.getProductID() + expirationDate.toString().hashCode();
  }
}
