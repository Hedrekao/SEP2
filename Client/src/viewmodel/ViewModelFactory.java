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
    private UserViewState userViewState;

    public ViewModelFactory(Model model)
    {
        userViewState = new UserViewState();
        itemViewState = new ItemViewState();
        productsViewModel = new ProductsViewModel(model, itemViewState);
        itemsViewModel = new ItemsViewModel(model, itemViewState);
        bagViewModel = new BagViewModel(model);
        loginViewModel = new LoginViewModel(model, userViewState);
        addProductViewModel = new AddProductViewModel(model);
        employeeViewModel = new EmployeeViewModel(model, userViewState);
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
