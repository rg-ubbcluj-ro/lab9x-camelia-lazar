import { Component, OnInit } from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';

@Component({
  selector: 'app-client-sort',
  templateUrl: './client-sort.component.html',
  styleUrls: ['./client-sort.component.css']
})
export class ClientSortComponent implements OnInit {
  clients: Client[] | undefined;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
  }

  async sortCinemaByName(): Promise<void> {
    console.log('We will sort by name');
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    this.clients = await this.clientService.sortByAge();
  }

}
