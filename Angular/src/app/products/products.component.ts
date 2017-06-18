import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { ProductsService } from './products.service';
import { LoginService } from '../login/login.service';
import { ActivatedRoute } from "@angular/router";
import { Categories } from  './categories';



@Component({
  selector: 'products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})


export class ProductsComponent implements OnInit{
products:Product[];
categories:Categories[];
constructor(private loginService: LoginService, private productsService: ProductsService, private route: ActivatedRoute){}

ngOnInit() {
    this.productsService.getProducts().subscribe(data => this.products = data);
    this.productsService.getCategories().subscribe(data=>this.categories=data);
    this.authenticated=this.loginService.isAuthenticated();
  }

  selectByCategory(id){
      this.productsService.getProductsByCategory(id).subscribe(data=>this.products=data);
  }
  public authenticated;
  //products=PRODUCTS; //rozwiazanie tymczasowe
  //selectedCategory:number=1;
  

  public currentProduct;
   
  addToCart(product){ 
  this.currentProduct=product;
  this.productsService.addProductToCart(this.currentProduct); }
}
