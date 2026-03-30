import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  imports: [CommonModule, RouterModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
  standalone: true
})
export class HeaderComponent implements OnInit {
  isArtist: boolean = false;
  isLoggedIn: boolean = false;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit() {
    
    this.authService.isLoggedIn$.subscribe(status => {
      this.isArtist = localStorage.getItem('hasArtist') === 'true';
      this.isLoggedIn = !!localStorage.getItem('token');
    });

  }

  /**
   * Cierra la sesión del usuario eliminando el token y rol del almacenamiento local. Redirige a la página de login.
   */
  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
