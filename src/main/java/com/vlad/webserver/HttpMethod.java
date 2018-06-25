package com.vlad.webserver;

public enum HttpMethod {
  GET("GET");

  private String httpMethodName;

  HttpMethod(String httpMethodName) {
    this.httpMethodName = httpMethodName;
  }

  static HttpMethod getHttpMethodByName(String httpMethodName) {
    for (HttpMethod httpMethod : HttpMethod.values()) {
      if (httpMethod.httpMethodName.equalsIgnoreCase(httpMethodName)) {
        return httpMethod;
      }
    }
    throw new IllegalArgumentException("HttpMethodName with name \"" + httpMethodName + "\" not found");
  }
}
