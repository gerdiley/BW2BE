import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from '../model/cliente';
import { Fattura } from '../model/fattura';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  cliente: Cliente[] = [];
  fatture: Fattura[] = [];

  constructor(private authSrv: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.getData();
  }

  getData() {
    this.authSrv.getData().subscribe(data => {
      console.log(data);
      this.cliente = data

    })
  }

  getFatture(nome: string) {
    this.authSrv.getFattureByCliente(nome).subscribe(data => {
      console.log(data);
      this.fatture = data;
    })
  }

  orderByName() {
    this.authSrv.getOrderByName().subscribe(data => {
      console.log(data);
      this.cliente = data;
    })
  }

  orderByFatturato() {
    this.authSrv.getOrderByFatturato().subscribe(data => {
      console.log(data);
      this.cliente = data;
    })
  }

  orderByDate() {
    this.authSrv.getOrderByData().subscribe(data => {
      console.log(data);
      this.cliente = data;
    })
  }

  orderByDateLast() {
    this.authSrv.getOrderByUltimoContatto().subscribe(data => {
      console.log(data);
      this.cliente = data;
    })
  }

  orderByProvincia() {
    this.authSrv.getOrderByProvincia().subscribe(data => {
      console.log(data);
      this.cliente = data;
    })
  }

}
