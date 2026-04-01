/* Permite que Angular pueda inyectar este servicio en otros componentes */
import { Injectable } from '@angular/core'; 

/* La clase que hace las peticiones HTTP al backend */
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

/* El tipo de dato que devuelven las peticiones HTTP, similar a una promesa en JavaScript */
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

export interface AccountRegisterData {
  email: string;
  contraseña: string;
}

export interface LoginResponse {
  token: string;
  role: string; // "user" o "artist"
  userId?: string;      // Si es usuario
  artistId?: string;    // Si es artista
}

export interface AuthResponse {
  success: boolean;
  mensaje: string;
  data?: any;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';
  private loggedIn = new BehaviorSubject<boolean>(!!localStorage.getItem('token'));

  constructor(private http: HttpClient) {}

  get isLoggedIn$() {
    return this.loggedIn.asObservable();
  }

  /**
   * Registra una nueva cuenta (primer paso)
   * @param accountData Email y contraseña
   * @returns Observable con la respuesta del servidor
   */
  registerAccount(accountData: AccountRegisterData): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, accountData).pipe(
      tap((response) => {
        if (response.success) {
          console.log('Cuenta creada exitosamente');
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Login de usuario
   * @param email Email del usuario
   * @param password Contraseña del usuario
   * @returns Observable con token y datos del usuario
   */
  login(email: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { email, password }).pipe(
      tap((response) => {
        if (response.token) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);
          
          // Guardar userId o artistId según el rol
          if (response.role === 'user' && response.userId) {
            localStorage.setItem('userId', response.userId);
            localStorage.removeItem('artistId');
          } else if (response.role === 'artist' && response.artistId) {
            localStorage.setItem('artistId', response.artistId);
            localStorage.removeItem('userId');
          }
          
          this.updateLoginStatus(true);
          console.log('Usuario autenticado correctamente como', response.role);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Verifica si un email ya está registrado
   * @param email Email a verificar
   * @returns Observable con el resultado
   */
  checkEmailExists(email: string): Observable<{ exists: boolean }> {
    return this.http.post<{ exists: boolean }>(`${this.apiUrl}/check-email`, { email }).pipe(
      catchError(this.handleError)
    );
  }

  /**
   * Obtiene el token guardado
   * @returns Token o null
   */
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  /**
   * Obtiene el ID del usuario
   * @returns ID del usuario
   */
  getUserId(): string | null {
    return localStorage.getItem('userId');
  }

  /**
   * Obtiene el rol del usuario
   * @returns Rol del usuario ("user" o "artist")
   */
  getUserRole(): string | null {
    return localStorage.getItem('role');
  }

  /**
   * Valida que el email tenga un formato correcto
   * @param email Email a validar
   * @returns true si el email es válido
   */
  isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

  /**
   * Valida que la contraseña cumpla con los requisitos
   * Mínimo 8 caracteres, al menos una mayúscula, una minúscula, un número
   * @param password Contraseña a validar
   * @returns true si la contraseña cumple con los requisitos
   */
  isValidPassword(password: string): boolean {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    return passwordRegex.test(password);
  }

  updateLoginStatus(status: boolean) {
    this.loggedIn.next(status);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('userId');
    localStorage.removeItem('artistId');
    localStorage.removeItem('currentUser');
    this.updateLoginStatus(false);
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

    console.error('Error en AuthService:', errorMessage, error);
    return throwError(() => new Error(errorMessage));
  }
}