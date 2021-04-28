import { Component, OnInit } from '@angular/core';
import {MovieService} from '../shared/movie.service';
import {Location} from '@angular/common';
import {Movie} from '../shared/movie.model';
import {ActivatedRoute} from '@angular/router';
import {AbstractControl, FormControl, FormControlName, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit {
  addMovieForm = new FormGroup({
    movieName: new FormControl('',
      [Validators.required, Validators.minLength(3)]),
    duration: new FormControl('',
      [Validators.required, Validators.minLength(2)]),
    genre: new FormControl('',
      [Validators.required, Validators.minLength(2)])
  });

  constructor(private movieService: MovieService,  private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  // saveMovie(name: string, duration: string, genre: string) {
  //   console.log("We will add a new movie ", name, duration, genre);
  //
  //   // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
  //   const movie: Movie = <Movie>{name, duration: +duration, genre};
  //   this.movieService.saveMovie(movie)
  //     .subscribe(movie => console.log("saved movie: ", movie));
  //
  //   this.back();
  // }

  saveMovie(): void {
      // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
      if (this.movieName != null && this.duration != null && this.genre != null) {
        const name: string = this.movieName.value;
        const duration: string = this.duration.value;
        const genre: string = this.genre.value;

        const movie: Movie = {name, duration: +duration, genre} as Movie;
        this.movieService.saveMovie(movie)
          .subscribe(newMovie => console.log('saved movie: ', newMovie));
      }
      this.back();
    }

  get movieName(): AbstractControl | null {
      return this.addMovieForm.get('movieName');
  }

  get duration(): AbstractControl | null {
    return this.addMovieForm.get('duration');
  }

  get genre(): AbstractControl | null {
    return this.addMovieForm.get('genre');
  }

    back(): void{
    this.movieService.getMovies().subscribe();
    this.location.back();
  }

}
