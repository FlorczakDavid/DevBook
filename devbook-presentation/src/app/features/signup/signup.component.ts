import { AccountService } from './../../services/account.service';
import { Component } from '@angular/core';
import { FormComponent } from '../../shared/form/form.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  imports: [FormComponent, HttpClientModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  signup: FormGroup;

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private http: HttpClient
  ) {
    this.signup = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  onSubmit() {
    // if (this.signup.valid) {
    //   const FormData = {
    //     username: this.signup.value.username,
    //     password: this.signup.value.password,
    //   };
    //   console.log('Form Data:', FormData);
    //   this.accountService.createAccount(FormData).subscribe((response) => {
    //     console.log('Account created successfully!', response);
    //     console.log('Form Submitted!', this.signup.value);
    //   });
    // }
    if (this.signup.valid) {
      console.log('valided form');
      const FormData = {
        username: this.signup.value.username,
        password: this.signup.value.password,
      };
      console.log(FormData);
      this.http.post('http://localhost:8080/accounts', FormData).subscribe({
        next: (response) => {
          console.log('La demande est encoyé', response);
        },
        error: (error) => {
          console.error('Erreur d envoie', error);
        },
      });
    }
  }
}
