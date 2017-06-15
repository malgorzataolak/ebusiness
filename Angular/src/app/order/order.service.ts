import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';


@Injectable()
export class OrderService {

  constructor(private http: Http) { }

sendDataToPlay(formData) {
    const serializedForm = JSON.stringify(formData);
    console.log(serializedForm);

    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});

    //this.http.post('http://localhost:9000/neworder', serializedForm, options).subscribe();
  }

}
