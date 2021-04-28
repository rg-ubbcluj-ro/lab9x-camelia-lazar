import { Component, OnInit } from '@angular/core';
import {ClientService} from '../shared/client.service';
import {Client} from '../shared/client.model';

@Component({
  selector: 'app-client-filter',
  templateUrl: './client-filter.component.html',
  styleUrls: ['./client-filter.component.css']
})
export class ClientFilterComponent implements OnInit {
  clients: Client[] | undefined;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
  }

  async filterClientsByAge(age: string): Promise<void> {
    console.log('We will filter by age');
    // const discipline: Discipline = {id: 0, title, teacher, credits: +credits};
    this.clients = await this.clientService.filterByAge(+age);
  }

}
