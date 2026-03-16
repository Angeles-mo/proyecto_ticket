import { User } from "./user.model";
import { Artist } from "./artist.model";
import { Rol } from "./enums.model";

export interface Account {
  id?: number;
  email: string;
  password: string;
  role: Rol;
  user?: User;
  artist?: Artist;
}