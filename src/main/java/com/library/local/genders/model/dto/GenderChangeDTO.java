package com.library.local.genders.model.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class GenderChangeDTO extends GenderDTO {
    private String errorMessage;
    private Integer result;

    public GenderChangeDTO(Integer id, String description) {
        super(id, description);
    }
}
