import { Country } from "./Country";
import { State } from "./State";

export interface MetroArea {
  displayName: string
  country: Country
  state: State
  id: number
  uri: string
}