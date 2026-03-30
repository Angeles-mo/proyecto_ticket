/* Permite que Angular pueda inyectar este servicio en otros componentes */
import { Injectable } from '@angular/core'; 

/* La clase que hace las peticiones HTTP al backend */
import { HttpClient } from '@angular/common/http';

/* El tipo de dato que devuelven las peticiones HTTP, similar a una promesa en JavaScript */
import { Observable, BehaviorSubject} from 'rxjs';


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

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { email, password });
  }

  updateLoginStatus(status: boolean) {
    this.loggedIn.next(status);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('hasArtist');
    this.updateLoginStatus(false);
  }
}