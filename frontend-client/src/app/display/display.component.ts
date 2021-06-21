import { Component, OnInit } from '@angular/core';
import {ApiService} from '../core/services/api.service';
import {Display} from '../shared/display.model';
import {Media} from '../shared/media.model';
import {FormControl, Validators} from '@angular/forms';
import {ProgressSpinnerMode} from '@angular/material/progress-spinner';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpErrorResponse} from '@angular/common/http';

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
    console.log(this.spinning);
    const response = await this.apiService.submitVideo(this.selectedDisplay, this.selectedVideo);
    console.log(this.spinning);
    console.log(response.statusText);
    this.spinning = 'determinate';
    this.openSnackBar(response, 'OK');
  }

  openSnackBar(error: HttpErrorResponse, action: string): void {
    this.selectedVideo = -1;
    this.selectedDisplay = -1;
    this.snackBar.open(error.status + ': ' + error.statusText, action);
  }
}
