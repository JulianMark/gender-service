package com.library.local.genders.service.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class GenderAddRequest {

    @ApiModelProperty(notes="Nueva descripción para el nuevo género", required = true, example = "DESCRIPCIÓN")
    private String description;

}

