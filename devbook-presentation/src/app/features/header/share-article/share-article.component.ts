import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-share-article',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './share-article.component.html',
  styleUrl: './share-article.component.css'
})
export class ShareArticleComponent {

  formGroup = new FormGroup({
    url: new FormControl('', [
      Validators.required,
      Validators.pattern(
        /^(https?:\/\/)?([\w\-]+\.)+[\w\-]{2,63}(\/[\w\-._~:/?#[\]@!$&'()*+,;=]*)?$/
      ),
    ]),
  });

  async onSubmit() {
    if (this.formGroup.valid) {
      console.log(this.formGroup.value.url);
      try {
        const response = await fetch('http://localhost:8080/article', {
          method: "POST",
          headers : {"Content-type": "application/json"},
          body: JSON.stringify({url: this.formGroup.value.url})
        })
        if(response.ok){
          alert('url correctly sent')
        } else if(response.status >= 400 && response.status  <= 500){
          alert('client error')
        } else if(response.status >= 500 && response.status <= 600) {
            alert('server error')
        }
      } catch(err:any) {
        alert('an enexpected error has occured')
      } 
    }
  }

  isInvalid(controlName: string): boolean {
    const control = this.formGroup.get(controlName);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }
}
