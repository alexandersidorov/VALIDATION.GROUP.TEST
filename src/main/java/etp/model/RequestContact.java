package etp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import etp.groups.ApplicationValidationGroup;
import etp.groups.TemplateValidationGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@ApiModel(description = "Физ.лицо обращения")
public class RequestContact {

    @ApiModelProperty(value = "Фамилия", example = "Иванов", position = 1)
    @NotNull(groups = ApplicationValidationGroup.class)
    private String lastName;

    @ApiModelProperty(value = "Имя", example = "Иван", position = 2)
    @NotNull(groups = ApplicationValidationGroup.class)
    private String firstName;

    @ApiModelProperty(value = "Отчество", example = "Иванович", position = 3)
    @NotNull(groups = ApplicationValidationGroup.class)
    private String middleName;

    @ApiModelProperty(value = "СНИЛС", example = "00009904392", position = 5)
    @NotNull(groups = ApplicationValidationGroup.class)
    @Pattern(regexp = "\\d{11}",groups = {ApplicationValidationGroup.class, TemplateValidationGroup.class})
    private String snils;

    @ApiModelProperty(value = "ИНН", example = "780204893188",  position = 6)
    @NotNull(groups = ApplicationValidationGroup.class)
    @Pattern(regexp = "\\d{12}", groups = {ApplicationValidationGroup.class, TemplateValidationGroup.class})
    private String inn;

    @ApiModelProperty(value = "Мобильный телефон", example = "9500142048", position = 7)
    @NotNull(groups = ApplicationValidationGroup.class)
    @NotBlank(groups = {ApplicationValidationGroup.class, TemplateValidationGroup.class})
    private String mobilePhone;

    @ApiModelProperty(value = "Адрес электронной почты", example = "ivanov@ivan.ru", position = 8)
    @JsonProperty("EMail")
    @NotNull(groups = ApplicationValidationGroup.class)
    @Email(groups = {ApplicationValidationGroup.class, TemplateValidationGroup.class})
    private String eMail;

    @ApiModelProperty(value = "Идентификатор СУДИР", example = "f1a7a95a-f6bf-4e5d-b9b6-49b639319a9d", position = 9)
    @NotNull(groups = ApplicationValidationGroup.class)
    private String ssoId;
}