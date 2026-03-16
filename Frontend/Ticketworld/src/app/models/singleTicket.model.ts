import { TicketType } from "./ticketType.model";
import { State } from "./enums.model";

export interface SingleTicket {
  id?: number;
  ticketType: TicketType;
  row: number;
  seatNumber: number;
  ticketCode: string;
  ticketPrice: number;
  state?: State;
}