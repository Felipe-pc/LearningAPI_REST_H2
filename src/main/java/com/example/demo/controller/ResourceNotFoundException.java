package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

  /**
   * Excepcion si no se encuentra objeto
   *
   * @param message the message
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
