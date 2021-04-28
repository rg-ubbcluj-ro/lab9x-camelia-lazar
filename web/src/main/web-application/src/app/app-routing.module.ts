import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MoviesComponent} from './movies/movies.component';
import {MovieAddComponent} from './movies/movie-add/movie-add.component';
import {MovieDetailsComponent} from './movies/movie-details/movie-details.component';
import {MovieDeleteComponent} from './movies/movie-delete/movie-delete.component';
import {TicketsComponent} from './tickets/tickets.component';
import {MovieUpdateComponent} from './movies/movie-update/movie-update.component';
import {CinemasComponent} from './cinemas/cinemas.component';
import {CinemaFilterByNameComponent} from './cinemas/cinema-filter-by-name/cinema-filter-by-name.component';
import {CinemaSortComponent} from './cinemas/cinema-sort/cinema-sort.component';
import {ClientsComponent} from './clients/clients.component';
import {ClientFilterComponent} from "./clients/client-filter/client-filter.component";
import {ClientSortComponent} from "./clients/client-sort/client-sort.component";

const routes: Routes = [
  {path: 'movies', component: MoviesComponent},
  {path: 'movie-add', component: MovieAddComponent},
  {path: 'movie-delete', component: MovieDeleteComponent},
  {path: 'movie-details', component: MovieDetailsComponent},
  {path: 'movie-update', component: MovieUpdateComponent},
  {path: 'cinemas', component: CinemasComponent},
  {path: 'cinemas-filter-by-name', component: CinemaFilterByNameComponent},
  {path: 'cinemas-sort', component: CinemaSortComponent},
  {path: 'clients', component: ClientsComponent},
  {path: 'clients-filter', component: ClientFilterComponent},
  {path: 'clients-sort', component: ClientSortComponent},
  {path: 'tickets', component: TicketsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
