package ru.netology;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tasks")
public class TaskTrackerController {
    private final List<Task> tasks;

    public TaskTrackerController() {
        tasks = new ArrayList<>();
    }

    @PostMapping
    public long addTask(@RequestBody TaskWithoutId task) {
        long id = tasks.size();

        tasks.add(new Task(task, id));

        return id;
    }

    @GetMapping
    public List<Task> getTasks() {
        return tasks.stream().filter(Objects::nonNull).toList();
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable long id, @RequestBody TaskWithoutId task) {
        if (id >= tasks.size() || tasks.get((int)id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        tasks.set((int)id, new Task(task, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if (id >= tasks.size() || tasks.get((int)id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        tasks.set((int)id, null);
    }
}
