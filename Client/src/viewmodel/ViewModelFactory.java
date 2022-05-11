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
    private AddProductViewModel addProductViewModel;
    private EmployeeViewModel employeeViewModel;
    private ShopViewState shopViewState;
    private ShopsViewModel shopsViewModel;
    private UserViewState userViewState;

    public ViewModelFactory(Model model)
    {
        userViewState = new UserViewState();
        itemViewState = new ItemViewState();
        shopViewState = new ShopViewState();
        shopsViewModel = new ShopsViewModel(model, shopViewState);
        productsViewModel = new ProductsViewModel(model, itemViewState, shopViewState);
        itemsViewModel = new ItemsViewModel(model, itemViewState, shopViewState);
        bagViewModel = new BagViewModel(model, shopViewState);
        loginViewModel = new LoginViewModel(model, userViewState);
        addProductViewModel = new AddProductViewModel(model,userViewState);
        employeeViewModel = new EmployeeViewModel(model, userViewState);
    }

    public ShopsViewModel getShopsViewModel()
    {
        return shopsViewModel;
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

    public EmployeeViewModel getEmployeeViewModel()
    {
        return employeeViewModel;
    }

    public AddProductViewModel getAddProductViewModel()
    {
        return addProductViewModel;
    }
}
