import {CanActivateFn, Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {inject} from '@angular/core';

export const accountGuard: CanActivateFn = (route, state) => {
  console.log(inject(AuthService).isAuthenticated())
  if (!inject(AuthService).isAuthenticated()) {
    inject(Router).navigate(['/auth']);
    return false;
  }
  return true;
};

export const shareArticleGuard: CanActivateFn = (route, state) => {
  console.log(inject(AuthService).isAuthenticated())
  if(inject(AuthService).isAuthenticated() && inject(AuthService).isMember()){
    return true
  }
    inject(Router).navigate(['/']);
    return false;
};

