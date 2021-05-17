import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Layout} from '../../shared/layout.model';
// import { Layout } from 'src/app/shared/models/layout.model';
// import { Media } from 'src/app/shared/models/media.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://vm141.htl-leonding.ac.at/api';

  constructor(private http: HttpClient) {
  }

  getAllLayouts(): Observable<Layout[]> {
    return this.http.get<Layout[]>('/layout');
  }
}
