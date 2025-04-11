package co.simplon.devbookapi.beans;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import co.simplon.devbookapi.dtos.Notification;


@Component
@EnableAsync
public class EmitterManager {

    private final HashSet<SseEmitter> emitters = new HashSet<>();
    private final ExecutorService executors = Executors.newVirtualThreadPerTaskExecutor();
	
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(-1L);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            emitters.remove(emitter);
        });
        emitter.onError((e) -> emitters.remove(emitter));
        return emitter;
    }
    
    @Async
	public void sendNotifications() {
        Notification notification = new Notification("Notification triggered at " + System.currentTimeMillis());
        for (SseEmitter emitter : emitters) {
        	executors.execute(() -> {
        		try {
        			emitter.send(
        				SseEmitter.event()
                			.name("notification")
                			.data(notification, MediaType.APPLICATION_JSON)
        			);
        		} catch (IOException e) {
        			emitter.completeWithError(e);
        			emitters.remove(emitter);
        		}
        	});
        }
	}

}
