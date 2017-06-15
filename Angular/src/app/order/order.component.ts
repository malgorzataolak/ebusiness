import { Component, OnInit } from '@angular/core';
import { OrderService } from './order.service';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import {ActivatedRoute} from "@angular/router";




@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})

export class OrderComponent implements OnInit {
  title = 'Formularz zakupu oraz dostawy';

  dataForm : FormGroup; 

  constructor(private orderService: OrderService, private route: ActivatedRoute) { }
  ngOnInit(){
        this.dataForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      adress: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      zipCode: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required)
      });
}

makeOrder(event){
    this.orderService.sendDataToPlay(this.dataForm.value);
}

}
