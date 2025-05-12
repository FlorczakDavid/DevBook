import { Component, OnInit } from '@angular/core';
import { IntegratorHomeComponent } from './pages/integrator-home/integrator-home.component';
import { MemberHomeComponent } from './pages/member-home/member-home.component';
import { AnonymousHomeComponent } from "./pages/anonymous-home/anonymous-home.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-landing-page',
  imports: [
    IntegratorHomeComponent,
    MemberHomeComponent,
    AnonymousHomeComponent,
    CommonModule
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent implements OnInit {

  roleInLocalStorage = "";

  ngOnInit(): void {

    this.roleInLocalStorage = localStorage.getItem('role') ?? 'anonyme';
    console.log(this.roleInLocalStorage);

  }

  checkRole(role: string) {
    return this.roleInLocalStorage == role;
  }

}
