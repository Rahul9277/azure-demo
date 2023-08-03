package org.tvs.model;

public class Response {

  private String message;
  private String token;

  public Response(String message) {
    this.message = message;
  }

  public Response(String message, String token) {
    this.message = message;
    this.token = token;
  }
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getToken() {
    return token;
  }
}
