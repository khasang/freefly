package com.demo.soap;

import javax.xml.ws.Endpoint;

public class Server {

    private final static String ADDRESS = "http://127.0.0.1:12345/Service";

    public Server() {
        System.out.println("Server starting...");
        Object implementator = new HelloImpl();
        Endpoint.publish(ADDRESS, implementator);
    }

    public static void main(String[] args) {
        new Server();

        System.out.println("Service listening at " + ADDRESS);
        System.out.println("WSDL hosted at " + ADDRESS + "/?wsdl");
    }
}
