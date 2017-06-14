import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import {Product} from "./product";
import 'rxjs/add/operator/map';

@Injectable()
export class ProductsService {

  constructor(private http: Http) { }

  getProducts() {
    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});

    return this.http.get('http://localhost:9000/', options)
      .map(response => <Product[]>response.json());
  }

  sendProductToCart(product){
        const serializedForm = JSON.stringify(product);
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-Type', 'application/json');
        
        const options = new RequestOptions({headers: headers});

    this.http.post('http://localhost:9000/newproduct', serializedForm, options)
      .subscribe();
}
}
