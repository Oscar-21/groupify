import { Artist } from "./Artist";

export interface Performance {
  id: number
  displayName: string
  billing: string
  billingIndex: number
  artist: Artist
}