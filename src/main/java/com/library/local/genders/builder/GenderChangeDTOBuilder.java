package com.library.local.genders.builder;

import com.library.local.genders.model.dto.GenderChangeDTO;
import com.library.local.genders.service.http.GenderChangeRequest;
import org.springframework.stereotype.Component;
import java.util.function.BiFunction;

@Component
public class GenderChangeDTOBuilder implements BiFunction<GenderChangeRequest, Integer, GenderChangeDTO> {

    @Override
    public GenderChangeDTO apply(GenderChangeRequest genderChangeRequest, Integer idGender) {
        return new GenderChangeDTO(idGender,genderChangeRequest.getDescription());
    }
}
