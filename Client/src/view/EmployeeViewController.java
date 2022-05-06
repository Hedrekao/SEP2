//package view;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//
//import java.io.IOException;
//
//public class EmployeeViewController extends ViewController
//{
//    @FXML private Label usernameLabel;
//    @FXML private Label workplaceLabel;
//
//    private EmployeeViewModel employeeViewModel;
//
//    protected void init()
//    {
//        usernameLabel.textProperty().bind(employeeViewModel.getUsernameLabelProperty());
//        workplaceLabel.textProperty().bind(employeeViewModel.getWorkplaceLabelProperty());
//    }
//
//    public void reset()
//    {
//        employeeViewModel.clear();
//    }
//
//    @FXML private void addProductsButton() throws IOException
//    {
//        getViewHandler().openView("EmployeeView");
//    }
//
//}
