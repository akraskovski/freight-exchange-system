export class SearchRequest {
  page: number;
  size: number;
  text: string;

  constructor(page: number, size: number, text: string) {
    this.page = page;
    this.size = size;
    this.text = text;
  }
}
