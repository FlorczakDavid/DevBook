import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly router = inject(Router);
  constructor() { }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  isMember(): boolean {
    const role = localStorage.getItem('role');
    console.log('role', role)
    return role === "MEMBER"
  }

  getAuthInfo(): any | null {
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role'); // Stockez le rôle dans le localStorage
    return token && role ? { token, role } : null;
  }

}
