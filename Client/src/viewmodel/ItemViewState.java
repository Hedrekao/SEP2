package viewmodel;

import model.Product;

public class ItemViewState
{
  private Product product;

  public ItemViewState()
  {
    product = null;
  }

  public void setProduct(Product product)
  {
    this.product = product;
  }

  public Product getProduct()
  {
    return product;
  }
}
