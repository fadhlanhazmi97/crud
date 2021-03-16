package fadhlan.exercise.crud.controller;

import fadhlan.exercise.crud.controller.presenter.GetPersonPresenter;
import fadhlan.exercise.crud.controller.response.GetPersonResponse;
import fadhlan.exercise.crud.controller.request.GetPersonRequest;
import fadhlan.exercise.crud.usecase.getperson.GetPersonInputBoundary;
import fadhlan.exercise.crud.usecase.getperson.GetPersonRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPersonController {

    private final GetPersonInputBoundary useCase;

    @GetMapping(path = "/getPerson")
    public GetPersonResponse getPerson(GetPersonRequest request) {
        GetPersonRequestModel requestModel = getRequestModel(request);
        GetPersonPresenter presenter = new GetPersonPresenter();
        useCase.getPerson(requestModel, presenter);
        return presenter.getResponse();
    }

    private GetPersonRequestModel getRequestModel(GetPersonRequest request) {
        return new GetPersonRequestModel(request.getId());
    }
}
