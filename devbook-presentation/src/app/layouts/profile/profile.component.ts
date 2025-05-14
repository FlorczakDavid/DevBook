import { Component } from '@angular/core';
import { Profile } from '../../core/models/Profile';
import { FormsModule } from '@angular/forms';
import { ProfileService } from '../../core/profile.service';

@Component({
  selector: 'app-profile',
  imports: [FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  profileService = new ProfileService;
  model = new Profile(true, true);

  ngOnInit() {
    const token = localStorage.getItem('token');
    this.profileService.getUserProfile(token!).then(result => {
      this.model = result;
    });
  }

  onSubmit() {
    this.profileService.updateProfile(this.model.article, this.model.rss);
  }
}
