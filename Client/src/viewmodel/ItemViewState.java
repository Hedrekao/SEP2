package viewmodel;

import model.Item;
import model.Product;

public class ItemViewState
{
  private Item item;

  public ItemViewState()
  {
    item = null;
  }

  public void setProduct(Item item)
  {
    this.item = item;
  }

  public Item getItem()
  {
    return item;
  }
}
