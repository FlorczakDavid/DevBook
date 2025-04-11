import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-auth-pin',
  imports: [ReactiveFormsModule, HttpClientModule],
  templateUrl: './auth-pin.component.html',
  styleUrl: './auth-pin.component.css'
})
export class AuthPinComponent{

  pinForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private route: ActivatedRoute){
    this.pinForm=this.fb.group({
      pin1 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin2 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin3 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin4 : ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))]
    })
  }

  onSubmit(){
    if(this.pinForm.valid){
      const formData = this.pinForm.get('pin1')?.value + this.pinForm.get('pin2')?.value + this.pinForm.get('pin3')?.value + this.pinForm.get('pin4')?.value;
      console.log(formData);
      this.route.paramMap.subscribe(params =>{
        const token = params.get('token');
        console.log(token);
        if(token){
          this.http.post(`http://localhost:8080/accounts/doubleAuth/${token}`, formData).subscribe({
            next: (response)=> {
              console.log('La demande est encoyé', response);
            } ,
            error : (error) => {
              console.error('Erreur d envoie', error);
            }
          })
        }
      })
      
    }
  };

  
}
