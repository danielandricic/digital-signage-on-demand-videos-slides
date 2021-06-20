import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
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

  async submitVideo(displayId: number, mediaId: number): Promise<object>{
    console.log(this.apiUrl + 'schedule/' + displayId + '/' +  mediaId);
    let data: any;
    try {
      data = this.http.post<object>(this.apiUrl + 'schedule/' + displayId + '/' +  mediaId, null)
        .toPromise();
    }
    catch (err) {
      console.log(err.status);
      this.msg = err.message;
      console.log(this.msg);
    }
    console.log(data);
    console.log(this.msg);

    // @ts-ignore
    return data;
  }
}
