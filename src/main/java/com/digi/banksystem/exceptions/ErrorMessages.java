package com.digi.banksystem.exceptions;

public interface ErrorMessages {


    String NOT_FOUND_USER = "User not found with given email";
    String NOT_MATCH = "passwords don't match";
    String NOT_FOUND_PASSWORD = "user not found with given password";
    String TOKEN_NOT_MATCH = "token dont not match";
    String OPERATION_EXCEPTION = "There is not enough money in the account";
    String TRANSFER_EXCEPTION = "Account currencies do not match";

}
