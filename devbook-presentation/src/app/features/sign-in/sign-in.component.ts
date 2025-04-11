import { Component } from '@angular/core';
import { FormComponent } from '../../shared/form/form.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-sign-in',
  imports: [FormComponent, HttpClientModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {
  signinForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.signinForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.signinForm.valid) {
      console.log("valided form")
      const formData = this.signinForm.value;
      console.log(formData);
      this.http.post('http://localhost:8080/accounts/authenticate', formData).subscribe({
        next: (response)=> {
          console.log('La demande est encoyé', response);
        } ,
        error : (error) => {
          console.error('Erreur d envoie', error);
        }
      })
    }
  }

}
