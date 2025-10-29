package ru.netology;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskTrackerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void checkTaskTracker() throws Exception {
        var gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        mvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        var taskData = new TaskWithoutId("", "", false, LocalDateTime.of(2025, 10, 28, 21, 58));

        var idStr = mvc.perform(
                post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(taskData))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = Long.parseLong(idStr);

        var responseBody = mvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Task> allTasks = gson.fromJson(responseBody, new TypeToken<ArrayList<Task>>(){}.getType());

        Assertions.assertEquals(1, allTasks.size());

        Assertions.assertEquals(new Task(taskData, id), allTasks.getFirst());

        taskData.setCompleted(true);

        mvc.perform(
                        put("/tasks/"+id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(taskData))
                )
                .andExpect(status().isOk());

        responseBody = mvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        allTasks = gson.fromJson(responseBody, new TypeToken<ArrayList<Task>>(){}.getType());

        Assertions.assertEquals(1, allTasks.size());

        Assertions.assertEquals(new Task(taskData, id), allTasks.getFirst());

        mvc.perform(
                        delete("/tasks/"+id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(taskData))
                )
                .andExpect(status().isOk());

        mvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
