import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { SharedService } from '../shared.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingINComponent implements OnInit {
  email: string = '';
  password: string = '';
  errorMessage: string | null = null;
  isLoggedIn: boolean = false;

  constructor(private http: HttpClient, private router: Router, private sharedService: SharedService) {
    // Підписка на зміну стану логіну
    this.sharedService.isLoggedIn$.subscribe(isLoggedIn =>{
      this.isLoggedIn = isLoggedIn;
    });
  }

  ngOnInit() {
    // Check if token exists in localStorage on component initialization
    if (localStorage.getItem('token')) {
      //this.isLoggedIn = true;
      this.sharedService.changeLoginState(true);
    }
  }
  // Login method
  login() {
    const loginData = {
      email: this.email,
      password: this.password
    };

    this.http.post('http://localhost:8080/api/customer/login', loginData)
      .subscribe({
        next: (response: any) => {
          console.log('Login successful', response);
          // Store token in localStorage
          localStorage.setItem('token', response.token);
         // this.isLoggedIn = true; // Set isLoggedIn to true after successful login
          this.sharedService.changeLoginState(true);
          this.router.navigate(['/about']); // Redirect after login
        },
        error: (error) => {
          this.errorMessage = 'Login failed';
          console.error('Login failed', error);
        }
      });
  }

}
