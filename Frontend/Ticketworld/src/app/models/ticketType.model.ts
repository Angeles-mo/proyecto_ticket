import { Event } from "./event.model";
import { SingleTicket } from "./singleTicket.model";

export interface TicketType {
  id?: number;
  event: Event;
  name: string;
  price: number;
  capacity: number;
  singleTickets?: SingleTicket[];
}