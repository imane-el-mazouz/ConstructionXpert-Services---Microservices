

package com.task.service;

import com.task.exception.ProjectNotFoundException;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired


    private final RestTemplate restTemplate;

    private static final String PROJECT_SERVICE_URL = "http://project-service/api/project";

    public Task addTask(Task task) {
        String url = PROJECT_SERVICE_URL + "/" + task.getProjectId() + "/exist";
        Boolean existProject = restTemplate.getForObject(url, Boolean.class);

        if (existProject != null && existProject) {
            return taskRepository.save(task);
        } else {
            throw new ProjectNotFoundException("Project with ID " + task.getProjectId() + " does not exist.");
        }
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


}