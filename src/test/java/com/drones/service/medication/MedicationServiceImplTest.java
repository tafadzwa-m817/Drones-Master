package com.drones.service.medication;

import com.drones.domain.Medication;
import com.drones.errors.InvalidInputParameterException;
import com.drones.persistence.MedicationRepository;
import com.drones.service.medication.MedicationRequest;
import com.drones.service.medication.MedicationService;
import com.drones.service.medication.MedicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static com.drones.util.AppConstants.IN_VALID_CODE;
import static com.drones.util.AppConstants.IN_VALID_NAME;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Romeo Jerenyama
 * @created 21/01/2023 - 11:20
 */
@ExtendWith(MockitoExtension.class)
class MedicationServiceImplTest {

    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private ModelMapper modelMapper;
    private Medication medication;
    private MedicationService underTest;
    @BeforeEach
    void setUp() {
        underTest = new MedicationServiceImpl(medicationRepository, modelMapper);
        medication = Medication.builder()
                .id(1l)
                .image("Abc")
                .code("Y98")
                .weight(54)
                .name("Flu-med")
                .build();
    }

    @Test
    @DisplayName("saveMedication should save medication")
    void givenValidMedicationRequestShouldSaveMedication() {
        //GIVEN
        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setCode("A452");
        medicationRequest.setName("Paracetamol");
        medicationRequest.setImage("Image");
        medicationRequest.setWeight(100);

        //WHEN
        underTest.saveMedication(medicationRequest);

        //THEN
        Medication expectedMedication = Medication.builder()
                .name(medicationRequest.getName())
                .code(medicationRequest.getCode())
                .image(medicationRequest.getImage())
                .weight(medicationRequest.getWeight())
                .build();

        ArgumentCaptor<Medication> medicationArgumentCaptor =
                ArgumentCaptor.forClass(Medication.class);
        verify(medicationRepository).save(medicationArgumentCaptor.capture());

        Medication capturedMedication = medicationArgumentCaptor.getValue();

        assertThat(capturedMedication).isEqualTo(expectedMedication);

    }
    @Test
    void throwsInvalidInputParameterExceptionWhenGivenInvalidCode(){
        //GIVEN
        String code = "#h7";
        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setCode(code);
        medicationRequest.setName("Paracetamol");
        medicationRequest.setImage("Image");
        medicationRequest.setWeight(100);

        //WHEN
        //THEN
        assertThatThrownBy(() -> underTest.saveMedication(medicationRequest))
                .isInstanceOf(InvalidInputParameterException.class)
                .hasMessageContaining(IN_VALID_CODE, medicationRequest.getCode());

    }
    @Test
    void throwsInvalidInputParameterExceptionWhenGivenInvalidName(){
        //GIVEN
        String name = "@Paracetamol";
        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setCode("A5");
        medicationRequest.setName(name);
        medicationRequest.setImage("Image");
        medicationRequest.setWeight(100);

        //WHEN
        //THEN
        assertThatThrownBy(() -> underTest.saveMedication(medicationRequest))
                .isInstanceOf(InvalidInputParameterException.class)
                .hasMessageContaining(IN_VALID_NAME, medicationRequest.getName());

    }
    @Test
    void givenValidIdShouldRetrieveMedication() {
        //GIVEN
        Long validId = 1l;

        given(medicationRepository.findById(validId))
                .willReturn(Optional.ofNullable(medication));

        //WHEN
        underTest.findMedicationById(medication.getId());
    }
}