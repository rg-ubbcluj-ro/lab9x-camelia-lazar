import { Component, OnInit } from '@angular/core';
import {Movie} from '../shared/movie.model';
import {Client} from '../shared/client.model';
import {Ticket} from '../shared/ticket.model';
import {TicketService} from '../shared/ticket.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-ticket-update',
  templateUrl: './ticket-update.component.html',
  styleUrls: ['./ticket-update.component.css']
})
export class TicketUpdateComponent implements OnInit {

  constructor(private ticketService: TicketService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit(): void {
  }

  updateTicket(id: string, price: string, movie: string, client: string): void {
    console.log('We will update the ticket ', name, price, movie, client);

    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    const movieEntity: Movie = {id: +movie} as Movie;
    const clientEntity: Client = {id: +client} as Client;

    const ticket: Ticket = {id: +id, price: +price, movie: movieEntity, client: clientEntity} as Ticket;
    this.ticketService.updateTicket(+id, ticket)
      .subscribe(ticket => console.log('saved ticket: ', ticket));

    this.back();
  }

  back(): void{
    this.ticketService.getTickets().subscribe();
    this.location.back();
  }

}
