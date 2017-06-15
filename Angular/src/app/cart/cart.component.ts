import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { CartService } from './cart.service';
import { ActivatedRoute } from "@angular/router";

const PRODUCTS: Product[]=[
    { nazwa: 'Naturalne piękno', opis:'Krem do rąk, odżywka do włosów, peeling do ciała', cena: 30, kategoria:'EKOKosmetyki' },
    { nazwa: 'Zadbana mama', opis:'Puder, krem do twarzy, krem do rąk, paleta cieni', cena:45, kategoria:'Dla Mamy'},
    { nazwa: 'Naczynkowy rytuał', opis:'Krem do cery naczynkowej na noc, krem na dzień, woda różana, płyn micelarny', cena:100, kategoria:'Dermokosmetyki'},
    { nazwa: 'Trądzik precz', opis: 'Korektor punktowy, krem złuszczający na noc, tonik, krem ochronny na dzień', cena: 98, kategoria: 'Dermokosmetyki'},
    { nazwa: 'Ekologiczne SPA', opis: 'Peeling do ciała, maseczka do twarzy, krem do ciała, sól do kąpieli', cena: 68, kategoria: 'EKOKosmetyki'},
    { nazwa: 'Nadziejka', opis:'Krem na rozstępy, krem nawilżający do ciała, krem do twarzy, woda termalna', cena:59, kategoria:'Dla Mamy'}
];



@Component({
  selector: 'cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})


export class CartComponent implements OnInit{
  title = 'Twoje produkty w koszyku';
  products=PRODUCTS;
  //products:Product[];
  constructor(private cartService: CartService, private route: ActivatedRoute){}
  ngOnInit() {
  //  this.productService.getProducts().subscribe(data => this.products = data);
  }

  public currentProduct;
  deleteProduct(product){
  this.currentProduct=product;
  console.log(this.currentProduct);
  this.cartService.deleteProductFromCart(this.currentProduct);}
}
