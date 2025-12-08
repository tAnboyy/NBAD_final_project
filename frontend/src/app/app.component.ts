import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  currentYear = new Date().getFullYear();
  constructor(private authService: AuthService, private router: Router) {}

  get isAuthenticated(): boolean {
    return this.authService.isLoggedIn();
  }
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
