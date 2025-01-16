import { ApplicationConfig } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { BookSearchComponent } from './components/book-search/book-search.component';
import { SearchHistoryComponent } from './components/search-history/search-history.component';

const routes: Routes = [
  { 
    path: '', 
    redirectTo: '/search', 
    pathMatch: 'full'  // Tipado específicamente como 'full'
  },
  { 
    path: 'search', 
    component: BookSearchComponent  // Usamos component en lugar de loadComponent para simplificar
  },
  { 
    path: 'history', 
    component: SearchHistoryComponent  // Usamos component en lugar de loadComponent para simplificar
  },
  { 
    path: '**', 
    redirectTo: '/search'
  }
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient()
  ]
};