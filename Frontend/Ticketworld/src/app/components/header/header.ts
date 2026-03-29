import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [CommonModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class HeaderComponent implements OnInit {
  isArtist: boolean = false;

  ngOnInit() {
    const role = localStorage.getItem('role');
    this.isArtist = role === 'artist';
  }
}
