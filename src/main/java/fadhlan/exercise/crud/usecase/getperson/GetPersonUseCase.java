package fadhlan.exercise.crud.usecase.getperson;

import fadhlan.exercise.crud.entity.PersonReadModel;
import fadhlan.exercise.crud.exception.InvalidRequestException;
import fadhlan.exercise.crud.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetPersonUseCase implements GetPersonInputBoundary {

    private final PersonGateway personGateway;

    @Override
    public void getPerson(GetPersonRequestModel requestModel, GetPersonOutputBoundary presenter) {
        validateRequest(requestModel);
        getAndPresentPerson(requestModel, presenter);
    }

    private void getAndPresentPerson(GetPersonRequestModel requestModel, GetPersonOutputBoundary presenter) {
        Optional<PersonReadModel> optionalPerson = personGateway.getPerson(requestModel.getId());
        if (optionalPerson.isPresent()) {
            presentFoundPerson(optionalPerson.get(), presenter);
        } else {
            presentPersonNotFound(presenter);
        }
    }

    private void presentPersonNotFound(GetPersonOutputBoundary presenter) {
        presenter.present(null);
    }

    private void presentFoundPerson(PersonReadModel personReadModel, GetPersonOutputBoundary presenter) {
        GetPersonResponseModel responseModel = constructResponseModel(personReadModel);
        presenter.present(responseModel);
    }

    private GetPersonResponseModel constructResponseModel(PersonReadModel personReadModel) {
        return GetPersonResponseModel.builder().id(personReadModel.getId()).name(personReadModel.getName())
                .gender(personReadModel.getGender()).age(personReadModel.getAge()).hobby(personReadModel.getHobby())
                .build();
    }

    private void validateRequest(GetPersonRequestModel requestModel) {
        if (requestModel == null) {
            throw new InvalidRequestException("request model cannot be null");
        }
        if (requestModel.getId() == null) {
            throw new InvalidRequestException("Id cannot be null");
        }
    }
}
