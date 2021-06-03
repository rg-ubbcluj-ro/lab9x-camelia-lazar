import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {TicketsWrapper} from './tickets-wrapper';
import {Ticket} from './ticket.model';
import {catchError, tap} from 'rxjs/operators';
import {MessageService} from '../../movies/shared/message.service';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private tickets = 'http://localhost:4567/api/tickets';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient, private messageService: MessageService) {
  }

  saveTicket(ticket: Ticket): Observable<Ticket> {
    return this.httpClient
      .post<Ticket>(this.tickets, ticket);
  }

  deleteTicket(id: number): Observable<Ticket> {
    const url = `${this.tickets}/${id}`;
    return this.httpClient
      .delete<Ticket>(url, this.httpOptions).pipe(
        tap(_ => this.log(`deleted ticket id=${id}`)),
        catchError(this.handleError<Ticket>('deleteTicket'))
      );
  }

  updateTicket(id: number, ticket: Ticket): Observable<Ticket> {
    const url = `${this.tickets}/${id}`;
    return this.httpClient
      .put<Ticket>(url, ticket);
  }

  getTickets(): Observable<TicketsWrapper> {
    return this.httpClient
      .get<TicketsWrapper>(this.tickets);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      //
      // // TODO: send the error to remote logging infrastructure
      // console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${"The id doesn't exist"}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`Ticket: ${message}`);
  }
}
