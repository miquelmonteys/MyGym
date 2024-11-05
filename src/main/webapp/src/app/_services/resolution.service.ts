import {EventEmitter, Injectable, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";


@Injectable({
  providedIn: 'root'
})
export class ResolutionService implements OnInit{

  resol : string = 'L';
  private resize: EventEmitter<any> = new EventEmitter<any>();

  constructor(private responsive: BreakpointObserver) {
    this.responsive.observe(Breakpoints.HandsetPortrait)
      .subscribe(result => {
        if (result.matches) {
          this.resol = "XS";
          this.resize.emit('XS')

        }
      })

    this.responsive.observe(Breakpoints.TabletPortrait)
      .subscribe(result => {
        if (result.matches) {
          this.resol = "S";
          this.resize.emit('S')
        }
      })

    this.responsive.observe(Breakpoints.Medium)
      .subscribe(result => {
        if (result.matches) {
          this.resol = "M";
          this.resize.emit('M')
        }
      })

    this.responsive.observe(Breakpoints.Large)
      .subscribe(result => {
        if (result.matches) {
          this.resol = "L";
          this.resize.emit('L')
        }
      })
  }

  getResizeChange(){
    return this.resize;
  }

  ngOnInit() {

  }




  getResolution() {
    return this.resol;
  }

}
