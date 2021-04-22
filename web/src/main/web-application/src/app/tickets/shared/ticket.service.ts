import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MessageService} from "../../movies/shared/message.service";
import {Observable} from "rxjs";
import {MoviesWrapper} from "../../movies/shared/movies-wrapper";
import {TicketsWrapper} from "./tickets-wrapper";

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private tickets = 'http://localhost:8080/api/tickets';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) {
  }

  getTickets(): Observable<TicketsWrapper> {
    return this.httpClient
      .get<TicketsWrapper>(this.tickets);
  }
}
