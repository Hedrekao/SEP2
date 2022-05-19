package model;

public interface ClientUserModel extends ModelUser
{
  void setDelivery(String pickUpTime);
  void setDelivery(String addressLinePrimary, String addressLineSecondary, String city, int postalCode);
  void setEmail(String email);
  void setPayment(String cardName, long cardNumber, int expirationMonth, int expirationYear, int securityCode);
  Order getOrder();
  void clearBag();
  void removeItemFromBag(Item item);
}
