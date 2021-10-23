package etp.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Getter
@Setter
@ApiModel(description = "Error response")
public class ErrorResponse extends Response {

    @ApiModelProperty(value = "Message", example = "Some message with error", required = true, position = 2)
    private String message;

    public ErrorResponse(String result,String message){
        super(result);
        this.message = message;
    }

}
