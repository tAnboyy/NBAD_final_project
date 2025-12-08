import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  statusMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, { validators: this.passwordsMatch });
  }

  passwordsMatch(group: FormGroup) {
    const password = group.get('password')?.value;
    const confirm = group.get('confirmPassword')?.value;
    return password === confirm ? null : { mismatch: true };
  }

  onSubmit() {
    this.statusMessage = '';
    this.errorMessage = '';

    if (this.registerForm.invalid) {
      this.errorMessage = 'Please fix the errors before submitting.';
      return;
    }

    const { username, password } = this.registerForm.value;
    this.authService.register({ username, password }).subscribe({
      next: () => {
        this.statusMessage = 'Account created! Redirecting to login...';
        setTimeout(() => this.router.navigate(['/login']), 1200);
      },
      error: (err) => {
        this.errorMessage = err?.error ?? 'Registration failed. Try a different username.';
      }
    });
  }
}
