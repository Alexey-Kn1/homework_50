package ru.netology;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskWithoutId {
    private String title;
    private String content;
    private boolean completed;
    private LocalDateTime createdAt;

    public TaskWithoutId(String title, String content, boolean completed, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.completed = completed;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskWithoutId task = (TaskWithoutId) o;
        return completed == task.completed && Objects.equals(title, task.title) && Objects.equals(content, task.content) && Objects.equals(createdAt, task.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, completed, createdAt);
    }
}
