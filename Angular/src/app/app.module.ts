import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { RouterModule } from "@angular/router";
import { CartComponent } from "./cart/cart.component";
import { OrderComponent } from './order/order.component';
import { ProductsComponent} from './products/products.component';
import { SendOrderComponent } from './order/sendOrder.component';
import { ProductsService } from './products/products.service';
import { OrderService } from './order/order.service';
import { CartService } from './cart/cart.service';
import { LoginService } from './login/login.service';





@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    OrderComponent,
    ProductsComponent,
    SendOrderComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: '',   redirectTo: '/products', pathMatch: 'full' },
      { path: 'products', component: ProductsComponent},
      { path: 'cart', component: CartComponent},
      { path: 'order', component: OrderComponent},
      { path: 'sendOrder', component: SendOrderComponent },
      ])
  ],
  providers: [ProductsService, OrderService, CartService, LoginService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
