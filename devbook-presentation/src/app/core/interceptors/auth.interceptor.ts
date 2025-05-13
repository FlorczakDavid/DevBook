import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private token = localStorage.getItem('token'); // Récupère le token depuis le localStorage

  constructor() {}

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    console.log('Intercepteur token:', this.token);

    if ( this.token) {
      console.log('Requête clonée avec le token:', req);
      const headers = new HttpHeaders({Authorization: `Bearer ${ this.token}`});
      const clonedReq = req.clone({headers})

      return next.handle(clonedReq);
    }
    return next.handle(req);
  }
}
