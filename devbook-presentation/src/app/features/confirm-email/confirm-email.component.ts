import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConfirmEmailService } from '../../services/confirm-email.service';

@Component({
  selector: 'app-confirm-email',
  imports: [],
  templateUrl: './confirm-email.component.html',
  styleUrl: './confirm-email.component.css',
})
export class ConfirmEmailComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private confirmService = inject(ConfirmEmailService);

  message: string | null = null;

  ngOnInit(): void {
    const token = this.route.snapshot.paramMap.get('token');
    if (token) {
      this.confirmService.confirm(token).subscribe({
        next: (response) => (this.message = response),
        error: (err) => (this.message = err.error || 'Erreur de confirmation'),
      });
    } else {
      this.message = 'Token manquant dans l’URL';
    }
  }
}
