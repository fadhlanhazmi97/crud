package fadhlan.exercise.crud.gateway;

import fadhlan.exercise.crud.entity.PersonReadModel;

import java.util.Optional;

public interface PersonGateway {
    Optional<PersonReadModel> getPerson(Integer id);
}
