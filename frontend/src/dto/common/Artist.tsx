import { Identifier } from "./Identifier";

export interface Artist {
  id: number
  displayName: string
  uri: string
  identifier: Identifier[]
}