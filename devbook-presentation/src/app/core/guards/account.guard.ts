import {CanActivateFn, Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {inject} from '@angular/core';

export const accountGuard: CanActivateFn = (route, state) => {
  console.log(inject(AuthService).isAuthenticated())
  if (!inject(AuthService).isAuthenticated()) {
    inject(Router).navigate(['/']);
    return false;
  }
  return true;
};
