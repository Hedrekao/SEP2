package viewmodel;

import model.Model;
import model.ModelEmployee;
import model.ModelUser;

public class ViewModelFactory
{

    private ProductsViewModel productsViewModel;
    private ItemsViewModel itemsViewModel;
    private BagViewModel bagViewModel;
    private ItemViewState itemViewState;
    private LoginViewModel loginViewModel;

    public ViewModelFactory(Model model)
    {
        itemViewState = new ItemViewState();
        productsViewModel = new ProductsViewModel(model, itemViewState);
        itemsViewModel = new ItemsViewModel(model, itemViewState);
        bagViewModel = new BagViewModel(model);
        loginViewModel = new LoginViewModel(model);
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

    public LoginViewModel getLoginViewModel()
    {
        return loginViewModel;
    }
}
