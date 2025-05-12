import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DatePipe, NgIf} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {RssProvider} from '../@types/rss-provider';

@Component({
  selector: 'app-rss-form',
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './rss-providers.page.html',
  styleUrl: './rss-providers.page.css'
})
export class RssProvidersPage {
  @Input() formGroup: FormGroup = new FormGroup({
    url: new FormControl(''),
  });

  @Input() toastMessage: RssProvider | null = null;

  @Output() formSubmit = new EventEmitter<void>();

  submitForm() {
    console.log('Formulaire valide :', this.formGroup.valid);
    console.log('Valeurs du formulaire :', this.formGroup.value);
    if (this.formGroup.valid) {
      this.formSubmit.emit();
    }
  }

  isInvalidAndTouchedOrDirty(control: any): boolean {
    return control.invalid && (control.dirty || control.touched);
  }

  showToast(message: RssProvider) {
    const toastContainer = document.getElementById('toast-container');
    if (toastContainer) {
      toastContainer.style.backgroundColor = '#c6f8d1'; // Couleur rouge pour l'erreur
      toastContainer.style.padding = '10px';
    }

    if (toastContainer) {
      const toast = document.createElement('div');
      toast.className = 'toast success';

      // Contenu du toast avec une icône et un message structuré
      toast.innerHTML = `
      <span class="toast-icon">✔️</span>
      <div>
        <strong>${message.title}</strong>
        <p>${message.description || 'Aucune description disponible.'}</p>
        <a href="${message.link}" target="_blank">Voir plus</a>
      </div>
    `;

      toastContainer.appendChild(toast);

      // Animation d'affichage
      setTimeout(() => {
        toast.classList.add('show');
      }, 100);

      // Suppression après 5 secondes
      setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => {
          toastContainer.removeChild(toast);
          toastContainer.style.padding = '';
        }, 300);
      }, 5000);
    }
  }


  showToastError(errorResponse: any) {
    const toastContainer = document.getElementById('toast-container');
    if (toastContainer) {
      toastContainer.style.backgroundColor = '#f8d7da'; // Couleur rouge pour l'erreur
      toastContainer.style.padding = '10px';
    }
    if (toastContainer) {
      const toast = document.createElement('div');
      toast.className = 'toast error';

      // Récupération des messages d'erreur
      const fieldErrors = errorResponse.fieldsErrors?.url?.join(', ') || 'Erreur inconnue.';
      const globalErrors = errorResponse.globalErrors?.join(', ') || '';

      // Contenu du toast
      toast.innerHTML = `
      <span class="toast-icon">❌</span>
      <div>
        <strong>Erreur :</strong>
        <p>${fieldErrors}</p>
        ${globalErrors ? `<p>${globalErrors}</p>` : ''}
      </div>
    `;

      toastContainer.appendChild(toast);

      // Animation d'affichage
      setTimeout(() => {
        toast.classList.add('show');
      }, 100);

      // Suppression après 5 secondes
      setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => {
          toastContainer.removeChild(toast);
          toastContainer.style.padding = '';
        }, 300);
      }, 5000);
    }
  }
}
