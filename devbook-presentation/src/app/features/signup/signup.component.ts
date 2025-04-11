import { Component } from '@angular/core';
import { FormComponent } from '../../shared/form/form.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  imports: [FormComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  signup: FormGroup;

  constructor(private fb: FormBuilder) {
    this.signup = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  onSubmit() {
    if (this.signup.valid) {
      console.log('Form Submitted!', this.signup.value);
    }
  }
}
