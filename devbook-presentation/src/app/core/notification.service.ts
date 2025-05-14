import { fetchEventSource, EventSourceMessage } from '@microsoft/fetch-event-source';

export class NotificationService {
    private static readonly notifications: string[] = [];
    private static readonly abortController: AbortController = new AbortController();

    static startListening() {
      fetchEventSource('http://localhost:8080/sse/subscribe', {
        method: 'GET',
        headers: {
          'Authorization':  `Bearer ${localStorage.getItem('token')}`
        },
        onmessage: (ev: EventSourceMessage) => {
          const gotJson = JSON.parse(ev.data);
          this.notifications.push(gotJson.message);
        },
        signal: this.abortController.signal,
      });
    }

    static stopListening() {
      this.abortController.abort();
    }

    static getNotifications(): string[] {
      return this.notifications;
    }
}
