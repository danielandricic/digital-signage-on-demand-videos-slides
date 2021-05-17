import { Component } from '@angular/core';
import {Layout} from './shared/layout.model';
import {ApiService} from './core/services/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-client';
  layouts: Layout[] = [];

  constructor(private apiservice: ApiService) {
  }

  fillLayouts(): void{
    this.apiservice.getAllLayouts()
      .subscribe( layouts => this.layouts = layouts);
  }
}
