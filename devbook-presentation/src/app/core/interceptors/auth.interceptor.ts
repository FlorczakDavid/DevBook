import {inject, Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from '../auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  // private token = localStorage.getItem('token'); // Récupère le token depuis le localStorage
 // private token=inject(AuthService).getAuthInfo()?.token; // Récupère le token depuis le localStorage

  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.authService.getAuthInfo()?.token;
    console.log('Intercepteur token:', token);

    if (token) {
      console.log('Requête clonée avec le token:', req);
      const headers = new HttpHeaders({Authorization: `Bearer ${ token}`});
      const clonedReq = req.clone({headers})

      return next.handle(clonedReq);
    }
    return next.handle(req);
  }
}
