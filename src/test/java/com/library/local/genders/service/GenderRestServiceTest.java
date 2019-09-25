package com.library.local.genders.service;

import com.library.local.genders.service.http.GenderChangeRequest;
import com.library.local.genders.service.http.GenderChangeResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class GenderRestServiceTest {

    @InjectMocks
    private GenderRestService sut;

    @Spy
    private GenderChangeRequest genderChangeRequest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_service_receives_null_id_gender_number_then_respond_400(){
        ResponseEntity<GenderChangeResponse> response = sut.updateGenderDescription(genderChangeRequest, null);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().getErrorMessage(), is("El id del género no puede ser nulo"));

    }
}