import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Display} from '../../shared/display.model';
import {Media} from '../../shared/media.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://vm141.htl-leonding.ac.at:8080/api/';
  public msg = '';

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

  async submitVideo(displayId: number, mediaId: number): Promise<HttpErrorResponse>{
    console.log(this.apiUrl + 'schedule/' + displayId + '/' +  mediaId);
    console.log('submit Video');
    let data: any;
    data = this.http.post<object>(this.apiUrl + 'schedule/' + displayId + '/' + mediaId, null)
      .subscribe(
        (response) => {
          console.log('response received');
          data = response;
        },
        (error) => {
          console.log('error caught');
          data = error;
        }
      );

    await this.delay(800);

    return data;
  }

  delay(ms: number): Promise<object> {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
