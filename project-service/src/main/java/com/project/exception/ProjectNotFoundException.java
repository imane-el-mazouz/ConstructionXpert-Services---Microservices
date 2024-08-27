package com.project.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long id){
        super("project with this id not found " + id);
    }
}
