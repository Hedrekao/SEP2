package viewmodel;

import model.Product;

public class UserViewState
{
  private String username;

  public UserViewState()
  {
    username = null;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getUsername()
  {
    return username;
  }
}
