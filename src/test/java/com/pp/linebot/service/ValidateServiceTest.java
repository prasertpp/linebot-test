package com.pp.linebot.service;

import com.pp.linebot.exception.ValidateException;
import com.pp.linebot.service.model.ValidateMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ValidateServiceTest {

    @InjectMocks
    private ValidateService validateService;

    @Test
    public void success_keyword_validateRequest() {
        List<String> successCases = new ArrayList<>();
        successCases.add("task : 12/07/2019 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00");
        successCases.add("task : 12/07/2019 : 14:00 DO VALIDATE SERVICE : 12/07/2019 : 14:30");
        successCases.add("task : 12/07/2019 : 14:30 DO SIMPLE UNIT TEST VALIDATION : 12/07/2019 : 14:50");
        successCases.add("task : today : 14:50 Trying to deploy : 12/07/2019 : 14:55");
        successCases.add("task : Tomorrow : time e.g Sleep : tomorrow : 14:55");

//        TODO case LEAP YEAR

        for (String successOnecase : successCases) {
            try {
                validateService.validateCreateTaskRequest(successOnecase);
            } catch (ValidateException e) {
                Assert.fail("should not be failed");
            }
        }
    }

    @Test
    public void fail_simple_keyword_validateRequest() {

//TODO validate more when colon is connected command, validate when the end time is before start time, validate action when it's empty, validate time with in 24 hrs format.
        //        failCases.add("task: 12/07/2019 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00");

        List<ValidateMessage> failCases = new ArrayList<>();

        failCases.add(new ValidateMessage("","message is null or empty"));
        failCases.add(new ValidateMessage("tsask : 12/07/2019 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00", "wrong command"));

// TODO fix code to accept leap year, wrong month
//        failCases.add(new ValidateMessage("task : 29/02/2019 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 01/13/2019 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 01/01/99 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 2019/01/01 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 01/01/2019 : timee.g. TESTING LINE BOT : 12/07/2019 : 15:00","actionDate is invalid" ));

//        failCases.add(new ValidateMessage("task : 25/02/2019 : time e.g. TESTING LINE BOT : 29/02/2019 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 25/02/2019 : time e.g.\"task : 29/02/2019 : time e.g. TESTING LINE BOT : 12/07/2019 : 15:00\" TESTING LINE BOT : 01/13/2019 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 25/02/2019 : time e.g. TESTING LINE BOT : 01/01/99 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 25/02/2019 : time e.g. TESTING LINE BOT : 2019/01/01 : 15:00","actionDate is invalid" ));
//        failCases.add(new ValidateMessage("task : 25/02/2019 : timee.g. TESTING LINE BOT : 01/01/2019 : 15:00","actionDate is invalid" ));


       for(ValidateMessage failedCase : failCases ){
           try{
               validateService.validateCreateTaskRequest(failedCase.getMessage());
               Assert.fail("should not execute this statement");
           }catch (ValidateException e){
               Assert.assertEquals(failedCase.getExpectResult(),e.getMessage() );
           }
       }
    }
}