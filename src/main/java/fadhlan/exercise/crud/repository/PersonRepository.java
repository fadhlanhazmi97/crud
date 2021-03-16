package fadhlan.exercise.crud.repository;

import fadhlan.exercise.crud.entity.PersonReadModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonReadModel, Integer> {
}
