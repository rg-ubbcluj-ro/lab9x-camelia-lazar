import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Movie} from "./movie.model";
import {MoviesWrapper} from "./movies-wrapper";
import {catchError, tap} from "rxjs/operators";
import {MessageService} from "./message.service";

@Injectable()
export class MovieService {
  private moviesUrl = 'http://localhost:8080/api/movies';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient, private messageService: MessageService) {
  }

  getMovies(): Observable<MoviesWrapper> {
    return this.httpClient
      .get<MoviesWrapper>(this.moviesUrl);
  }

  saveMovie(movie: Movie): Observable<Movie> {
    console.log('In service');
    console.log(movie);
    return this.httpClient
      .post<Movie>(this.moviesUrl, movie);
  }

  updateMovie(id: number, movie: Movie): Observable<Movie> {
    const url = `${this.moviesUrl}/${id}`;
    return this.httpClient
      .put<Movie>(url, movie);
  }

  deleteMovie(id: number): Observable<Movie> {
    const url = `${this.moviesUrl}/${id}`;

    return this.httpClient
      .delete<Movie>(url, this.httpOptions).pipe(
        tap(_ => this.log(`deleted movie id=${id}`)),
        catchError(this.handleError<Movie>('deleteMovie'))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      //
      // // TODO: send the error to remote logging infrastructure
      // console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${"The id doesn't exist"}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`Movie: ${message}`);
  }
}
