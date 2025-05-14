package co.simplon.devbookapi.beans;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import co.simplon.devbookapi.dtos.Notification;
import co.simplon.devbookapi.dtos.ProfileDetails;
import co.simplon.devbookapi.services.AccountService;


@Component
@EnableAsync
public class EmitterManager {
	

    public final AccountService accountService;
    
    public EmitterManager(AccountService accountService) {
		this.accountService = accountService;
	}

//    private final HashSet<SseEmitter> emitters = new HashSet<>();
    private final HashMap<SseEmitter, String> emittersMap = new HashMap<>();
    private final ExecutorService executors = Executors.newVirtualThreadPerTaskExecutor();
	
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(-1L);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        emittersMap.put(emitter, username);

        emitter.onCompletion(() -> emittersMap.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            emittersMap.remove(emitter);
        });
        emitter.onError((e) -> emittersMap.remove(emitter));
        return emitter;
    }
    
    @Async
	public void sendNotifications(String notifType) {
        Notification notification = new Notification("Notification triggered at " + System.currentTimeMillis());
        Set<String> accountsToSendNotification = new HashSet<>();
    	if("ARTICLE".equals(notifType)) {
    		List<String> accountList = accountService.getAccountUsernamesWithNotifArticle();
    		accountsToSendNotification.addAll(accountList);
    	}

    	if("RSS".equals(notifType)) {
    		List<String> accountList = accountService.getAccountUsernamesWithNotifRss();
    		accountsToSendNotification.addAll(accountList);
    	}
    	
        emittersMap.entrySet().stream().forEach(entry -> { 
        	String username = entry.getValue();
        	if(accountsToSendNotification.contains(username)) {
        		executors.execute(() -> {
        			try {
        				entry.getKey().send(
        					SseEmitter.event()
                				.name("notification")
                				.data(notification, MediaType.APPLICATION_JSON)
        				);
        			} catch (IOException e) {
        				entry.getKey().completeWithError(e);
        				emittersMap.remove(entry.getKey());
        			}
        		});
        	}
        }); 
	}

}
