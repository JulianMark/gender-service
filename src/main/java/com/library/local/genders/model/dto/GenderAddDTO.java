package com.library.local.genders.model.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class GenderAddDTO {
    private String description;
    private Integer result;

    public GenderAddDTO(String description) {
        this.description = description;
    }
}
