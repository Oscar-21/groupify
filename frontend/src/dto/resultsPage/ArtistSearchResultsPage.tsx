import { ArtistSearchResults } from '../results/ArtistSearchResults';

export interface ArtistSearchResultsPage {
  status: String
  results: ArtistSearchResults
  perPage: number
  page: number
  totalEntries: number
}