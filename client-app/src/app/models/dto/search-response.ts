export class SearchResponse<T> {
  totalPages: number;
  totalElements: number;
  content: Array<T>;
}
