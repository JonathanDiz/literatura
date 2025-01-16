import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BookService } from '../../services/book.service';
import { Book } from '../../models/book.model';

@Component({
  selector: 'app-search-history',
  templateUrl: './search-history.component.html',
  styleUrls: ['./search-history.component.css'],
  standalone: true,
  imports: [CommonModule, RouterModule]
})
export class SearchHistoryComponent implements OnInit {
  searchHistory: Book[] = [];

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.loadSearchHistory();
  }

  loadSearchHistory(): void {
    this.bookService.getSearchHistory()
      .subscribe({
        next: (history) => {
          this.searchHistory = history;
        },
        error: (error) => {
          console.error('Error loading search history:', error);
        }
      });
  }
}