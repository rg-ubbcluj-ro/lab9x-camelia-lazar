import { Component, OnInit } from '@angular/core';
import {Cinema} from '../shared/cinema.model';
import {CinemaService} from '../shared/cinema.service';
import {CinemasWrapper} from '../shared/cinemas-wrapper';
import {Observable} from 'rxjs';
import {FormControl} from '@angular/forms';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-cinema-filter-by-name',
  templateUrl: './cinema-filter-by-name.component.html',
  styleUrls: ['./cinema-filter-by-name.component.css']
})
export class CinemaFilterByNameComponent implements OnInit {
  cinemas: Cinema[] | undefined;

  myControl = new FormControl();
  filteredOptions: Observable<Cinema[] | undefined> | undefined;

  constructor(private cinemaService: CinemaService) { }

  ngOnInit(): void {
    // this.filteredOptions = this.myControl.valueChanges
    //   .pipe(
    //     startWith(''),
    //     map(value => {
    //       return this.filterCinemaByName(value).pipe(map(v => v));
    //     })
    //   );
    this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => {
          console.log('input changed...');
          this.filteredOptions = this.filterCinemaByName(value).pipe(map(v => v.cinemas));
        })
      );
  }

  filterCinemaByName(name: string) {
    console.log('We will filter by name', name);
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    // return this.cinemaService.filterByNameOnServerSide(name)
    //   .subscribe((result: CinemasWrapper) => {this.cinemas = result.cinemas; });

    return this.cinemaService.filterByNameOnServerSide(name);
  }

  update(): void {
    this.filteredOptions = this.filterCinemaByName(this.myControl.value).pipe(map(v => v.cinemas));
  }
}
