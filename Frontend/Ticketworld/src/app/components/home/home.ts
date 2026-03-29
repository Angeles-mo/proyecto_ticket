import { N } from '@angular/cdk/keycodes';
import { CommonModule } from '@angular/common';
import { Component, HostListener, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Header } from '../header/header';
import { Footer } from '../footer/footer';

/* Componente Home: Página de inicio de la aplicación */
@Component({
  selector: 'app-home',
  imports: [CommonModule, Header, Footer],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class HomeComponent {
}
