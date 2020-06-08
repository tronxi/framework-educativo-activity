package es.upm.frameworkeducativoactivity.infrastructure.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.upm.frameworkeducativoactivity.domain.port.primary.DeleteGroup;
import es.upm.frameworkeducativoactivity.infrastructure.event.model.DeletedGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "group.deleted.activity")
public class DeletedGroupEventHandler {

    private final ObjectMapper objectMapper;
    private final DeleteGroup deleteGroup;

    @RabbitHandler
    public void deletedGroup(String message) {
        try {
            DeletedGroup deletedGroup  = objectMapper.readValue(message, DeletedGroup.class);
            deleteGroup.deleteGroupById(deletedGroup.getId_group());
        } catch (IOException e) {
            throw new RuntimeException("Error parsing object");
        }
    }
}
