import { Component } from '@angular/core';
import { Subscription } from '../../core/models/subscription';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-profile',
  imports: [FormsModule, JsonPipe],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  // model = new Subscription(localStorage.getItem("subsciption.article") == 'TRUE', localStorage.getItem("subsciption.rss") == 'TRUE');
  model = new Subscription(false, true);

  onSubmit() {
    localStorage.setItem("subscription.article", this.model.article.toString().toUpperCase())
    localStorage.setItem("subscription.rss", this.model.rss.toString().toUpperCase())
    this.updateDB();
  }

  // TODO - à exporter
  async updateDB() {
    const url = "http://localhost:8080/accounts/set-notifications";
    try {
      const response = await fetch(url, {
        method: "POST",
        body: JSON.stringify({
          user: localStorage.getItem("account.id"),
          article: localStorage.getItem("subsciption.article") == 'TRUE',
          rss: localStorage.getItem("subsciption.rss") == 'TRUE'
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
}
