import { Component, OnInit } from '@angular/core';
import {ApiService} from '../core/services/api.service';
import {Display} from '../shared/display.model';
import {Media} from '../shared/media.model';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.scss']
})
export class DisplayComponent implements OnInit {
  displays: Display[] = [];
  videos: Media[] = [];
  selectedVideo = -1;
  selectedDisplay  = -1;
  formControl = new FormControl('', Validators.required);

  constructor(private apiService: ApiService) { }

  async ngOnInit(): Promise<void> {
    this.displays = await this.apiService.getDisplays();
    this.videos = await this.apiService.getVideos();
  }

  submit(): void {
    console.log(this.selectedDisplay.toString() + ' ' + this.selectedVideo.toString());
    this.apiService.submitVideo(this.selectedDisplay, this.selectedVideo);
  }
}
