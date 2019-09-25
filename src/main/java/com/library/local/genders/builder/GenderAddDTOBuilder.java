package com.library.local.genders.builder;

import com.library.local.genders.model.dto.GenderAddDTO;
import com.library.local.genders.service.http.GenderAddRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GenderAddDTOBuilder implements Function<GenderAddRequest, GenderAddDTO> {
    @Override
    public GenderAddDTO apply(GenderAddRequest genderAddRequest) {
        return new GenderAddDTO(genderAddRequest.getDescription());
    }
}
