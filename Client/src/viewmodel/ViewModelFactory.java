package viewmodel;

import model.Model;
import view.ViewHandler;

public class ViewModelFactory
{

    private ProductsViewModel productsViewModel;
    private ItemsViewModel itemsViewModel;
    private BagViewModel bagViewModel;
    private ItemViewState itemViewState;

    public ViewModelFactory(Model model)
    {
        itemViewState = new ItemViewState();
        productsViewModel = new ProductsViewModel(model, itemViewState);
        itemsViewModel = new ItemsViewModel(model, itemViewState);
        bagViewModel = new BagViewModel(model);
    }


    public ProductsViewModel getProductsViewModel()
    {
        return productsViewModel;
    }

    public ItemsViewModel getItemsViewModel()
    {
        return itemsViewModel;
    }

    public BagViewModel getBagViewModel()
    {
        return bagViewModel;
    }
}
