import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-regest',
  templateUrl: './regest.component.html',
  styleUrls: ['./regest.component.css']
})
export class RegestComponent {
  email: string = '';
  password: string = '';
  passwordAgain: string = '';
  errorMessage: string | null = null;

  constructor(private http: HttpClient, private router: Router) { }

  register() {
    if (this.password !== this.passwordAgain) {
      this.errorMessage = 'Паролі не співпадають';
      console.error(this.errorMessage);
      return;
    }

    const registrationData = {
      email: this.email,
      password: this.password
    };
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response' as 'response'
    };

    this.http.post('http://localhost:8080/api/users/register', registrationData, httpOptions)
      .subscribe({
        next: (response) => {
          console.log('Response status:', response.status);
          console.log('Response body:', response.body);
          if (response.status === 201) {
            console.log('Реєстрація успішна', response);
            this.router.navigate(['/sing-in']);
          } else {
            this.errorMessage = 'Сталася невідома помилка';
          }
        },
        error: (error) => {
          console.error('Помилка:', error);
          if (error.status === 409) {
            this.errorMessage = 'Користувач з такою поштою вже існує';
          } else {
            this.errorMessage = 'Реєстрація не лася';
          }
        }
      });
}}
