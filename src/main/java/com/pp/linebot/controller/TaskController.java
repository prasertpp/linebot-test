package com.pp.linebot.controller;

import com.pp.linebot.exception.ValidateException;
import com.pp.linebot.model.request.CreateTaskRequest;
import com.pp.linebot.service.TaskService;
import com.pp.linebot.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ValidateService validateService;

    @PostMapping("/task")
    public ResponseEntity<Object> createTask(@RequestBody CreateTaskRequest request) throws ValidateException {

        validateService.validateCreateTaskRequest(request.getMessage());

        return new ResponseEntity<Object>("success", HttpStatus.OK);
    }




}
