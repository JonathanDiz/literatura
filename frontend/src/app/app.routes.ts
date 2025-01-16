import { Routes } from '@angular/router';
import { BookSearchComponent } from './components/book-search/book-search.component';
import { SearchHistoryComponent } from './components/search-history/search-history.component';

export const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full' },
  { path: 'search', component: BookSearchComponent },
  { path: 'history', component: SearchHistoryComponent },
  { path: '**', redirectTo: '/search' }
];