package model;
import java.io.Serializable;
import java.util.ArrayList;

public class ItemList implements Serializable
{
  private ArrayList<Item> list;

  public ItemList()
  {
    this.list = new ArrayList<>();
  }

  public void addItem(Item item)
  {
    list.add(item);
  }

  public void removeItem(Item item)
  {
    list.remove(item);
  }

  public ArrayList<Item> getItems(Product product)
  {
    ArrayList<Item> temp = new ArrayList<>();
    for(Item i : list)
    {
      if(i.getProduct().equals(product))
      {
        temp.add(i);
      }
    }
    return temp;
  }
}
