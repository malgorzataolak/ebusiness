import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';

import {MyFilterPipe} from './myfilter.pipe';


import { AppComponent } from './app.component';
import { RouterModule } from "@angular/router";
import { CartComponent } from "./cart/cart.component";
import { OrderComponent } from './order/order.component';
import { ProductsComponent} from './products/products.component';
import { SendOrderComponent } from './order/sendOrder.component';
import { ProductsService } from './products/products.service';
import { OrderService } from './order/order.service';
import { CartService } from './cart/cart.service';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login/login.service';




@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    OrderComponent,
    ProductsComponent,
    SendOrderComponent,
    LoginComponent,
    MyFilterPipe
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
      { path: 'login', component: LoginComponent},
      { path: 'callback', component: LoginComponent},
      ])
  ],
  providers: [ProductsService, OrderService, CartService, LoginService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
