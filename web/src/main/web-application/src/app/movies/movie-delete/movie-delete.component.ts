import { Component, OnInit } from '@angular/core';
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-movie-delete',
  templateUrl: './movie-delete.component.html',
  styleUrls: ['./movie-delete.component.css']
})
export class MovieDeleteComponent implements OnInit {
  private idNumber: number | undefined;

  constructor(private movieService: MovieService,  private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  deleteMovie(id: string) {
    console.log("We will delete a  movie ", id);
    this.idNumber = +id;
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    this.movieService.deleteMovie(this.idNumber)
      .subscribe(result => console.log("deleted movie: ", result));
  }

}
