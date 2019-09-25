package com.library.local.genders.service;

import com.library.local.genders.builder.GenderAddDTOBuilder;
import com.library.local.genders.builder.GenderChangeDTOBuilder;
import com.library.local.genders.mapper.GenderMapper;
import com.library.local.genders.model.dto.GenderAddDTO;
import com.library.local.genders.model.dto.GenderChangeDTO;
import com.library.local.genders.model.dto.GenderDTO;
import com.library.local.genders.service.http.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="Genders Books WS", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenderRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenderRestService.class);

    private final GenderMapper genderMapper;
    private final GenderChangeDTOBuilder genderChangeDTOBuilder;
    private final GenderAddDTOBuilder genderAddDTOBuilder;

    @Autowired
    public GenderRestService(GenderMapper genderMapper,
                             GenderChangeDTOBuilder genderChangeDTOBuilder,
                             GenderAddDTOBuilder genderAddDTOBuilder) {
        this.genderMapper = genderMapper;
        this.genderChangeDTOBuilder = genderChangeDTOBuilder;
        this.genderAddDTOBuilder = genderAddDTOBuilder;
    }

    @GetMapping(
            value = "books/genders",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Obtener todos los géneros de los libros")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtienen los géneros de los libros", response = GendersResponse.class),
            @ApiResponse(code = 206, message = "No se obtuvo la informacion de los géneros", response = GendersResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = GendersResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = GendersResponse.class)
    })
    public ResponseEntity<GendersResponse> obtainGenders(){
        try{
            final List<GenderDTO> genders = genderMapper.obtainGenders();
            LOGGER.info("Se obtuvieron todos los géneros");
            return ResponseEntity.ok(new GendersResponse(genders));
        }catch (IllegalArgumentException e) {
            LOGGER.warn("Argumentos inválidos al intentar obtener los géneros de los libros", e.getMessage());
            return ResponseEntity.badRequest().body(new GendersResponse(e.getMessage()));
        }catch (Exception e) {
            LOGGER.error("Ocurrio un error inesperado al intentar obtener los generos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GendersResponse(e.getMessage()));
        }
    }

    @PostMapping(
            value = "/books/{idGender}/gender",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Actualizar un género de los libros")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se actualiza un género para los libros", response = GenderChangeResponse.class),
            @ApiResponse(code = 206, message = "El género no fue actualizado correctamente", response = GenderChangeResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = GenderChangeResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = GenderChangeResponse.class),
    })
    public ResponseEntity<GenderChangeResponse> updateGenderDescription(@RequestBody GenderChangeRequest genderChangeRequest,
                                                                        @PathVariable Integer idGender) {
        try {
            GenderChangeDTO genderChangeDTO = genderChangeDTOBuilder.apply(genderChangeRequest,idGender);
            try {
                genderMapper.updateGenderDescription(genderChangeDTO);
            }catch (Exception ex){
                LOGGER.error("Ocurrio un error al intentar actualizar el género con id {}", idGender, ex);
            }
            LOGGER.info("Se actualizó correctamente el género con id {}", idGender);
            return ResponseEntity.ok(new GenderChangeResponse(genderChangeDTO.getResult()));
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("Argumentos inválidos al intentar actualizar el género: {} ", iae.getMessage());
            return ResponseEntity.badRequest().body(new GenderChangeResponse(iae.getMessage()));
        } catch (Exception ex) {
            LOGGER.error("Ocurrio un error inesperado al intentar actualizar el género con id {} ", idGender, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenderChangeResponse(ex.getMessage()));
        }
    }

    @PostMapping(
            value = "/books/gender/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Crea un género para los libros")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se crea un género para los libros", response = GenderAddResponse.class),
            @ApiResponse(code = 206, message = "El género no fue creado correctamente", response = GenderAddResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = GenderAddResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = GenderAddResponse.class),
    })
    public ResponseEntity<GenderAddResponse> insertGender(@RequestBody GenderAddRequest genderAddRequest) {

        try {
            GenderAddDTO genderAddDTO = genderAddDTOBuilder.apply(genderAddRequest);
            try {
                genderMapper.insertGender(genderAddDTO);
            }catch (Exception ex){
                LOGGER.error("Ocurrio un error inesperado al intentar crear el género con descripción {} ",
                        genderAddDTO.getDescription(), ex);
            }
            LOGGER.info("Se creó correctamente el género con descripción {}", genderAddDTO.getDescription());
            return ResponseEntity.ok(new GenderAddResponse(genderAddDTO.getResult()));
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("Argumentos inválidos al intentar crear el género: {} ", iae.getMessage());
            return ResponseEntity.badRequest().body(new GenderAddResponse(iae.getMessage()));
        } catch (Exception ex) {
            LOGGER.error("Ocurrio un error inesperado al intentar crear el género con descripción {} ",
                    genderAddRequest.getDescription(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenderAddResponse(ex.getMessage()));
        }
    }

}
