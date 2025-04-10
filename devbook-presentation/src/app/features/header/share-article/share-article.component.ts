import { Component } from '@angular/core';
import { ModalComponent } from '../../../shared/modal/modal.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-share-article',
  imports: [CommonModule, ModalComponent],
  templateUrl: './share-article.component.html',
  styleUrl: './share-article.component.css'
})
export class ShareArticleComponent {
  isModalVisible = false;
  showModal(){
    this.isModalVisible = true;
  }
  hideModal(){
    this.isModalVisible = false;
  }
  // addArticle(){
  //   var modal = document.getElementById("articleModal");
  //   modal.style.display= "block";
    //var btn = document.getElementById("articleModal");
  // }
}
