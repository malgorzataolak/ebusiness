import { Component, OnInit, Input } from '@angular/core';
import { Router,
         NavigationExtras } from '@angular/router';
import { Observable }           from 'rxjs/Observable';
import { LoginService } from './login/login.service';
import {ActivatedRoute} from "@angular/router";







@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

private token: number;
profile: any;
profileSaved: boolean = false;

@Input()
 authConfig: any;

public authenticated;

  title = 'Zapraszamy Cię do zakupu naszych tajemniczych pudełek!';
  constructor(public loginService: LoginService, private route: ActivatedRoute) { }
  ngOnInit() {
  var res = this.loginService.handleAuthentication();

  this.authenticated=this.loginService.isAuthenticated();
  console.log(this.authenticated);

  }

  isAuthenticated() {
  return this.loginService.isAuthenticated();
}

hasToken() {
  if(localStorage.getItem('access_token')){
    return true;
  } else {
    return false;
  }
}

login() {
  return this.loginService.auth();
  
}

logout() {
  console.log("logout called");
  this.profileSaved = false;
  console.log("profileSaved in logout=" + this.profileSaved);
  return this.loginService.logout();
}

getUserInfo() {
  console.log("getUserInfo()");
  if (this.loginService.userProfile) {
    console.log("getUserInfo(): userProfile filled");
    this.profile = this.loginService.userProfile;
  } else {
    console.log("getUserInfo(): gettingProfile");
    this.loginService.getProfile((err, profile) => {
      this.profile = profile;
      console.log(profile);
      //"google-oauth2|111945265065266018866"
    });
    }
  }

isSuccess() {
  var res = this.loginService.success;
  console.log("isSuccess: " + res);
  if(res && !this.profileSaved){
    console.log("isSuccess: gettingUserInfo");
    this.getUserInfo();
    this.profileSaved = true;
  }
  console.log("Profilesaved=" + this.profileSaved);
  return res;
}

}
