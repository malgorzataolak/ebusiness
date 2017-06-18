import { Component, OnInit } from '@angular/core';
import { CartProduct } from '../products/cartProduct';
import { CartService } from './cart.service';
import { ActivatedRoute } from "@angular/router";




@Component({
  selector: 'cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})


export class CartComponent implements OnInit{
  title = 'Twoje produkty w koszyku';

  cartProducts:CartProduct[];
  constructor(private cartService: CartService, private route: ActivatedRoute){}
  ngOnInit() {
    this.cartService.getProductsFromCart().subscribe(data => this.cartProducts = data);
    console.log(this.cartProducts);
  }


  deleteProduct(productID){
 
  
  this.cartService.deleteProductFromCart(productID);
  window.location.reload();}
}
