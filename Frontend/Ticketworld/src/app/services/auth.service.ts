/* Permite que Angular pueda inyectar este servicio en otros componentes */
import { Injectable } from '@angular/core'; 

/* La clase que hace las peticiones HTTP al backend */
import { HttpClient } from '@angular/common/http';

/* El tipo de dato que devuelven las peticiones HTTP, similar a una promesa en JavaScript */
import { Observable } from 'rxjs';

/* El decorador @Injectable indica que esta clase puede ser inyectada como una dependencia en otros componentes o servicios de Angular. 
El parámetro providedIn: 'root' significa que este servicio estará disponible en toda la aplicación, es decir, se creará una única instancia de este servicio que será compartida por todos los componentes que lo necesiten. */
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { email, password });
  }

}