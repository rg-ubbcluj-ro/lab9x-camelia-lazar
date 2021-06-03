import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from './client.model';

@Injectable()
export class ClientService {
  private clientsUrl = 'http://localhost:4567/api/clients';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<Array<Client>> {
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl);
  }

  sortByAge(): Promise<Array<Client> | undefined> {
    return new Promise((Resolve, Reject) => {
      this.httpClient
        .get<Array<Client>>(this.clientsUrl)
        .subscribe((result: Array<Client> ) => {

          // arrayClients.sort((a, b): number => {
          //   if (a.age !== undefined && b.age !== undefined) {
          //     return a.age - b.age;
          //   }
          //   return 0;
          // });

          result.sort((a, b): number => {
            if (a.firstname !== undefined && b.lastname !== undefined) {
              return a.lastname < b.lastname ? -1 : 1;
            }
            return 0;
          });
          // sort here
          // clients.sort((client1, client2))
          Resolve(result);
        });
    });
  }


  filterByAge(age: number): Promise<Array<Client> | undefined> {
    return new Promise((Resolve, Reject) => {
      this.httpClient
        .get<Array<Client>>(this.clientsUrl)
        .subscribe((result: Array<Client> ) => {
          result = result.filter(client => {
              if (client.age !== undefined) {
                return client.age <= age;
              }
              return 0;
            }
          );
          // sort here
          // clients.sort((client1, client2))
          Resolve(result);
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
