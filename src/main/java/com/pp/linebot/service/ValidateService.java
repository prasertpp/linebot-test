package com.pp.linebot.service;

import com.pp.linebot.exception.ValidateException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ValidateService {


    public void validateCreateTaskRequest(String message) throws ValidateException {

        if (StringUtils.isEmpty(message)) {
            throw new ValidateException("message is null or empty");
        }

        String[] words = message.split(" :");

        String command = words[0];

        if (!"task".equalsIgnoreCase(command)) {
            throw new ValidateException("wrong command");
        }
        String dateStartAction = words[1];

        if (!isvalidDateFormat(dateStartAction)) {
            throw new ValidateException("actionDate is invalid");
        }

        String timeStartAction = words[2];

//      TODO validate time with action is taking a time so skipped first,


        String dateTimeEndAction = words[3];
        if (!isvalidDateFormat(dateTimeEndAction)) {
            throw new ValidateException("dateTimeEndAction is invalid");
        }
//        TODO create Date from start and END and check whether it's goes before or after



    }


    private boolean isvalidDateFormat(String message) {
        if (message.trim().equalsIgnoreCase("today") || message.trim().equalsIgnoreCase("tomorrow") ) {
            return true;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                simpleDateFormat.parse(message);
            } catch (ParseException e) {
                return false;
            }

        }
        return true;
    }

}
