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

}
