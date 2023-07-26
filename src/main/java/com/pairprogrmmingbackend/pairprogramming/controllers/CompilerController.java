package com.pairprogrmmingbackend.pairprogramming.controllers;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CompilerController {

    @PostMapping("/compile")
    public ResponseEntity<String> compileCOde(@RequestBody String body) {
        String response = "";
        System.out.println("[compilerController] compiling..");

        // Replace this RapidAPI Key with the actual key you obtained from RapidAPI
        String rapidApiKey = "0ca1f29d51mshb986e0c32e43c2fp154775jsn238ffb60bb8b";

        try (AsyncHttpClient client = new DefaultAsyncHttpClient()) {
            response = client.preparePost("https://onecompiler-apis.p.rapidapi.com/api/v1/run")
                    .setHeader("content-type", "application/json")
                    .setHeader("X-RapidAPI-Key", rapidApiKey)
                    .setHeader("X-RapidAPI-Host", "onecompiler-apis.p.rapidapi.com")
                    .setBody(body)
                    .execute()
                    .toCompletableFuture()
                    .thenApply(Response::getResponseBody)
                    .join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

}
