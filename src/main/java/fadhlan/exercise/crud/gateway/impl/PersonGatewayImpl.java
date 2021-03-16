package fadhlan.exercise.crud.gateway.impl;

import fadhlan.exercise.crud.entity.PersonReadModel;
import fadhlan.exercise.crud.gateway.PersonGateway;
import fadhlan.exercise.crud.repository.PersonRepository;

import java.util.Optional;

public class PersonGatewayImpl implements PersonGateway {

    private PersonRepository personRepository;

    @Override
    public Optional<PersonReadModel> getPerson(Integer id) {
        return personRepository.findById(id);
    }
}
