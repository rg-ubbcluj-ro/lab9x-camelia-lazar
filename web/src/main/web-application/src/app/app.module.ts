import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import { MovieListComponent } from './movies/movie-list/movie-list.component';
import { MovieService } from './movies/shared/movie.service';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { MovieAddComponent } from './movies/movie-add/movie-add.component';
import { MovieDetailsComponent } from './movies/movie-details/movie-details.component';
import { MovieDeleteComponent } from './movies/movie-delete/movie-delete.component';
import { MessageComponent } from './movies/message/message.component';
import { TicketsComponent } from './tickets/tickets.component';
import { TicketsListComponent } from './tickets/tickets-list/tickets-list.component';

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    MovieListComponent,
    MovieAddComponent,
    MovieDetailsComponent,
    MovieDeleteComponent,
    MessageComponent,
    TicketsComponent,
    TicketsListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [MovieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
