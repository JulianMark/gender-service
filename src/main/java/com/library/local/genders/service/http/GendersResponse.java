package com.library.local.genders.service.http;

import com.library.local.genders.model.dto.GenderDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class GendersResponse {

    @ApiModelProperty(notes="Listado de los géneros de los libros")
    private List<GenderDTO> genders;

    @ApiModelProperty(notes="Mensaje de error de listado de los géneros de los libros")
    private String errorMessage;

    public GendersResponse(List<GenderDTO> genders) {
        this.genders = genders;
    }

    public GendersResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<GenderDTO> getGenders() {
        return genders;
    }

    public void setGenders(List<GenderDTO> genders) {
        this.genders = genders;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
