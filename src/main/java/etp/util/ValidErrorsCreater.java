package etp.util;

import etp.responses.ValidError;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ValidErrorsCreater {

    public static List<ValidError> getList(BindingResult bindingResult){
        List<ValidError> validErrors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(e -> {
            if (e instanceof FieldError)
                validErrors.add(new ValidError(((FieldError) e).getField(),
                        e.getDefaultMessage()));

            else
                validErrors.add(new ValidError(e.getObjectName().substring(e.getObjectName().lastIndexOf('.') + 1),
                        e.getDefaultMessage()));

        });

        return validErrors;
    }
}
