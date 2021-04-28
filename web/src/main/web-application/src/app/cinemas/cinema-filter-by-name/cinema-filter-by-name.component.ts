import { Component, OnInit } from '@angular/core';
import {Cinema} from "../shared/cinema.model";
import {CinemaService} from "../shared/cinema.service";
import {CinemasWrapper} from "../shared/cinemas-wrapper";

@Component({
  selector: 'app-cinema-filter-by-name',
  templateUrl: './cinema-filter-by-name.component.html',
  styleUrls: ['./cinema-filter-by-name.component.css']
})
export class CinemaFilterByNameComponent implements OnInit {
  cinemas: Cinema[] | undefined;


  constructor(private cinemaService: CinemaService) { }

  ngOnInit(): void {
  }

  filterCinemaByName(name: string) {
    console.log("We will filter by name", name);
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    this.cinemaService.filterByNameOnServerSide(name)
      .subscribe((result: CinemasWrapper) => {this.cinemas = result.cinemas; });
  }

}
