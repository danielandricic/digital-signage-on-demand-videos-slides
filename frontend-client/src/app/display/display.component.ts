import { Component, OnInit } from '@angular/core';
import {ApiService} from '../core/services/api.service';
import {Display} from '../shared/display.model';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.scss']
})
export class DisplayComponent implements OnInit {
  displays: Display[] = Array();

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getDisplays().subscribe(
      displays => this.displays = displays
    );
  }
}
