import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MoviesComponent} from './movies/movies.component';
import {MovieAddComponent} from './movies/movie-add/movie-add.component';
import {MovieDetailsComponent} from './movies/movie-details/movie-details.component';
import {MovieDeleteComponent} from './movies/movie-delete/movie-delete.component';
import {TicketsComponent} from './tickets/tickets.component';
import {MovieUpdateComponent} from './movies/movie-update/movie-update.component';
import {CinemasComponent} from './cinemas/cinemas.component';
import {ClientsComponent} from './clients/clients.component';
import {ClientFilterComponent} from './clients/client-filter/client-filter.component';
import {ClientSortComponent} from './clients/client-sort/client-sort.component';
import {TicketAddComponent} from './tickets/ticket-add/ticket-add.component';
import {TicketDeleteComponent} from './tickets/ticket-delete/ticket-delete.component';
import {TicketUpdateComponent} from './tickets/ticket-update/ticket-update.component';
import {MovieMostPopularGenresComponent} from './movies/movie-most-popular-genres/movie-most-popular-genres.component';

const routes: Routes = [
  {path: 'movies', component: MoviesComponent},
  {path: 'movie-add', component: MovieAddComponent},
  {path: 'movie-delete', component: MovieDeleteComponent},
  {path: 'movie-details', component: MovieDetailsComponent},
  {path: 'movie-update', component: MovieUpdateComponent},
  {path: 'movie-most-popular-genres', component: MovieMostPopularGenresComponent},
  {path: 'cinemas', component: CinemasComponent},
  {path: 'clients', component: ClientsComponent},
  {path: 'clients-filter', component: ClientFilterComponent},
  {path: 'clients-sort', component: ClientSortComponent},
  {path: 'tickets', component: TicketsComponent},
  {path: 'ticket-add', component: TicketAddComponent},
  {path: 'ticket-delete', component: TicketDeleteComponent},
  {path: 'ticket-update', component: TicketUpdateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
