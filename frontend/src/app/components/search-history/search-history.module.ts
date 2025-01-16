import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { SearchHistoryComponent } from './search-history.component';

@NgModule({
  imports: [
    HttpClientModule,
    SearchHistoryComponent  
  ],
  exports: [
    SearchHistoryComponent  
  ]
})
export class SearchHistoryModule { }