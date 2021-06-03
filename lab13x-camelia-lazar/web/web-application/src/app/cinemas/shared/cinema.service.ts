import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {CinemasWrapper} from "./cinemas-wrapper";
import {Cinema} from "./cinema.model";
import {Client} from '../../clients/shared/client.model';

@Injectable()
export class CinemaService {
  private cinemasUrl = 'http://localhost:4567/api/cinemas';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) {
  }

  getCinemas(): Observable<Array<Cinema>> {
    return this.httpClient
      .get<Array<Cinema>>(this.cinemasUrl);
  }

  // filterByNameOnServerSide(name: string): Observable<CinemasWrapper> {
  //  let url: string;
  //  url = 'http://localhost:8080/api/cinemas/filterByName';
  //
  //  return this.httpClient
  //     .post<CinemasWrapper>(url, name);
  // }
  //
  // sortByNameServerSide(): Observable<CinemasWrapper> {
  //   let url: string;
  //   url = 'http://localhost:8080/api/cinemas/sortByName';
  //
  //   return this.httpClient
  //     .get<CinemasWrapper>(url);
  // }

}
