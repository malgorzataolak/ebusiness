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

  
  constructor(private cartService: CartService, private route: ActivatedRoute){}
  ngOnInit() {
    this.cartService.getProductsFromCart().subscribe(data => this.cartService.cartProducts = data);
    
  }


  deleteProduct(productID){
 
  
  this.cartService.deleteProductFromCart(productID);
  window.location.reload();}

}
