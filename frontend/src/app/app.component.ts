import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  template: `
    <div class="container">
      <nav class="navigation">
        <a routerLink="/search" routerLinkActive="active">Búsqueda</a>
        <a routerLink="/history" routerLinkActive="active">Historial</a>
      </nav>
      <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .container {
      padding: 20px;
    }
    .navigation {
      margin-bottom: 20px;
      padding: 10px;
      background-color: #f8f9fa;
      border-radius: 4px;
    }
    .navigation a {
      margin-right: 15px;
      text-decoration: none;
      color: #007bff;
      padding: 5px 10px;
    }
    .navigation a.active {
      background-color: #007bff;
      color: white;
      border-radius: 4px;
    }
  `],
  standalone: true,
  imports: [RouterModule, CommonModule]
})
export class AppComponent { }