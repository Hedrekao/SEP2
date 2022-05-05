package model;

public interface ModelEmployee
{
  void addUser(String username, String password);
  User getUser(String username, String password);
}
