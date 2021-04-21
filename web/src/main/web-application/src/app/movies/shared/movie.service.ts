import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Movie} from "./movie.model";
import {MoviesWrapper} from "./movies-wrapper";

@Injectable()
export class MovieService {
  private moviesUrl = 'http://localhost:8080/api/movies';

  constructor(private httpClient: HttpClient) {
  }

  getMovies(): Observable<MoviesWrapper> {
    return this.httpClient
      .get<MoviesWrapper>(this.moviesUrl);
  }

  saveMovie(discipline: Movie): Observable<Movie> {
    return this.httpClient
      .post<Movie>(this.moviesUrl, discipline);
  }
}
