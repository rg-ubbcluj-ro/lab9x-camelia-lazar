import { Component, OnInit } from '@angular/core';
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  movies: Movie[] | undefined;

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.movieService.getMovies()
      .subscribe(movies => this.movies = movies.movies);
  }
}
