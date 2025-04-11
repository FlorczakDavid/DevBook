import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-auth-pin',
  imports: [ReactiveFormsModule, HttpClientModule],
  templateUrl: './auth-pin.component.html',
  styleUrl: './auth-pin.component.css'
})
export class AuthPinComponent{

  pinForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient){
    this.pinForm=this.fb.group({
      pin1 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin2 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin3 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin4 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))]
    })
  }

  onSubmit(){
    if(this.pinForm.valid){
      const formData = this.pinForm.value;
      this.http.post('http://localhost:8080/account/creer-compte', formData).subscribe({
        next: (response)=> {
          console.log('La demande est encoyé', response);
        } ,
        error : (error) => {
          console.error('Erreur d envoie', error);
        }
      })
    }
  };

  
}
