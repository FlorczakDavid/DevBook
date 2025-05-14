import { Component } from '@angular/core';
import { Profile } from '../../core/models/Profile';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  imports: [FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  // model = new Subscription(localStorage.getItem("subsciption.article") == 'TRUE', localStorage.getItem("subsciption.rss") == 'TRUE');
  model = new Profile(true, true);

  ngOnInit() {
    const token = localStorage.getItem('token');
    this.getUserProfile(token!).then(result => {
      this.model = result;
    });
  }

  onSubmit() {
    this.updateProfile(this.model.article, this.model.rss);
  }

  async updateProfile(articleSub: boolean, rssSub: boolean) {
    const url = "http://localhost:8080/accounts/updateProfile";
    try {
      const response = await fetch(url, {
        method: "PATCH",
        headers: {
          'Content-Type': 'application/json',
          'Authorization':  `Bearer ${localStorage.getItem('token')}`
        },
        body: JSON.stringify({
          token: localStorage.getItem('token'),
          notifArticle: articleSub,
          notifRss: rssSub
        })
      });
      if (!response.ok) {
        throw new Error(`Response status: ${response.status}`);
      }

      const json = await response.json();
      console.log(json);
    } catch (error: any) {
      console.error(error.message);
    }
  }

  async getUserProfile(token: string): Promise<Profile> {
    const url = "http://localhost:8080/accounts/profile/"+token;
    let ret = new Profile(true, false);
    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {
          'Authorization':  `Bearer ${localStorage.getItem('token')}`
        },
      });
      if (!response.ok) {
        throw new Error(`Response status: ${response.status}`);
      }

      const profile = await response.json();
      ret.article = profile.notifArticle;
      ret.rss = profile.notifRss;
    } catch (error: any) {
      console.error(error.message);
    }
    return ret;
  }
}
