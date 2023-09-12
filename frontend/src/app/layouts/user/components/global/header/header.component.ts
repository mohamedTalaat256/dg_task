import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {


  isOpen: boolean = true;


  @Output()
  toggleSideNavEvent: EventEmitter<boolean> = new EventEmitter<boolean>();

  toggleSideNav(){
    this.isOpen = !this.isOpen;
    this.toggleSideNavEvent.emit();
  }
}
