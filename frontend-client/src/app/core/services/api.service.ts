import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Layout} from '../../shared/layout.model';
import {Display} from '../../shared/display.model';
// import { Layout } from 'src/app/shared/models/layout.model';
// import { Media } from 'src/app/shared/models/media.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {
  }

  getDisplays(): Observable<Display[]> {
    return this.http.get<Display[]>('display');
  }
}
