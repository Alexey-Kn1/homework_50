package ru.netology;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private long id;
    private String title;
    private String content;
    private boolean completed;
    private LocalDateTime createdAt;

    public Task(TaskWithoutId taskWithoutId, long id) {
        this(id, taskWithoutId.getTitle(), taskWithoutId.getContent(), taskWithoutId.isCompleted(), taskWithoutId.getCreatedAt());
    }

    public Task(long id, String title, String content, boolean completed, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.completed = completed;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        Task task = (Task) o;
        return id == task.id && completed == task.completed && Objects.equals(title, task.title) && Objects.equals(content, task.content) && Objects.equals(createdAt, task.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, completed, createdAt);
    }
}
