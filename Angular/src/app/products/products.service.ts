import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import {Product} from "./product";
import { CartProduct } from "./cartProduct";
import { LoginService } from "../login/login.service";
import 'rxjs/add/operator/map';

@Injectable()
export class ProductsService {
  
  constructor(private loginService: LoginService, private http: Http) { }

  getProducts() {
    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});
    console.log("jestem gotowy do pobierania");

    return this.http.get('http://localhost:9000/', options)
      .map(response => <Product[]>response.json());
  }

  getCategories(){
      
        const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});
    console.log("jestem gotowy do pobierania");

    return this.http.get('http://localhost:9000/getcategories', options)
      .map(response => <Product[]>response.json());

  }

  getProductsByCategory(id){
          const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});
    console.log("jestem gotowy do pobierania");

    return this.http.get('http://localhost:9000/categoryproducts/'+id, options)
      .map(response => <Product[]>response.json());
  }

profile:any;

  addProductToCart(product){ 
        this.profile=this.loginService.userProfile;
        var cartProduct=new CartProduct();
        cartProduct.id=0;
        cartProduct.productID=product.ID;
        cartProduct.userID=this.profile.sub;
        console.log(this.profile.sub);
        cartProduct.productName=product.name;
        cartProduct.productPrice=product.price;
         const serializedForm = JSON.stringify(cartProduct);
         console.log(serializedForm);
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-Type', 'application/json');
        
        const options = new RequestOptions({headers: headers});

    //this.http.post('http://localhost:9000/addcartproduct', serializedForm, options).subscribe(); 
}

}
