package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject, ModelEmployee, ModelUser
{


}
