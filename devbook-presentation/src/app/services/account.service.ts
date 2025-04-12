import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface Account {
  id?: number;
  username: string;
  password: string;
}
@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private readonly apiUrl = 'http://localhost:8080/accounts';

  constructor(private http: HttpClient) {}

  createAccount(accountData: Account): Observable<Account> {
    return this.http.post<Account>(this.apiUrl, accountData);
  }
}
