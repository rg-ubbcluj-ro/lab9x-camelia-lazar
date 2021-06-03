import { Component, OnInit } from '@angular/core';
import {TicketService} from '../shared/ticket.service';
import {ActivatedRoute} from '@angular/router';
import {Ticket} from '../shared/ticket.model';
import {Movie} from '../shared/movie.model';
import {Client} from '../shared/client.model';
import {Location} from "@angular/common";

@Component({
  selector: 'app-ticket-add',
  templateUrl: './ticket-add.component.html',
  styleUrls: ['./ticket-add.component.css']
})
export class TicketAddComponent implements OnInit {

  constructor(private ticketService: TicketService,  private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  saveTicket(price: string, movie: string, client: string): void {
    console.log('We will add a new ticket ', name, price, movie, client);

    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    const movieEntity: Movie = {id: +movie} as Movie;
    const clientEntity: Client = {id: +client} as Client;

    const ticket: Ticket = {price: +price, movie: movieEntity, client: clientEntity} as Ticket;
    this.ticketService.saveTicket(ticket)
      .subscribe(ticket => console.log('saved ticket: ', ticket));

    // this.back();
  }

  back(): void{
    this.ticketService.getTickets().subscribe();
    this.location.back();
  }
}
