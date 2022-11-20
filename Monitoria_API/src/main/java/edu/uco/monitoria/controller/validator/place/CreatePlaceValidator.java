package edu.uco.monitoria.controller.validator.place;

import edu.uco.monitoria.controller.validator.Validator;
import edu.uco.monitoria.crosscuting.helper.UUIDHelper;
import edu.uco.monitoria.crosscuting.messages.enumeration.Message;
import edu.uco.monitoria.domain.PlaceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreatePlaceValidator implements Validator<PlaceDTO>{
	@Override
    public List<Message> validate(PlaceDTO dto) {
        List<Message> messages = new ArrayList<>();

        validatePlaceId(dto.getId(), messages);

        return messages;
    }

    private void validatePlaceId(UUID placeId, List<Message> messages) {

        if(UUIDHelper.isDefaultUUID(placeId)) {
            messages.add(Message.createErrorMessage("place id is equal to default value"));
        }

    }
}
