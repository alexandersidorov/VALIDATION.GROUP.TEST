package etp.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Data
@AllArgsConstructor
@ApiModel(description = "Valid errors")
public class ValidError {

    @ApiModelProperty(value = "Field", required = true, position = 1)
    private String field;

    @ApiModelProperty(value = "Message", required = true, position = 2)
    private String message;

}
