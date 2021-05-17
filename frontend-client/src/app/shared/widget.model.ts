import { WidgetOption } from './widget-option.model';

export interface Widget {
  widgetId: number;
  playlistId: number;
  type: string;
  customType?: string;
  duration: number;
  fromDt: number;
  toDt: number;
  mediaIds: string[];
  widgetOptions: WidgetOption[];

  // Frontend specific
  widgetIndex?: number;
  valid: boolean;
}
