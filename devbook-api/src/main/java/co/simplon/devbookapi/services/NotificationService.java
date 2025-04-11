package co.simplon.devbookapi.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import co.simplon.devbookapi.beans.EmitterManager;

@Service
public class NotificationService {
	
	private final EmitterManager notifBean;
	
	public NotificationService(EmitterManager notifBean) {
		this.notifBean = notifBean;
	}

	public SseEmitter subscribe() {
		return notifBean.subscribe();
	}

	public void sendNotif() {
		notifBean.sendNotifications();
	}
	
}
