package model;

import java.io.Serializable;

public class User implements Serializable
{
  private String username;
  private String password;

  public User(String username, String password)
  {
    if(username == null || password == null || username.length() <= 8 || password.length() <= 8 || username.length() >= 20 || password.length() >= 20)
    {
      throw new IllegalArgumentException("Username and password needs to have between 8 and 20 characters");
    }
    this.username = username;
    this.password = password;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUsername()
  {
    return username;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof User))
    {
      return false;
    }
    User other = (User) obj;
    return this.password.equals(other.password) && this.username.equals(other.username);
  }
}
