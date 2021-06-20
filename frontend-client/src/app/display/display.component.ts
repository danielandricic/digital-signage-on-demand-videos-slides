import { Component, OnInit } from '@angular/core';
import {ApiService} from '../core/services/api.service';
import {Display} from '../shared/display.model';
import {Media} from '../shared/media.model';
import {FormControl, Validators} from '@angular/forms';
import {ProgressSpinnerMode} from '@angular/material/progress-spinner';
import {MatSnackBar} from '@angular/material/snack-bar';

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
  spinning: ProgressSpinnerMode = 'determinate';

  constructor(private apiService: ApiService, private snackBar: MatSnackBar) { }

  async ngOnInit(): Promise<void> {
    this.displays = await this.apiService.getDisplays();
    this.videos = await this.apiService.getVideos();
  }

  async submit(): Promise<void> {
    console.log('Display with ID: ' + this.selectedDisplay.toString() + ' selected Video with ID: ' + this.selectedVideo.toString());
    this.spinning = 'indeterminate';
    const response = await this.apiService.submitVideo(this.selectedDisplay, this.selectedVideo);
    console.log(response.toString());
    this.spinning = 'determinate';
    this.openSnackBar(this.apiService.msg, 'OK');
  }

  openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action);
  }
}
