import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Fattura } from '../model/fattura';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-fatture',
  templateUrl: './fatture.component.html',
  styleUrls: ['./fatture.component.scss']
})
export class FattureComponent implements OnInit {

  fatture: Fattura[] = [];
  stato!: string;
  min!: number;
  max!: number
  anno!: number

  constructor(private authSrv: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.getFatture();
  }

  getFatture() {
    this.authSrv.getFatture().subscribe(data => {
      console.log(data);
      this.fatture = data;
    })
  }

  getByStatoPagata() {
    this.authSrv.getFattureByStato("PAGATA").subscribe(data => {
      console.log(data);
      this.fatture = data;
    })
  }

  getByStatoNonPagata() {
    this.authSrv.getFattureByStato("NON PAGATA").subscribe(data => {
      console.log(data);
      this.fatture = data;
    })
  }

  getByImporto(min: number, max: number) {
    this.authSrv.getFattureByImporto(min, max).subscribe(data => {
      console.log(data);
      this.fatture = data;
    })
  }

  getByAnno(anno: number) {
    this.authSrv.getFattureByAnno(anno).subscribe(data => {
      console.log(data);
      this.fatture = data;
    })
  }

  onSubmit(f: NgForm) {
    this.min = f.value.min;
    this.max = f.value.max
    this.getByImporto(this.min, this.max);
  }

  onSubmit2(f: NgForm) {
    this.anno = f.value.anno;
    this.getByAnno(this.anno);
  }


}
