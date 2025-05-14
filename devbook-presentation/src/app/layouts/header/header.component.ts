import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavigationEnd, Router, RouterLink} from '@angular/router';
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
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean = !!localStorage.getItem('token');
  // showNotification: boolean = localStorage.getItem('role') == 'MEMBER';
  showNotification: boolean = true; //pour tester
  // displayedShareArticle: boolean = false;
  // toggleShareArticle(){
  //   this.displayedShareArticle = !this.displayedShareArticle;
  // }
  constructor(private router: Router) {}

  ngOnInit(): void {
    // Écoute les événements de navigation
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // Met à jour l'état de connexion
        this.isLoggedIn = !!localStorage.getItem('token');
      }
    });
  }

  logout() {
    localStorage.clear();
    this.isLoggedIn = false;
    this.router.navigateByUrl('/auth');
  }
}
