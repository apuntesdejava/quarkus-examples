package com.example.project.type;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    public static TaskStatus from(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.name().equalsIgnoreCase(status)) {
                return taskStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
