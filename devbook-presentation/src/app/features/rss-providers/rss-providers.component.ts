import {Component, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {RssProvidersPage} from '../../shared/components/forms/rss-providers.page';
import {RssProvider} from '../../shared/components/@types/rss-provider';
@Component({
  selector: 'app-rss-providers',
  imports: [],
  templateUrl: './rss-providers.component.html',
  styleUrl: './rss-providers.component.css'
})
export class RssProvidersComponent {
  @ViewChild(RssProvidersPage) rssFormComponent!: RssProvidersPage;

  rssProviderForm: FormGroup;
  toastMessage: RssProvider | null  = null;

  constructor(private readonly fb: FormBuilder, private readonly http: HttpClient) {
    this.rssProviderForm = this.fb.group({
      url: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.rssProviderForm.valid) {
      console.log("valided form")
      const formData = this.rssProviderForm.value;
      console.log( 'Test composant rss Provider', formData);
      this.http.post<RssProvider>('http://localhost:8080/rss/import', formData).subscribe({
        next: (response)=> {
          // Handle the response here
          console.log('La demande a été envoyée avec succès !', response);
          this.toastMessage = response  ;

          // Appel de la méthode showToast du composant enfant
          if (this.rssFormComponent) {
            this.rssFormComponent.showToast(response);
          }

        } ,
        error: (error) => {
          console.error('Erreur d\'envoi', error);
          if (this.rssFormComponent) {
            this.rssFormComponent.showToastError(error.error);
          }
        }
      })
    } else {
      console.log("Formulaire invalide :", this.rssProviderForm.errors);
    }
  }
}
