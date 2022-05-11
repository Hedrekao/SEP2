package model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable
{
  private boolean completed;
  private HashMap<Item, Integer> items;
  private LocalTime localTime;
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

  public Order()
  {
    items = new HashMap<>();
    completed = false;
    date = new Date();
    localTime = LocalTime.now();
  }

  public HashMap<Item, Integer> getItems()
  {
    return items;
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

  public void setDelivery(String addressLinePrimary, String addressLineSecondary, String city, int postalCode)
  {
    if(addressLinePrimary == null || city == null || postalCode < 1)
    {
      throw new IllegalArgumentException("Check input fields.");
    }
    else
    {
      this.addressLinePrimary = addressLinePrimary;
      this.addressLineSecondary = addressLineSecondary;
      this.city = city;
      this.postalCode = postalCode;
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

  public int getHour()
  {
    return localTime.getHour();
  }

  public int getMinute()
  {
    return localTime.getMinute();
  }

  public int getSecond()
  {
    return localTime.getSecond();
  }

  public String getAddressLinePrimary()
  {
    return addressLinePrimary;
  }

  public void removeItem(Item item)
  {
    items.remove(item);
  }

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
