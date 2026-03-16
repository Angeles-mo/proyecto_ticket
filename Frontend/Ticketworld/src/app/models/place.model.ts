import { Event } from "./event.model";

export interface Place {
  id?: number;
  name: string;
  address: string;
  city: string;
  province: string;
  country: string;
  totalCapacity: number;
  seatMap?: string;
  events?: Event[];
}