import { Component } from '@angular/core';
import { NotificationComponent } from '../../features/header/notification/notification.component';

@Component({
  selector: 'app-header',
  imports: [NotificationComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
