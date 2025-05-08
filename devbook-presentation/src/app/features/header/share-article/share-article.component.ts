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
  // urlForm = new FormGroup({
  //   url: new FormControl('', [
  //     forbiddenUrlValidator()
  //   ])
  // });
  // onSubmit(){
    
  // }

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
      console.log(this.formGroup.value);
      try {
        const response = await fetch('http://localhost:8080/article', {
          method: "POST",
          headers : {"Content-type": "application/json"},
          body: JSON.stringify({url: "url"})
          
        })
      } catch(err:any) {
        if(err.status.code >= 400 || err.status.code <= 500){
          console.log('error', err)
          alert('client error')
        } else if(err.status.code <= 500 || err.status.code <= 600) {
            console.log('err', err)
            alert('server error')
        } else {
          console.log('err', err)
          alert('an enexpected error has occured')
        }
      } 
    }
  }

  isInvalid(controlName: string): boolean {
    const control = this.formGroup.get(controlName);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }
}
