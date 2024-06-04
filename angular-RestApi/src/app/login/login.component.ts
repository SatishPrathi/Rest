import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) { }

  login(): void {
    const body = { username: this.username, password: this.password };
    this.http.post<any>('http://localhost:8080/api/login', body).subscribe(
      response => {
        console.log('Login successful', response);
        // Extracting role from the roles array
        const role = response.roles && response.roles.length > 0 ? response.roles[0] : '';
        // Store token and role in local storage
        localStorage.setItem('token', response.access_token);
        localStorage.setItem('role', role);
        localStorage.setItem('username', this.username); // Store username
        // Redirect to welcome page
        this.router.navigate(['/welcome']);
      },
      error => {
        console.error('Login failed', error);
        alert('Login failed');
      }
    );
  }
}
