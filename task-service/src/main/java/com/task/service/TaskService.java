package com.task.service;

import com.task.exception.ProjectNotFoundException;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    private final RestTemplate restTemplate;

    private static final String PROJECT_SERVICE_URL = "http://project-service/api/project";

    public Task AddTask(Task task) {
        Boolean existProject = restTemplate.getForObject(PROJECT_SERVICE_URL + "/" + task.getProjectId() + "/exist", Boolean.class);
        if (Boolean.TRUE.equals(existProject)) {
            return taskRepository.save(task);
        } else {
            throw new ProjectNotFoundException("Project with ID " + task.getProjectId() + " does not exist.");
        }
    }


}
