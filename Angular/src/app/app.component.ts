import { Component, OnInit } from '@angular/core';
import { LoginService } from './login/login.service';
import {ActivatedRoute} from "@angular/router";







@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

public authenticated;

  title = 'Zapraszamy Cię do zakupu naszych tajemniczych pudełek!';
  constructor(private loginService: LoginService, private route: ActivatedRoute) { }
  ngOnInit() {

  this.authenticated=this.loginService.isAuthenticated();
  console.log(this.authenticated);

  }
 
}
