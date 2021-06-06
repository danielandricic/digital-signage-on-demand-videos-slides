import { Component } from '@angular/core';
import {Layout} from './shared/layout.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-client';
  layouts: Layout[] = [];

  constructor() {
  }
}
