import { Component, OnInit } from '@angular/core';
import {Cinema} from '../shared/cinema.model';
import {CinemaService} from '../shared/cinema.service';

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.css']
})
export class CinemaListComponent implements OnInit {
  cinemas: Cinema[] | undefined;

  constructor(private cinemaService: CinemaService) { }

  ngOnInit(): void {
    this.cinemaService.getCinemas()
      .subscribe(cinemas => this.cinemas = cinemas.cinemas);
  }

}
