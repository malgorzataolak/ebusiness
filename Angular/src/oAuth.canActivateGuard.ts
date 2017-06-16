import { Injectable } from '@angular/core';
import { CanActivate , Router} from '@angular/router';
import { LoginService } from './login/login.service';

@Injectable()
export class CanActivateViaOAuthGuard implements CanActivate {

  constructor(private loginService: LoginService, public router : Router) {}
  canActivate() {
    var isAuthenticated = this.loginService.isAuthenticated();
    if(!isAuthenticated) {
      this.router.navigateByUrl('/login');
    }
    return isAuthenticated;
  }
}
