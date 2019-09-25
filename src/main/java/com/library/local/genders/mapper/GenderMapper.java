package com.library.local.genders.mapper;

import com.library.local.genders.model.dto.GenderAddDTO;
import com.library.local.genders.model.dto.GenderChangeDTO;
import com.library.local.genders.model.dto.GenderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GenderMapper {

    List<GenderDTO> obtainGenders();

    void updateGenderDescription(@Param("genderChangeDTO") GenderChangeDTO genderChangeDTO);

    void insertGender(@Param("genderAddDTO")GenderAddDTO genderAddDTO);
}
