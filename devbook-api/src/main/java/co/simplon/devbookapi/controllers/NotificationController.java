package co.simplon.devbookapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import co.simplon.devbookapi.services.NotificationService;

@RestController
@RequestMapping("/sse")
public class NotificationController {
	private final NotificationService notificationService;
	
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

    @GetMapping("/subscribe")
    public SseEmitter streamSse() {
    	return notificationService.subscribe();
    }
    
    @PostMapping("/notify/rss")
    public ResponseEntity<Void> notifyClientsRss() {
    	notificationService.sendNotif("RSS");
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/notify/article")
    public ResponseEntity<Void> notifyClientsArticle() {
    	notificationService.sendNotif("ARTICLE");
        return ResponseEntity.ok().build();
    }
}