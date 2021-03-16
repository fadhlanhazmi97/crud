package fadhlan.exercise.crud.usecase.getperson;

import fadhlan.exercise.crud.entity.PersonReadModel;
import fadhlan.exercise.crud.exception.InvalidRequestException;
import fadhlan.exercise.crud.gateway.PersonGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetPersonUseCaseTest {

    @Mock
    private PersonGateway personGateway;

    @InjectMocks
    private GetPersonUseCase useCase;

    @Captor
    ArgumentCaptor<Integer> intCaptor;

    @Captor
    ArgumentCaptor<GetPersonResponseModel> responseCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenInvalidRequest_whenGetPerson_shouldThrowException() {
        GetPersonRequestModel requestModel = new GetPersonRequestModel(null);
        assertThrows(InvalidRequestException.class, () -> useCase.getPerson(requestModel, responseModel -> {}),
                "Id cannot be null");
    }

    @Test
    void givenNullRequest_whenGetPerson_shouldThrowException() {
        assertThrows(InvalidRequestException.class, () -> useCase.getPerson(null, responseModel -> {}),
                "Request model cannot be null");
    }

    @Test
    void givenValidRequest_whenGetPerson_shouldCallGatewayWithCorrectParam() {
        GetPersonRequestModel requestModel = new GetPersonRequestModel(1);
        useCase.getPerson(requestModel, responseModel -> {});
        verify(personGateway, times(1)).getPerson(intCaptor.capture());
        assertNotNull(intCaptor.getValue());
        assertEquals(requestModel.getId(), intCaptor.getValue());
    }

    @Test
    void givenPersonFound_whenCallGetPersonGateway_shouldCallPresenterWithCorrectResponse() {
        GetPersonRequestModel requestModel = new GetPersonRequestModel(1);
        GetPersonOutputBoundary presenter = mock(GetPersonOutputBoundary.class);
        PersonReadModel personReadModel = getPersonReadModel();
        when(personGateway.getPerson(anyInt())).thenReturn(Optional.of(personReadModel));
        useCase.getPerson(requestModel, presenter);
        verify(presenter, times(1)).present(responseCaptor.capture());
        GetPersonResponseModel responseModel = responseCaptor.getValue();
        assertEquals(personReadModel.getId(), responseModel.getId());
        assertEquals(personReadModel.getName(), responseModel.getName());
        assertEquals(personReadModel.getAge(), responseModel.getAge());
        assertEquals(personReadModel.getGender(), responseModel.getGender());
        assertEquals(personReadModel.getHobby(), responseModel.getHobby());
    }

    @Test
    void givenPersonNotFound_whenCallGetPersonGateway_shouldCallPresenterWithCorrectResponse() {
        GetPersonRequestModel requestModel = new GetPersonRequestModel(1);
        GetPersonOutputBoundary presenter = mock(GetPersonOutputBoundary.class);
        when(personGateway.getPerson(anyInt())).thenReturn(Optional.empty());
        useCase.getPerson(requestModel, presenter);
        verify(presenter, times(1)).present(responseCaptor.capture());
        assertNull(responseCaptor.getValue());
    }

    private PersonReadModel getPersonReadModel() {
        return PersonReadModel.builder().id(1).name("fadhlan").age(20).gender("Male").hobby("Playing").build();
    }

}
