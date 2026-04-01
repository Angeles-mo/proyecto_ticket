import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onLogin() {
    // Validar que los campos no estén vacíos
    if (!this.email || !this.password) {
      this.errorMessage = 'Por favor, completa todos los campos';
      return;
    }

    // Validar email
    if (!this.authService.isValidEmail(this.email)) {
      this.errorMessage = 'El email no es válido';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.authService.login(this.email, this.password).subscribe({
      next: (response) => {
        this.isLoading = false;
        
        // Guardar token y rol
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        
        // Guardar userId o artistId según el rol
        if (response.role === 'user' && response.userId) {
          localStorage.setItem('userId', response.userId);
        } else if (response.role === 'artist' && response.artistId) {
          localStorage.setItem('artistId', response.artistId);
        }

        // Actualizar estado de login
        this.authService.updateLoginStatus(true);
        
        // Redirigir al home
        this.router.navigate(['/home']);
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMessage = 'Email o contraseña incorrectos';
        console.error('Error en login:', err);
      }
    });
  }
  
}