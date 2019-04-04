import { MetroArea } from "./MetroArea";

export interface Venue {
  id: number
  displayName: string
  uri: string
  metroArea: MetroArea
  lat: number
  lng: number
}