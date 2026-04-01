import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AuthService } from './auth.service';

export interface ArtistRegisterData {
  nombreArtistico: string;
  genero: string;
  descripcion: string;
  enlacePortfolio?: string;
}

export interface ArtistResponse {
  id: string;
  nombreArtistico: string;
  genero: string;
  descripcion: string;
  enlacePortfolio?: string;
  email: string;
  role: string;
  fechaRegistro: string;
}

export interface ArtistApiResponse {
  success: boolean;
  mensaje: string;
  artist?: ArtistResponse;
}

@Injectable({
  providedIn: 'root',
})
export class ArtistService {

  private apiUrl = 'http://localhost:8080/api/artists';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  /**
   * Registra un artista con su información
   * Se ejecuta después de haber creado la cuenta en el register
   * @param artistData Datos del artista (nombreArtistico, genero, descripcion, enlacePortfolio)
   * @returns Observable con la respuesta del servidor
   */
  registerArtist(artistData: ArtistRegisterData): Observable<ArtistApiResponse> {
    // Obtener el token del authService
    const token = this.authService.getToken();
    
    return this.http.post<ArtistApiResponse>(`${this.apiUrl}/register`, artistData).pipe(
      tap((response) => {
        if (response.success && response.artist) {
          // Guardar información del artista en localStorage
          localStorage.setItem('currentArtist', JSON.stringify(response.artist));
          console.log('Artista registrado exitosamente:', response.artist);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Obtiene los datos del artista actual
   * @returns Observable con los datos del artista
   */
  getCurrentArtist(): Observable<ArtistResponse> {
    const artistId = this.authService.getUserId();
    if (!artistId) {
      return throwError(() => new Error('No hay artista autenticado'));
    }
    return this.http.get<ArtistResponse>(`${this.apiUrl}/${artistId}`).pipe(
      tap((artist) => {
        localStorage.setItem('currentArtist', JSON.stringify(artist));
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Obtiene el artista del localStorage
   * @returns Artista guardado o null
   */
  getArtistFromStorage(): ArtistResponse | null {
    const artist = localStorage.getItem('currentArtist');
    return artist ? JSON.parse(artist) : null;
  }

  /**
   * Actualiza los datos del artista
   * @param artistData Nuevos datos del artista
   * @returns Observable con la respuesta del servidor
   */
  updateArtist(artistData: Partial<ArtistRegisterData>): Observable<ArtistApiResponse> {
    const artistId = this.authService.getUserId();
    if (!artistId) {
      return throwError(() => new Error('No hay artista autenticado'));
    }

    return this.http.put<ArtistApiResponse>(`${this.apiUrl}/${artistId}`, artistData).pipe(
      tap((response) => {
        if (response.success && response.artist) {
          localStorage.setItem('currentArtist', JSON.stringify(response.artist));
          console.log('Artista actualizado:', response.artist);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Valida que el nombre artístico sea válido
   * Mínimo 2 caracteres
   * @param name Nombre artístico a validar
   * @returns true si es válido
   */
  isValidArtisticName(name: string): boolean {
    return name.trim().length >= 2;
  }

  /**
   * Valida que el género sea válido
   * No vacío
   * @param genre Género a validar
   * @returns true si es válido
   */
  isValidGenre(genre: string): boolean {
    return genre.trim().length > 0;
  }

  /**
   * Valida que la descripción sea válida
   * Mínimo 10 caracteres
   * @param description Descripción a validar
   * @returns true si es válida
   */
  isValidDescription(description: string): boolean {
    return description.trim().length >= 10;
  }

  /**
   * Valida que el enlace del portfolio sea válido (opcional)
   * Si se proporciona, debe ser una URL válida
   * @param url URL a validar
   * @returns true si es válida o está vacía
   */
  isValidPortfolioUrl(url: string): boolean {
    if (!url || url.trim() === '') {
      return true; // Es opcional
    }
    
    try {
      new URL(url);
      return true;
    } catch {
      return false;
    }
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

    console.error('Error en ArtistService:', errorMessage, error);
    return throwError(() => new Error(errorMessage));
  }
}