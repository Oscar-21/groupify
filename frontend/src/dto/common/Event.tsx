import { Start } from "./Start";
import { Venue } from "./Venue";

export default interface Event {
  id: number
  displayName: string
  type: string
  uri: string
  status: string
  popularity: number
  start: Start
  performance: Performance[]
  flaggedAsEnded: boolean
  venue: Venue
  location: Location
}