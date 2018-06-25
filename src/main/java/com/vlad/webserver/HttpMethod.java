package com.vlad.webserver;

public enum HttpMethod {
  GET("GET");

  private String httpMethodName;

  HttpMethod(String httpMethodName) {
    this.httpMethodName = httpMethodName;
  }

  static HttpMethod getHttpMethodByName(String name) {
    for (HttpMethod httpMethod : HttpMethod.values()) {
      if (httpMethod.httpMethodName.equalsIgnoreCase(name)) {
        return httpMethod;
      }
    }
    throw new IllegalArgumentException("HttpMethodName with name \"" + name + "\" not found");
  }
}
