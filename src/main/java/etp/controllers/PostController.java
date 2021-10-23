package etp.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import etp.groups.ApplicationValidationGroup;
import etp.groups.TemplateValidationGroup;
import etp.model.RequestContact;
import etp.responses.ErrorResponse;
import etp.responses.Response;
import etp.responses.ValidError;
import etp.util.ValidErrorsCreater;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Test validation group")
public class PostController {

    @Autowired
    private SmartValidator validator;

    private static final String OK = "SUCCESS";
    private static final String ERR = "FAIL";

    private final ObjectMapper mapper = new ObjectMapper();

    @ApiOperation(value = "Validate body with annotations from group 'ApplicationValidationGroup'")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Response.class),
            @ApiResponse(code = 400, message = "Fail", response = Response.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request body", dataType = "RequestContact", value = "Request body", required = true, paramType = "body")
    })
    @PostMapping(value = "/applicationValid")
    public ResponseEntity<Response> postApplication(
            @ApiParam(hidden = true)
            @RequestHeader HttpHeaders headers,
            @ApiParam(name = "request body", value = "Тело запроса", required = true)
            @RequestBody JsonNode rawBody
    ) {

        RequestContact contact;
        try {
            contact = mapper.convertValue(rawBody, RequestContact.class);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new ErrorResponse(ERR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        BindingResult bindingResult = new BeanPropertyBindingResult(contact, contact.getClass().getName());
        validator.validate(contact,bindingResult,ApplicationValidationGroup.class);
        if (bindingResult.hasErrors()) {
            List<ValidError> validErrors = ValidErrorsCreater.getList(bindingResult);
            return new ResponseEntity<>(new Response(ERR, validErrors), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Response(OK, new ArrayList<>()), HttpStatus.OK);
    }


    @ApiOperation(value = "Validate body with annotations from group 'TemplateValidationGroup'")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Response.class),
            @ApiResponse(code = 400, message = "Fail", response = Response.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request body", dataType = "RequestContact", value = "Request body", required = true, paramType = "body")
    })
    @PostMapping(value = "/templateValid")
    public ResponseEntity<Response> postTemplate(
            @ApiParam(hidden = true)
            @RequestHeader HttpHeaders headers,
            @ApiParam(name = "request body", value = "Тело запроса", required = true)
            @RequestBody JsonNode rawBody
    ) {

        RequestContact contact;
        try {
            contact = mapper.convertValue(rawBody, RequestContact.class);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new ErrorResponse(ERR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        BindingResult bindingResult = new BeanPropertyBindingResult(contact, contact.getClass().getName());
        validator.validate(contact,bindingResult, TemplateValidationGroup.class);
        if (bindingResult.hasErrors()) {
            List<ValidError> validErrors = ValidErrorsCreater.getList(bindingResult);
            return new ResponseEntity<>(new Response(ERR, validErrors), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Response(OK, new ArrayList<>()), HttpStatus.OK);
    }

}
