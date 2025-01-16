import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BookService } from '../../services/book.service';
import { Book } from '../../models/book.model';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule]
})
export class BookSearchComponent {
  searchQuery: string = '';
  books: Book[] = [];
  loading: boolean = false;

  constructor(private bookService: BookService) { }

  onSearch(): void {
    if (this.searchQuery.trim()) {
      this.loading = true;
      this.bookService.searchBooks(this.searchQuery)
        .subscribe({
          next: (results) => {
            this.books = results;
            this.loading = false;
          },
          error: (error) => {
            console.error('Error searching books:', error);
            this.loading = false;
          }
        });
    }
  }
}