import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ProfileLinkComponent } from '../../features/header/profile-link/profile-link.component';
import { NotificationComponent } from '../../features/header/notification/notification.component';
import {ShareArticleIconComponent} from '../../features/header/share-article-icon/share-article-icon.component';

@Component({
  selector: 'app-header',
  imports: [
    CommonModule,
    RouterLink,
    NotificationComponent,
    ProfileLinkComponent,
    ShareArticleIconComponent
],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  // showNotification: boolean = localStorage.getItem('role') == 'MEMBER';
  showNotification: boolean = true; //pour tester
  // displayedShareArticle: boolean = false;
  // toggleShareArticle(){
  //   this.displayedShareArticle = !this.displayedShareArticle;
  // }
  logout() {
    localStorage.clear();
  }
}
