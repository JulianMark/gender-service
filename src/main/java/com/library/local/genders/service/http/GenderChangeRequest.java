package com.library.local.genders.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class GenderChangeRequest {

    @ApiModelProperty(notes="Nuevo nombre de género", required = true, example = "DESCRIPCIÓN")
    private String description;

}

