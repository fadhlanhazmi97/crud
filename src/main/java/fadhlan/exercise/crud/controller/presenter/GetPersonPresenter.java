package fadhlan.exercise.crud.controller.presenter;

import fadhlan.exercise.crud.controller.response.GetPersonResponse;
import fadhlan.exercise.crud.usecase.getperson.GetPersonOutputBoundary;
import fadhlan.exercise.crud.usecase.getperson.GetPersonResponseModel;
import lombok.Getter;

@Getter
public class GetPersonPresenter implements GetPersonOutputBoundary {

    private GetPersonResponse response;

    @Override
    public void present(GetPersonResponseModel responseModel) {
        this.response = GetPersonResponse.builder().id(responseModel.getId()).name(responseModel.getName())
                .gender(responseModel.getGender()).age(responseModel.getAge()).hobby(responseModel.getHobby()).build();
    }
}
