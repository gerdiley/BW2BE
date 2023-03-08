import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from '../model/cliente';
import { Fattura } from '../model/fattura';
import { User } from '../model/user';
import { Userc } from '../model/userc';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user: Userc | undefined
  clienti: Cliente[] = [];
  fatture: Fattura[] = [];

  constructor(private http: HttpClient) { }

  login(user: {}) {
    return this.http.post<User>("http://localhost:8081/api/login", user);
  }

  getData() {
    return this.http.get<Cliente[]>("http://localhost:8081/clienti/sort/name");
  }

  getFatture() {
    return this.http.get<Fattura[]>("http://localhost:8081/fatture/all");
  }

  getFattureByCliente(name:string) {
    return this.http.get<Fattura[]>("http://localhost:8081/fatture/cliente?nome="+ name);
  }

  getFattureByStato(stato:string) {
    return this.http.get<Fattura[]>("http://localhost:8081/fatture/stato?stato="+ stato);
  }

  getFattureByImporto(min: number, max:number) {
    return this.http.get<Fattura[]>("http://localhost:8081/fatture/importo?min=" + min + "&max=" + max);
  }

  getFattureByAnno(anno: number) {
    return this.http.get<Fattura[]>("http://localhost:8081/fatture/anno?anno=" + anno);
  }

  getOrderByName() {
    return this.http.get<Cliente[]>("http://localhost:8081/clienti/sort/name");
  }

  getOrderByFatturato() {
    return this.http.get<Cliente[]>("http://localhost:8081/clienti/sort/fatturato");
  }

  getOrderByData() {
    return this.http.get<Cliente[]>("http://localhost:8081/clienti/sort/date");
  }

  getOrderByUltimoContatto() {
    return this.http.get<Cliente[]>("http://localhost:8081/clienti/sort/lastdate");
  }

  getOrderByProvincia() {
    return this.http.get<Cliente[]>("http://localhost:8081/clienti/sort/provincia");
  }

  createUser(token: string, type: string, id: number, username: string, email: string, roles: string[], expirationTime: Date) {
    this.user = new Userc(token, type, id, username, email, roles, expirationTime);
  }
}
