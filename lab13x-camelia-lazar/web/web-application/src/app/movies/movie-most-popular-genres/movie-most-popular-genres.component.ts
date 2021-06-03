import { Component, OnInit } from '@angular/core';
import {MovieService} from '../shared/movie.service';
import {Movie} from '../shared/movie.model';

@Component({
  selector: 'app-movie-most-popular-genres',
  templateUrl: './movie-most-popular-genres.component.html',
  styleUrls: ['./movie-most-popular-genres.component.css']
})
export class MovieMostPopularGenresComponent implements OnInit {
  genres: Map<string, number> = new Map<string, number>();

  genresStatistic: Array<[string, number]> = [];

  movies: Movie[] | undefined;

  constructor(private movieService: MovieService) {
    this.movieService.getMovies()
      .subscribe(movies => {
        this.movies = movies.movies;
        this.movies?.forEach((movie) => {
          // @ts-ignore
          if (this.genres.get(movie.genre) === undefined) {
            // @ts-ignore
            this.genres.set(movie.genre, 0);
          }
          // @ts-ignore
          this.genres.set(movie.genre, this.genres.get(movie.genre) + 1);
        });

        for (const genre of this.genres.keys()) {
          // @ts-ignore
          this.genresStatistic?.push([genre, this.genres.get(genre)]);
        }
      });
  }

  ngOnInit(): void {
  }

}
