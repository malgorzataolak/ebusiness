import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';
import { Order } from "./order";

@Injectable()
export class OrderService {

  constructor(private http: Http) { }

sendDataToPlay(formData, totalPrice, cartCount, productList) {
	var newOrder=new Order();
	newOrder.id=0;
	newOrder.productsList=productList;
	newOrder.userID=localStorage.getItem('userID');
	newOrder.totalPrice=totalPrice;
	newOrder.paymentType=formData.payment;
	newOrder.shipmentType=formData.delivery;
	newOrder.address=formData.firstName+" "+formData.lastName+" "+formData.email+" "+formData.address+" "+formData.phone;
	var currentDate= new Date();
	newOrder.date=currentDate.toDateString();
    const serializedForm = JSON.stringify(newOrder);
    console.log(serializedForm);

    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');

    const options = new RequestOptions({headers: headers});

    this.http.post('http://localhost:9000/createorder', serializedForm, options).subscribe();
  }

}
