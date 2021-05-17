import { Playlist } from './playlist.model';

export interface Region {
  regionId: number;
  name: string;
  regionPlaylist: Playlist;
  width: number;
  height: number;
}
