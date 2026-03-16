import { Place } from "./place.model";
import { Artist } from "./artist.model";
import { TicketType } from "./ticketType.model";
import { Status } from "./enums.model";

export interface Event {
  id?: number;
  place: Place;
  title: string;
  description: string;
  dateStartTime: string; // ISO date string
  dateEndTime: string;   // ISO date string
  status: Status;
  artists?: Artist[];
  ticketTypes?: TicketType[];
}