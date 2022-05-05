package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable
{
  private ArrayList<User> list;

  public UserList()
  {
    this.list = new ArrayList<>();
  }

  public User getUser(String username, String password)
  {
    for(User u : list)
    {
      if(u.getPassword().equals(password) && u.getUsername().equals(username))
      {
        return u;
      }
    }
    throw new IllegalArgumentException("Such user does not exist, check username and password.");
  }

  public void addUser(String  username, String password)
  {
    if(isLegalUsername(username))
    {
      addUser(username, password);
    }
    else
    {
      throw new IllegalArgumentException("Such username already exists.");
    }
  }

  public boolean isLegalUsername(String username)
  {
    for(User u : list)
    {
      if(u.getUsername().equals(username))
      {
        throw new IllegalArgumentException("Such username already exists.");
        //could also return false but this way we don't need to write it in view model just set errorLabel to what is caught
      }
    }
    return true;
  }
}
