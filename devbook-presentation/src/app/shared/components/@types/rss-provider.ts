export interface RssProvider {
  id: number;
  link: string;
  url: string;
  title: string;
  description: string;
  imageUrl: string;
  lastUpdate: Date;
}
