package org.example.price.adapters.controllers;

/*
    This class is artificial.
    I wanted to keep the codebase and its dependencies as small as possible to focus on design and clean code.
    In real life I would base my solution on Spring Boot.
 */
public record HttpResponse(int code, Object body) {

    public static HttpResponse ok(Object body) {
        return new HttpResponse(200, body);
    }

    public static HttpResponse notFound() {
        return new HttpResponse(404, null);
    }

}
