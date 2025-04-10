import { Component } from '@angular/core';
import { NotificationComponent } from '../../features/header/notification/notification.component';
import {ShareArticleComponent} from '../../features/header/share-article/share-article.component';

@Component({
  selector: 'app-header',
  imports: [NotificationComponent, ShareArticleComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
