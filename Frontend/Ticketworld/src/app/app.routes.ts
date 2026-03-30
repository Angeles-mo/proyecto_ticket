import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { HomeComponent } from './components/home/home';
import { RegisterComponent } from './components/register/register';
import { ArtistRegisterComponent } from './components/artist-register/artist-register';
import { UserRegisterComponent } from './components/user-register/user-register';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'artist-register', component: ArtistRegisterComponent },
  { path: 'user-register', component: UserRegisterComponent },
  { path: '**', redirectTo: 'home' }
  
];