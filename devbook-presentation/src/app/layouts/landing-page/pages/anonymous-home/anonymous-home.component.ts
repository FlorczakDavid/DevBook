import { Component } from '@angular/core';
import { SignInComponent } from "../../../../features/sign-in/sign-in.component";
import { SignupComponent } from "../../../../features/signup/signup.component";

@Component({
  selector: 'app-anonymous-home',
  imports: [SignInComponent, SignupComponent],
  templateUrl: './anonymous-home.component.html',
  styleUrl: './anonymous-home.component.css'
})
export class AnonymousHomeComponent {

}
