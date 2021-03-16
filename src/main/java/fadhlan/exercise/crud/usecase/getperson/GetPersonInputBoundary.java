package fadhlan.exercise.crud.usecase.getperson;

public interface GetPersonInputBoundary {
    void getPerson(GetPersonRequestModel requestModel, GetPersonOutputBoundary presenter);
}
