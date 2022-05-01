package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable
{
  private boolean completed;
  private HashMap<Item, Integer> items;
  private Date date;

  public Order()
  {
    items = new HashMap<>();
    completed = false;
    date = new Date();
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

  public void removeItem(Item item)
  {
    items.remove(item);
  }

  public void completeOrder()
  {
    completed = true;
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

  public boolean isCompleted()
  {
    return completed;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Order))
    {
      return false;
    }
    Order other = (Order) obj;
    return this.completed == other.completed && this.items.equals(other.items) && this.date.equals(other.date);
  }
}
