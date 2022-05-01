package model;

import java.io.Serializable;

public class Category implements Serializable
{
  private static final String[] NAMES = {"Frozen Food", "Meat & Seafood", "Diary", "Bakery", "Beverages", "Snacks", "Fruits", "Vegetables"};
  private String name;

  public Category(String name)
  {
    boolean cond = false;

    for(String s : NAMES)
    {
      if(name.equals(s))
      {
        cond = true;
      }
    }

    if(cond)
    {
      this.name = name;
    }
    else
    {
      throw new IllegalArgumentException("Such category does not exist");
    }
  }

  public String getName()
  {
    return name;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Category))
    {
      return false;
    }
    Category other = (Category) obj;
    return this.name.equals(other.name);
  }
}
