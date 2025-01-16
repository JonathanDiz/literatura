import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BookSearchComponent } from './book-search.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    BookSearchComponent  
  ],
  exports: [
    BookSearchComponent  
  ]
})
export class BookSearchModule { }