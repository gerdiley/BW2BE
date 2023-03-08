import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
    if(!localStorage.getItem("user")) {
      this.router.navigate(["/login"])
    }
  }
  title = 'epicEnergyService';

  constructor(private router: Router) {}

  logout() {
    localStorage.removeItem("user");
    this.router.navigate(['/login']);
  }
}
