import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-share-article',
  imports: [ReactiveFormsModule],
  templateUrl: './share-article.component.html',
  styleUrl: './share-article.component.css'
})
export class ShareArticleComponent {
  urlForm = new FormGroup({
    url: new FormControl('', [
      forbiddenUrlValidator()
    ])
  });
  onSubmit(){
    
  }

  formGroup = new FormGroup({
    url: new FormControl('', [
      Validators.required,
      Validators.pattern(
        /^(https?:\/\/)?([\w\-]+\.)+[\w\-]{2,63}(\/[\w\-._~:/?#[\]@!$&'()*+,;=]*)?$/
      ),
    ]),
  });

  onSubmit() {
    if (this.formGroup.valid) {
      console.log(this.formGroup.value);
    }
  }

  isInvalid(controlName: string): boolean {
    const control = this.formGroup.get(controlName);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }
}
