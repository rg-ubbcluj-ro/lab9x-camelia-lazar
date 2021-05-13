import {Movie} from './movie.model';
import {Client} from './client.model';

export class Ticket {
  id: number | undefined;
  price: number | undefined;
  movie: Movie = new Movie;
  client: Client = new Client;
}
