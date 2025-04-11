import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layouts/header/header.component';
import { SignupComponent } from './features/signup/signup.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, SignupComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'devbook-presentation';
}
