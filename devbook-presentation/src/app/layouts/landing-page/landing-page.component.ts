import { Component } from '@angular/core';
import { IntegratorHomeComponent } from '../integrator-home/integrator-home.component';
import { MemberHomeComponent } from '../member-home/member-home.component';

@Component({
  selector: 'app-landing-page',
  imports: [
    IntegratorHomeComponent,
    MemberHomeComponent
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {

}
