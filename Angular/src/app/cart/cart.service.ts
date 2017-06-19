import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import { CartProduct } from '../products/cartProduct';
import 'rxjs/add/operator/map';

@Injectable()
export class CartService{


    constructor(private http: Http) { }
    cartProducts:CartProduct[];
  getProductsFromCart() {
    var userID=localStorage.getItem('userID');
    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});

    return this.http.get('http://localhost:9000/cart/'+userID, options)
      .map(response => <CartProduct[]>response.json());
  }


  deleteProductFromCart(productID){ 

   
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-Type', 'application/json');
        const options = new RequestOptions({headers: headers});
       this.http.delete('http://localhost:9000/cart/'+productID, options).subscribe(); 
}

}



