import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AuthService } from './auth.service';

export interface UserRegisterData {
  nombre: string;
  apellidos: string;
  numeroTelefono: string;
}

export interface UserResponse {
  id: string;
  nombre: string;
  apellidos: string;
  numeroTelefono: string;
  email: string;
  role: string;
  fechaRegistro: string;
}

export interface UserApiResponse {
  success: boolean;
  mensaje: string;
  user?: UserResponse;
}

@Injectable({
  providedIn: 'root',
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  /**
   * Registra un usuario con su información personal
   * Se ejecuta después de haber creado la cuenta en el register
   * @param userData Datos personales del usuario (nombre, apellidos, teléfono)
   * @returns Observable con la respuesta del servidor
   */
  registerUser(userData: UserRegisterData): Observable<UserApiResponse> {
    // Obtener el token del authService
    const token = this.authService.getToken();
    
    return this.http.post<UserApiResponse>(`${this.apiUrl}/register`, userData).pipe(
      tap((response) => {
        if (response.success && response.user) {
          // Guardar información del usuario en localStorage
          localStorage.setItem('currentUser', JSON.stringify(response.user));
          console.log('Usuario registrado exitosamente:', response.user);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Obtiene los datos del usuario actual
   * @returns Observable con los datos del usuario
   */
  getCurrentUser(): Observable<UserResponse> {
    const userId = this.authService.getUserId();
    if (!userId) {
      return throwError(() => new Error('No hay usuario autenticado'));
    }
    return this.http.get<UserResponse>(`${this.apiUrl}/${userId}`).pipe(
      tap((user) => {
        localStorage.setItem('currentUser', JSON.stringify(user));
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Obtiene el usuario del localStorage
   * @returns Usuario guardado o null
   */
  getUserFromStorage(): UserResponse | null {
    const user = localStorage.getItem('currentUser');
    return user ? JSON.parse(user) : null;
  }

  /**
   * Actualiza los datos del usuario
   * @param userData Nuevos datos del usuario
   * @returns Observable con la respuesta del servidor
   */
  updateUser(userData: Partial<UserRegisterData>): Observable<UserApiResponse> {
    const userId = this.authService.getUserId();
    if (!userId) {
      return throwError(() => new Error('No hay usuario autenticado'));
    }

    return this.http.put<UserApiResponse>(`${this.apiUrl}/${userId}`, userData).pipe(
      tap((response) => {
        if (response.success && response.user) {
          localStorage.setItem('currentUser', JSON.stringify(response.user));
          console.log('Usuario actualizado:', response.user);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Valida que el nombre sea válido
   * Mínimo 2 caracteres, solo letras y espacios
   * @param name Nombre a validar
   * @returns true si el nombre es válido
   */
  isValidName(name: string): boolean {
    const nameRegex = /^[a-zA-ZáéíóúÁÉÍÓÚ\s]{2,}$/;
    return nameRegex.test(name.trim());
  }

  /**
   * Valida que el teléfono tenga un formato correcto
   * Acepta números, guiones y espacios, mínimo 9 dígitos
   * @param phone Teléfono a validar
   * @returns true si el teléfono es válido
   */
  isValidPhone(phone: string): boolean {
    const phoneRegex = /^[0-9\s\-+]{9,}$/;
    return phoneRegex.test(phone.trim());
  }

  /**
   * Manejo de errores HTTP
   * @param error Error de la petición HTTP
   * @returns Observable con el error
   */
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Ha ocurrido un error en la solicitud';

    if (error.error instanceof ErrorEvent) {
      // Error del cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del servidor
      if (error.error?.mensaje) {
        errorMessage = error.error.mensaje;
      } else if (error.error?.message) {
        errorMessage = error.error.message;
      } else {
        errorMessage = `Error del servidor: ${error.status} - ${error.statusText}`;
      }
    }

    console.error('Error en UserService:', errorMessage, error);
    return throwError(() => new Error(errorMessage));
  }
}