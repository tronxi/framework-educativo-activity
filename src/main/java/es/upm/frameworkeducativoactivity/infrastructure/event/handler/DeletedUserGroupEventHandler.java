package es.upm.frameworkeducativoactivity.infrastructure.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteStudent;
import es.upm.frameworkeducativoactivity.infrastructure.event.model.DeletedUserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "userGroup.deleted.activity")
public class DeletedUserGroupEventHandler {

    private final ObjectMapper objectMapper;
    private final DeleteStudent deleteStudent;

    @RabbitHandler
    public void deleteUserGroup(String message) {

        try {
            DeletedUserGroup deletedUserGroup = objectMapper.readValue(message, DeletedUserGroup.class);
            deleteStudent.deleteStudentByIdAndGroupId(deletedUserGroup.getStudentId(), deletedUserGroup.getGroupId());
        } catch (IOException e) {
            throw new RuntimeException("Error parsing object");
        }

    }
}
