import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthInfo } from '../../core/models/authInfo';

@Component({
  selector: 'app-auth-pin',
  imports: [ReactiveFormsModule, HttpClientModule],
  templateUrl: './auth-pin.component.html',
  styleUrl: './auth-pin.component.css'
})
export class AuthPinComponent {

  pinForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.pinForm = this.fb.group({
      pin1: ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin2: ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin3: ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))],
      pin4: ['', (Validators.required, Validators.maxLength(1), Validators.minLength(1))]
    })
  }

  onPaste(event: ClipboardEvent): void {
    const clipboardData = event.clipboardData;
    const pastedText = clipboardData?.getData('text') || '';

    if (pastedText.length === 4) {
      this.pinForm.patchValue({
        pin1: pastedText[0],
        pin2: pastedText[1],
        pin3: pastedText[2],
        pin4: pastedText[3]
      });
      event.preventDefault(); // Empêche le comportement par défaut du collage
    }
  }

  validateInput(event: KeyboardEvent): void {
    const inputChar = event.key;
    if (!/^\d$/.test(inputChar)) {
      event.preventDefault(); // Empêche la saisie si ce n'est pas un chiffre
    }
  }

  moveToNext(event: Event, nextField: string | null): void {
    const input = event.target as HTMLInputElement;

    // Si un chiffre est saisi, passe au champ suivant
    if (input.value.length === 1 && nextField) {
      const nextInput = document.querySelector(`input[formControlName="${nextField}"]`) as HTMLInputElement;
      if (nextInput) {
        nextInput.focus();
      }
    }
  }

  handleBackspace(event: KeyboardEvent, previousField: string | null): void {
    const input = event.target as HTMLInputElement;

    // Si la touche Backspace est pressée et que le champ est vide
    if (event.key === 'Backspace' && input.value.length === 0 && previousField) {
      const previousInput = document.querySelector(`input[formControlName="${previousField}"]`) as HTMLInputElement;
      if (previousInput) {
        previousInput.focus();
        previousInput.value = ''; // Efface le champ précédent
        this.pinForm.get(previousField)?.setValue(''); // Met à jour le formulaire
      }
    }
  }

  onSubmit() {
    if (this.pinForm.valid) {
      const formData = this.pinForm.get('pin1')?.value + this.pinForm.get('pin2')?.value + this.pinForm.get('pin3')?.value + this.pinForm.get('pin4')?.value;
      console.log(formData);
      this.route.paramMap.subscribe(params => {
        const token = params.get('token');
        console.log(token);
        if (token) {
          this.http.post<AuthInfo>(`http://localhost:8080/accounts/doubleAuth/${token}`, formData).subscribe({
            next: (response) => {
              localStorage.setItem('token', response.token);
              localStorage.setItem('role', response.role);
              this.router.navigateByUrl('');
              console.log('La demande est enVoyé', response);
            },
            error: (error) => {
              console.error('Erreur d envoie', error);
            }
          })
        }
      })

    }
  };


}
