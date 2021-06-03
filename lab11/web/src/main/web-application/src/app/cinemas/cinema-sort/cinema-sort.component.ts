import { Component, OnInit } from '@angular/core';
import {Cinema} from '../shared/cinema.model';
import {CinemasWrapper} from '../shared/cinemas-wrapper';
import {CinemaService} from '../shared/cinema.service';

@Component({
  selector: 'app-cinema-sort',
  templateUrl: './cinema-sort.component.html',
  styleUrls: ['./cinema-sort.component.css']
})
export class CinemaSortComponent implements OnInit {
  cinemas: Cinema[] | undefined;

  constructor(private cinemaService: CinemaService) { }

  ngOnInit(): void {
  }

  sortCinemaByName(): void {
    console.log('We will sort by name');
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    this.cinemaService.sortByNameServerSide()
      .subscribe((result: CinemasWrapper) => {this.cinemas = result.cinemas; });
  }

}
