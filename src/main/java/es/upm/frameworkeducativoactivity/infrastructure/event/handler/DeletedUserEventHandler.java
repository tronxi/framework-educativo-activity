package es.upm.frameworkeducativoactivity.infrastructure.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteStudent;
import es.upm.frameworkeducativoactivity.infrastructure.event.model.DeletedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "user.deleted.activity")
public class DeletedUserEventHandler {

    private final ObjectMapper objectMapper;
    private final DeleteStudent deleteStudent;

    @RabbitHandler
    public void deletedUser(String message) {
        try {
            DeletedUser user = objectMapper.readValue(message, DeletedUser.class);
            deleteStudent.deleteStudentById(user.getId_user());
        } catch(IOException e) {
            throw new RuntimeException("Error parsing object");
        }
    }
}
