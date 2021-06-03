import { Component, OnInit } from '@angular/core';
import {MovieService} from '../../movies/shared/movie.service';
import {Movie} from '../../movies/shared/movie.model';
import {TicketService} from '../shared/ticket.service';
import {Ticket} from '../shared/ticket.model';
import {FormControl} from '@angular/forms';


@Component({
  selector: 'app-tickets-list',
  templateUrl: './tickets-list.component.html',
  styleUrls: ['./tickets-list.component.css']
})
export class TicketsListComponent implements OnInit {
  tickets: Ticket[] = new Array<Ticket>();

  constructor(private ticketService: TicketService) {
  }

  ngOnInit(): void {
    this.ticketService.getTickets()
      .subscribe(tickets => {
        if (tickets.tickets != null) {
          this.tickets = tickets.tickets;
        }
      });
  }

}
