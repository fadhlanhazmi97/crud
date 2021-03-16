package fadhlan.exercise.crud.controller;

import fadhlan.exercise.crud.controller.request.GetPersonRequest;
import fadhlan.exercise.crud.controller.response.GetPersonResponse;
import fadhlan.exercise.crud.usecase.getperson.GetPersonInputBoundary;
import fadhlan.exercise.crud.usecase.getperson.GetPersonOutputBoundary;
import fadhlan.exercise.crud.usecase.getperson.GetPersonRequestModel;
import fadhlan.exercise.crud.usecase.getperson.GetPersonResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

class GetPersonControllerTest {

    @Mock
    private GetPersonInputBoundary useCase;

    @InjectMocks
    private GetPersonController controller;

    @Captor
    private ArgumentCaptor<GetPersonRequestModel> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenRequest_whenGetPerson_shouldConstructCorrectRequestModel() {
        GetPersonRequest request = new GetPersonRequest(1);
        controller.getPerson(request);
        verify(useCase).getPerson(captor.capture(), any());
        GetPersonRequestModel requestModel = captor.getValue();
        assertNotNull(requestModel);
        assertEquals(request.getId(), requestModel.getId());
    }

    @Test
    void givenResponseFromUseCase_whenGetPerson_shouldReturnCorrectResponse() {
        GetPersonRequest request = new GetPersonRequest(1);
        doAnswer(this::answer).when(useCase).getPerson(any(), any());
        GetPersonResponse response = controller.getPerson(request);
        GetPersonResponseModel responseModel = getResponseModel();
        assertEquals(responseModel.getId(), response.getId());
        assertEquals(responseModel.getName(), response.getName());
        assertEquals(responseModel.getAge(), response.getAge());
        assertEquals(responseModel.getGender(), response.getGender());
        assertEquals(responseModel.getHobby(), response.getHobby());
    }

    private Void answer(InvocationOnMock invocation) {
        GetPersonOutputBoundary presenter = invocation.getArgument(1);
        GetPersonResponseModel responseModel = getResponseModel();
        presenter.present(responseModel);
        return null;
    }

    private GetPersonResponseModel getResponseModel() {
        return GetPersonResponseModel.builder().id(1).name("fadhlan").age(20).gender("Male").hobby("Playing").build();
    }

}
