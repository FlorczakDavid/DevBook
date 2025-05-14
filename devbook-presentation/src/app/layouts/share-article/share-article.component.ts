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
        /^https:\/\/([a-zA-Z0-9]{1}[a-zA-Z0-9-]{0,61}[a-zA-Z0-9]{1}|[a-zA-Z0-9]{1,63})(\.([a-zA-Z0-9]{1}[a-zA-Z0-9-]{0,61}[a-zA-Z0-9]{1}|[a-zA-Z0-9]{1,63})){0,3}\.([a-zA-Z0-9]{1}[a-zA-Z0-9-]{0,61}[a-zA-Z0-9]{1}|[a-zA-Z0-9]{2,63})$/
      ),
    ]),
  });

  async onSubmit() {
    if (this.formGroup.valid) {
      console.log('form', this.formGroup.value);
      const token = localStorage.getItem('token');

      try {
        const response = await fetch('http://localhost:8080/article', {
          method: "POST",
          headers : {"Content-type": "application/json", 
            "Authorization": `Bearer ${token}`},
          body: JSON.stringify({url: this.formGroup.value.url})
        })
        if(response.ok){
          alert('url correctly sent')
        } else if (!response.ok){
          const err = await response.json()
          if(err){
            alert(err.fieldsErrors.url)
          }
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
