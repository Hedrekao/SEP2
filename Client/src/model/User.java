package model;

import java.io.Serializable;

public class User implements Serializable
{
  private String username;
  private int hashPassword;

  public User(String username, String password)
  {
    if(username == null || password == null || username.length() <= 8 || password.length() <= 8 || username.length() >= 20 || password.length() >= 20)
    {
      throw new IllegalArgumentException("Username and password needs to have between 8 and 20 characters");
    }
    this.username = username;
    this.hashPassword = hash(password);
  }
  public User(String username, int hashValue)
  {
    this.username = username;
    this.hashPassword = hashValue;
  }

  public String getUsername()
  {
    return username;
  }

  public int getHashPassword()
  {
    return hashPassword;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof User))
    {
      return false;
    }
    User other = (User) obj;
    return this.hashPassword == other.hashPassword && this.username.equals(other.username);
  }

  private int hash(String password)
  {
    return password.hashCode();
  }
}
