import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {Display} from '../../shared/display.model';
import {Media} from '../../shared/media.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {
  }

  async getDisplays(): Promise<Display[]> {
    const data = this.http.get<Display[]>(this.apiUrl + 'display').toPromise();
    console.log(data);
    return data;
  }

  async getVideos(): Promise<Media[]> {
    const data = this.http.get<Media[]>(this.apiUrl + 'video').toPromise();
    console.log(data);
    return data;
  }

  async submitVideo(displayId: number, mediaId: number): Promise<object>{
    let msg = '';
    console.log(this.apiUrl + 'schedule/' + displayId + '/' +  mediaId);
    const data = this.http.post(this.apiUrl + 'schedule/' + displayId + '/' +  mediaId, null)
      .toPromise()
      .then()
      .catch(err => {
        msg = err.error.message;
      });
    console.log(msg);
    console.log(data);
    // @ts-ignore
    return data;
  }
}
