package com.duberlyguarnizo.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/main")
public class MainController {
    @GET
    public String getMain() {
        return "Hello World!";
    }
}
