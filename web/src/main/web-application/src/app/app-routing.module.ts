import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MoviesComponent} from "./movies/movies.component";
import {MovieAddComponent} from "./movies/movie-add/movie-add.component";
import {MovieDetailsComponent} from "./movies/movie-details/movie-details.component";
import {MovieDeleteComponent} from "./movies/movie-delete/movie-delete.component";
import {TicketsComponent} from "./tickets/tickets.component";

const routes: Routes = [
  {path: 'movies', component: MoviesComponent},
  {path: 'movie-add', component: MovieAddComponent},
  {path: 'movie-delete', component: MovieDeleteComponent},
  {path: 'movie-details', component: MovieDetailsComponent},
  {path: 'tickets', component: TicketsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
