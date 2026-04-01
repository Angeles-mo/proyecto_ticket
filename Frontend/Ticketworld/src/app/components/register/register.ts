import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, Router } from "@angular/router";
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class RegisterComponent {

  email: string = '';
  contraseña: string = '';
  errorMessage: string = '';
  successMessage: string = '';
  isLoading: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  /**
   * Crear la cuenta (email + contraseña) y navegar a user-register o artist-register
   * @param type 'user' o 'artist' - Tipo de registro elegido
   */
  onNavigateToRegister(type: 'user' | 'artist') {
    // Limpiar mensajes previos
    this.errorMessage = '';
    this.successMessage = '';

    // Validaciones
    if (!this.email || !this.contraseña) {
      this.errorMessage = 'Por favor, completa todos los campos';
      return;
    }

    if (!this.authService.isValidEmail(this.email)) {
      this.errorMessage = 'El email no es válido';
      return;
    }

    if (!this.authService.isValidPassword(this.contraseña)) {
      this.errorMessage = 'La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número';
      return;
    }

    // Verificar que el email no exista
    this.isLoading = true;

    this.authService.checkEmailExists(this.email).subscribe({
      next: (response) => {
        if (response.exists) {
          this.isLoading = false;
          this.errorMessage = 'Este email ya está registrado';
        } else {
          // Proceder a crear la cuenta
          this.createAccountAndNavigate(type);
        }
      },
      error: (err) => {
        console.error('Error verificando email:', err);
        // Continuar aunque falle la verificación
        this.createAccountAndNavigate(type);
      }
    });
  }

  /**
   * Crea la cuenta en el backend
   * @param type 'user' o 'artist'
   */
  private createAccountAndNavigate(type: 'user' | 'artist') {
    this.authService.registerAccount({
      email: this.email,
      contraseña: this.contraseña
    }).subscribe({
      next: (response) => {
        this.isLoading = false;
        if (response.success) {
          this.successMessage = 'Cuenta creada exitosamente. Redirigiendo...';
          console.log('Cuenta creada exitosamente');
          
          // Guardar email y contraseña en sessionStorage para login automático después
          sessionStorage.setItem('tempEmail', this.email);
          sessionStorage.setItem('tempPassword', this.contraseña);
          
          // Navegar a user-register o artist-register según lo elegido
          setTimeout(() => {
            if (type === 'user') {
              this.router.navigate(['/user-register']);
            } else {
              this.router.navigate(['/artist-register']);
            }
          }, 500);
        }
      },
      error: (err) => {
        this.isLoading = false;
        console.error('Error creando cuenta:', err);
        this.errorMessage = err.message || 'Error al crear la cuenta';
      }
    });
  }

}