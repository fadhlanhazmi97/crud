package fadhlan.exercise.crud.gateway;

import fadhlan.exercise.crud.gateway.impl.PersonGatewayImpl;
import fadhlan.exercise.crud.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PersonGatewayTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonGatewayImpl gateway;

    @Captor
    private ArgumentCaptor<Integer> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenRequest_whenGetPerson_shouldCallRepositoryWithCorrectParam() {
        int id = 1;
        gateway.getPerson(id);
        verify(repository, times(1)).findById(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals(id, captor.getValue());
    }
}
