import { Component, OnInit } from '@angular/core';
import {MovieService} from '../shared/movie.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Movie} from '../shared/movie.model';
import {Trailer} from '../shared/trailer.model';

@Component({
  selector: 'app-movie-update',
  templateUrl: './movie-update.component.html',
  styleUrls: ['./movie-update.component.css']
})
export class MovieUpdateComponent implements OnInit {
  movieId = '';
  movieName = '';
  movieDuration = '';
  movieGenre = '';
  trailerYear = '';
  soundtrack = '';

  constructor(private movieService: MovieService,  private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  exists(): boolean {
    return this.movieId != null && this.movieName != null &&  this.movieDuration != null &&  this.movieGenre != null
      && this.soundtrack != null && this.trailerYear != null;
  }


  updateMovie(): void {
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    console.log('Id-ul e' + +this.movieId);

    const trailer: Trailer = {publishingYear: +this.trailerYear, soundtrack: this.soundtrack} as Trailer;

    const movie: Movie = {id: +this.movieId, name: this.movieName, duration: +this.movieDuration,
      genre: this.movieGenre, trailer} as Movie;
    this.movieService.updateMovie(+this.movieId, movie)
        // tslint:disable-next-line:no-shadowed-variable
        .subscribe(movie => console.log('updated movie: ', movie));

     this.back();
  }

  back(): void{
      this.movieService.getMovies().subscribe();
      this.location.back();
  }

}
