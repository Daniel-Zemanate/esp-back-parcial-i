package com.dh.serieservice.api.queue;

import com.dh.serieservice.model.Serie;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerieSender {

    private final RabbitTemplate rabbitTemplate;

    private final Queue catalogQueue;

    public void send(Serie serie) {
        this.rabbitTemplate.convertAndSend(this.catalogQueue.getName(), serie);
    }
}
