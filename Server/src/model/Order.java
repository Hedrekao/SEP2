package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable
{
  private HashMap<Item, Integer> items;
  private Date date;
  private String addressLinePrimary;
  private String addressLineSecondary;
  private String city;
  private int postalCode;

  private String pickUpTime;

  private String cardName;
  private long cardNumber;
  private int expirationMonth;
  private int expirationYear;
  private int securityCode;
  private String email;
  private String shopAddress;

  public Order()
  {
    items = new HashMap<>();
    date = new Date();
    addressLinePrimary = null;
    addressLineSecondary = null;
    city = null;
    postalCode = 0;
    pickUpTime = null;
    email = null;
    cardName = null;
    cardNumber = 0;
    expirationMonth = 0;
    expirationYear = 0;
    securityCode = 0;
    shopAddress = null;
  }

  public Order(Date date)
  {
    items = new HashMap<>();
    this.date = date;
    addressLinePrimary = null;
    addressLineSecondary = null;
    city = null;
    postalCode = 0;
    pickUpTime = null;
    email = null;
    cardName = null;
    cardNumber = 0;
    expirationMonth = 0;
    expirationYear = 0;
    securityCode = 0;
    shopAddress = null;
  }

  public HashMap<Item, Integer> getItems()
  {
    return items;
  }

  public void setShopAddress(String shopAddress)
  {
    this.shopAddress = shopAddress;
  }

  public String getShopAddress()
  {
    return shopAddress;
  }

  public void addItem(Item item)
  {
    if (items.containsKey(item))
    {

      items.put(item, items.get(item) + 1);
    }
    else {
      items.put(item, 1);
    }
  }

  public void addItem(Item item, int quantity)
  {

      items.put(item, quantity);
  }

  public void setDelivery(String addressLinePrimary, String addressLineSecondary, String city, int postalCode, String email)
  {
    if(addressLinePrimary == null || city == null || postalCode < 1 || !email.contains("@"))
    {
      throw new IllegalArgumentException("Check input fields.");
    }
    else
    {
      this.addressLinePrimary = addressLinePrimary;
      this.addressLineSecondary = addressLineSecondary;
      this.city = city;
      this.postalCode = postalCode;
      this.email = email;
    }
  }

  public void setDelivery(String pickUpTime)
  {
    if(pickUpTime == null)
    {
      throw new IllegalArgumentException("Please, select pick-up time.");
    }
    else
    {
      this.pickUpTime = pickUpTime;
    }
  }

  public void setPayment(String cardName, long cardNumber, int expirationMonth, int expirationYear, int securityCode)
  {
    if(cardNumber < 0 || cardName == null || expirationMonth > 12 || expirationMonth < 0 ||expirationYear > 2030 ||expirationYear < 2022 || securityCode > 999 || securityCode < 0)
    {
      throw new IllegalArgumentException();
    }
    else
    {
      this.cardName = cardName;
      this.cardNumber = cardNumber;
      this.expirationMonth = expirationMonth;
      this.expirationYear = expirationYear;
      this.securityCode = securityCode;
    }
  }

  public int getPostalCode()
  {
    return postalCode;
  }

  public String getAddressLinePrimary()
  {
    return addressLinePrimary;
  }

  public String getAddressLineSecondary()
  {
    return addressLineSecondary;
  }

  public String getCity()
  {
    return city;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPickUpTime()
  {
    return pickUpTime;
  }


  public void removeItem(Item item) {items.remove(item);}

  public Date getDate()
  {
    return date;
  }

  public double getTotalPrice()
  {
    double sum = 0;

    for (Map.Entry<Item, Integer> entry : items.entrySet()) {
      sum += entry.getValue() * entry.getKey().getCurrentPrice();
    }
    return sum;
  }

  public int getQuantityOfItemsInOrder()
  {
    int sum = 0;

    for (Map.Entry<Item, Integer> entry : items.entrySet()) {
      sum += entry.getValue();
    }
    return sum;
  }

  public String getOrderDescription()
  {
    if(addressLinePrimary == null)
    {
      return "Pick-up time: " + pickUpTime + "\n";
    }
    else
    {
      return "Address 1: " + addressLinePrimary + "\n" + "Address 2: " + addressLineSecondary + "\n" + "City: " + city
          + "\n" + "Postal code: " + postalCode + "\n" + "Email: " + email;
    }
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Order))
    {
      return false;
    }
    Order other = (Order) obj;
    return this.items.equals(other.items) && this.date.equals(other.date);
  }
}
