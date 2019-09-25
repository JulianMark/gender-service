package com.library.local.genders.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class GenderAddResponse {

    @ApiModelProperty(notes = "Resultado de la operación, el valor de éxito es 0")
    private Integer result;

    @ApiModelProperty(notes = "Mensaje de error, en caso de que falle el WS")
    private String errorMessage;

    public GenderAddResponse(Integer result){
        this.result = result;
    }

    public GenderAddResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
