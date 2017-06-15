import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import { Product } from './product';
import 'rxjs/add/operator/map';

@Injectable()
export class CartService{
    constructor(private http: Http) { }

  getProductsFromCart() {
    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});

    return this.http.get('http://localhost:9000/cart', options)
      .map(response => <Product[]>response.json());
  }


  deleteProductFromCart(product){ 
    const serializedForm = JSON.stringify(product);
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-Type', 'application/json');
        console.log(serializedForm);
        const options = new RequestOptions({headers: headers});
//        this.http.delete('http://localhost:9000/cart', serializedForm, options).subscribe(); 
}

}



