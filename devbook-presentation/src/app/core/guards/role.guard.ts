import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
 const authService = inject(AuthService)
  const authInfo = authService.getAuthInfo(); // Méthode pour récupérer les infos d'authentification
  if (authInfo && authInfo.role === 'INTEGRATOR') {
    return true; // Autorise l'accès si le rôle est INTEGRATOR
  }
  return false;
};
