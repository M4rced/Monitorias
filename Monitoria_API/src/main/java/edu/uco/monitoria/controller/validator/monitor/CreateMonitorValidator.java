package edu.uco.monitoria.controller.validator.monitor;

import edu.uco.monitoria.controller.validator.Validator;
import edu.uco.monitoria.crosscuting.helper.UUIDHelper;
import edu.uco.monitoria.crosscuting.messages.enumeration.Message;
import edu.uco.monitoria.domain.MonitorDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateMonitorValidator implements Validator<MonitorDTO> {
	@Override
    public List<Message> validate(MonitorDTO dto) {
        List<Message> messages = new ArrayList<>();

        validateMonitorId(dto.getId(), messages);

        return messages;
    }

    private void validateMonitorId(UUID monitorId, List<Message> messages) {

        if(UUIDHelper.isDefaultUUID(monitorId)) {
            messages.add(Message.createErrorMessage("place id is equal to default value"));
        }

    }
}
