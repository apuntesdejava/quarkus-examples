package com.example.project.request;

import java.time.LocalDate;

public record ProjectRequest(String name,
                             LocalDate startDate,
                             LocalDate endDate) {
}
