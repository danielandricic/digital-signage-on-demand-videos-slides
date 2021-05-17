import { Display } from './display.model';
import { Region } from './region.model';

export interface Layout {
  layoutId: number;
  publishedStatusId: 1 | 2;
  layout: string;
  regions: Region[];
  displays: Display[];
}
