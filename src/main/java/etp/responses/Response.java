package etp.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Getter
@Setter
@ApiModel(description = "Success response")
public class Response {

    @ApiModelProperty(value = "Result", example = "SUCCESS", required = true, position = 1)
    private String result;

    @ApiModelProperty(value = "ValidationErrors", position = 3)
    private List<ValidError> validErrors;


    public Response(String result, List<ValidError> validErrors) {
        this.result = result;
        this.validErrors = validErrors;
    }

    public Response(String result) {
        this.result = result;
    }
}
