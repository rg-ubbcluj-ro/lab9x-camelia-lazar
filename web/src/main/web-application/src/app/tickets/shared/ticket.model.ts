import {Movie} from "./movie.model";
import {Client} from "./client.model";

export class Ticket {
  price: number | undefined;
  date: string | undefined;
  time: string | undefined;
  movie: Movie = new Movie;
  client: Client = new Client;
}
