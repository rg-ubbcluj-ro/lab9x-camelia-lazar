import { Component, OnInit } from '@angular/core';
import {MovieService} from "../shared/movie.service";
import {Location} from '@angular/common';
import {Movie} from "../shared/movie.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit {

  constructor(private movieService: MovieService,  private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  saveMovie(name: string, duration: string, genre: string) {
    console.log("We will add a new movie ", name, duration, genre);

    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    const movie: Movie = <Movie>{name, duration: +duration, genre};
    this.movieService.saveMovie(movie)
      .subscribe(movie => console.log("saved movie: ", movie));

    this.back();
  }

  back():void{
    this.movieService.getMovies().subscribe();
    this.location.back();
  }

}
