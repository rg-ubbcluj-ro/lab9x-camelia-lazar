import {Trailer} from './trailer.model';

export class Movie {
  id: number | undefined;
  name: string | undefined;
  duration: number | undefined;
  genre: string | undefined;
  trailer: Trailer | undefined;
}
