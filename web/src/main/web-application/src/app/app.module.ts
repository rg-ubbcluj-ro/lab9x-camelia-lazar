import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import { MovieListComponent } from './movies/movie-list/movie-list.component';
import { MovieService } from './movies/shared/movie.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { MovieAddComponent } from './movies/movie-add/movie-add.component';
import { MovieDetailsComponent } from './movies/movie-details/movie-details.component';
import { MovieDeleteComponent } from './movies/movie-delete/movie-delete.component';
import { MessageComponent } from './movies/message/message.component';
import { TicketsComponent } from './tickets/tickets.component';
import { TicketsListComponent } from './tickets/tickets-list/tickets-list.component';
import { MovieUpdateComponent } from './movies/movie-update/movie-update.component';
import { CinemasComponent } from './cinemas/cinemas.component';
import { CinemaListComponent } from './cinemas/cinema-list/cinema-list.component';
import {CinemaService} from './cinemas/shared/cinema.service';
import { CinemaFilterByNameComponent } from './cinemas/cinema-filter-by-name/cinema-filter-by-name.component';
import { CinemaSortComponent } from './cinemas/cinema-sort/cinema-sort.component';
import {TicketService} from './tickets/shared/ticket.service';
import { ClientsComponent } from './clients/clients.component';
import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientFilterComponent } from './clients/client-filter/client-filter.component';
import { ClientSortComponent } from './clients/client-sort/client-sort.component';
import {ClientService} from './clients/shared/client.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";

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
    MovieUpdateComponent,
    CinemasComponent,
    CinemaListComponent,
    CinemaFilterByNameComponent,
    CinemaSortComponent,
    ClientsComponent,
    ClientListComponent,
    ClientFilterComponent,
    ClientSortComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [MovieService, CinemaService, TicketService, ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
