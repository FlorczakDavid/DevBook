import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConfirmEmailService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/accounts/confirm/';

  confirm(token: string): Observable<string> {
    return this.http.get(this.baseUrl + token, { responseType: 'text' });
  }
}
