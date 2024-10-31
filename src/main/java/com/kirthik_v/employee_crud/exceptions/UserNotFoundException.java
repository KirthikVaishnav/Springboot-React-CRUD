package com.kirthik_v.employee_crud.exceptions;

public class UserNotFoundException extends RuntimeException{
   public UserNotFoundException(long id){
       super("User id:" +id+ " is not present");
   }
}
