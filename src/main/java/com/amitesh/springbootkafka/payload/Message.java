package com.amitesh.springbootkafka.payload;

public record Message(String sender, String receiver, String message) {

}
