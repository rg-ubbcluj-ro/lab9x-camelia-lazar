import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClientsWrapper} from './clients-wrapper';
import {Client} from './client.model';

@Injectable()
export class ClientService {
  private clientsUrl = 'http://localhost:8080/api/clients';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<ClientsWrapper> {
    return this.httpClient
      .get<ClientsWrapper>(this.clientsUrl);
  }

  sortByAge(): Promise<Array<Client> | undefined> {
    return new Promise((Resolve, Reject) => {
      this.httpClient
        .get<ClientsWrapper>(this.clientsUrl)
        .subscribe((result: ClientsWrapper ) => {

          const arrayClients: Array<Client> = result.clients as Array<Client>;

          // arrayClients.sort((a, b): number => {
          //   if (a.age !== undefined && b.age !== undefined) {
          //     return a.age - b.age;
          //   }
          //   return 0;
          // });

          arrayClients.sort((a, b): number => {
            if (a.firstName !== undefined && b.lastName !== undefined) {
              return a.lastName < b.lastName ? -1 : 1;
            }
            return 0;
          });
          // sort here
          // clients.sort((client1, client2))
          Resolve(arrayClients);
        });
    });
  }


  filterByAge(age: number): Promise<Array<Client> | undefined> {
    return new Promise((Resolve, Reject) => {
      this.httpClient
        .get<ClientsWrapper>(this.clientsUrl)
        .subscribe((result: ClientsWrapper ) => {

          let arrayClients: Array<Client> = result.clients as Array<Client>;

          // arrayClients.sort((a): number => {
          //   if (a.age !== undefined && b.age !== undefined) {
          //     return a.age - b.age;
          //   }
          //   return 0;
          // });
          arrayClients = arrayClients.filter(client => {
              if (client.age !== undefined) {
                return client.age <= age;
              }
              return 0;
            }
          );
          // sort here
          // clients.sort((client1, client2))
          Resolve(arrayClients);
        });
    });
  }

  // filterByNameOnServerSide(name: string): Observable<ClientsWrapper> {
  //   let url: string;
  //   url = 'http://localhost:8080/api/clients/filterByName';
  //
  //   return this.httpClient
  //     .post<ClientsWrapper>(url, name);
  // }
  //
  // sortByNameServerSide(): Observable<ClientsWrapper> {
  //   let url: string;
  //   url = 'http://localhost:8080/api/clients/sortByName';
  //
  //   return this.httpClient
  //     .get<ClientsWrapper>(url);
  // }

}
