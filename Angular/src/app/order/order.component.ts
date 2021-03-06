import { Component, OnInit } from '@angular/core';
import { OrderService } from './order.service';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import { LoginService } from '../login/login.service';
import {CartService } from "../cart/cart.service";





@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})

export class OrderComponent implements OnInit {
  title = 'Formularz zakupu oraz dostawy';

  dataForm : FormGroup; 

  public deliveryItems=[
    {value:'osobisty', display: 'Odbiór osobisty'},
    {value: 'kurier', display: 'Kurier DPD'},
    {value: 'paczkomat', display: 'Paczkomaty Inpost'},
    {value: 'poczta', display: 'Poczta Polska'}
  ]

  public paymentItems=[
    {value: 'przelew', display: 'Przedpłata przelewem'},
    {value: 'pobranie', display: 'Płatność za pobraniem'},
    {value: 'karta', display: 'Kartą kredytową'},
    {value: 'payu', display: 'Płatność w systemie PayU'},
    {value: 'paypal', display: 'Platnosc metodą PayPal'}
  ]

  constructor(private orderService: OrderService, private cartService: CartService, private loginService: LoginService, private route: ActivatedRoute) { }
  totalPrice=0;
  productCount=0;

  ngOnInit(){

      
      if(this.cartService.cartProducts){
      this.productCount=this.cartService.cartProducts.length;

      

      for(var i in this.cartService.cartProducts){
          this.totalPrice+=this.cartService.cartProducts[i].productPrice;

      }
      }

        this.dataForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
      delivery: new FormControl('', Validators.required),
      payment: new FormControl('', Validators.required)
      });

      //console.log(this.dataForm);


}



makeOrder(event){
    var products="";
     for(var i in this.cartService.cartProducts){
          products+=this.cartService.cartProducts[i].productName;
          products+=" ";

      }
       
    this.orderService.sendDataToPlay(this.dataForm.value, this.totalPrice, this.productCount, products);
    
}



}
