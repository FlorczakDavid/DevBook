import {HttpHandlerFn, HttpInterceptorFn, HttpRequest} from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
    const token = localStorage.getItem('token'); // Récupère le token depuis le localStorage
    console.log('Intercepteur token:', token);
    if (token) {
        // Clone la requête et ajoute l'en-tête Authorization
        const clonedReq = req.clone({
            headers: req.headers.set('Authorization', `Bearer ${token}`),
        });

        return next(clonedReq);
    }

    // Si aucun token, passe la requête sans modification
    return next(req);
};
