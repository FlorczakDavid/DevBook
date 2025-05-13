import { Component } from '@angular/core';
import { fetchEventSource, EventSourceMessage } from '@microsoft/fetch-event-source';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-notification',
  imports: [CommonModule],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent {
  notifications: string[] = [];
  abortController: AbortController = new AbortController()

  ngOnInit() {
    if(localStorage.getItem('role') === 'MEMBER') {
      this.startListening();
    }
  }

  startListening() {
    fetchEventSource('http://localhost:8080/sse/subscribe', {
      method: 'GET',
      headers: {
        'Authorization':  `Bearer ${localStorage.getItem('token')}`
      },
      onmessage: (ev: EventSourceMessage) => {
        console.log(ev);
        const gotJson = JSON.parse(ev.data);
        console.log(gotJson);
        this.notifications.push(gotJson.message);
        console.log(this.notifications);
      },
      signal: this.abortController.signal,
    });
  }

  ngOnDestroy() {
    this.abortController.abort();
  }
}
