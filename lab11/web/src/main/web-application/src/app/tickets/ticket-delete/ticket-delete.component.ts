import { Component, OnInit } from '@angular/core';
import {TicketService} from '../shared/ticket.service';

@Component({
  selector: 'app-ticket-delete',
  templateUrl: './ticket-delete.component.html',
  styleUrls: ['./ticket-delete.component.css']
})
export class TicketDeleteComponent implements OnInit {
  private idNumber: number | undefined;

  constructor(private ticketService: TicketService) { }

  ngOnInit(): void {
  }

  deleteTicket(id: string): void {
    console.log('We will delete the ticket with id', id);
    this.idNumber = +id;
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    this.ticketService.deleteTicket(this.idNumber)
      .subscribe(result => console.log('deleted ticket: ', result));
  }

}
