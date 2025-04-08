package com.example.project.request;

public record TaskRequest( String name,
                           String description,
                           Long projectId,
                           String status) {
}
