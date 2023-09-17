import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Phone } from 'src/app/model/phone.model';

@Component({
  selector: 'app-phone-card',
  templateUrl: './phone-card.component.html',
  styleUrls: ['./phone-card.component.css']
})
export class PhoneCardComponent {




  @Input()
  phone: Phone;


  @Output()
  deleteEntityPhoneEvent: EventEmitter<string> = new EventEmitter<string>();



  fireDeleteEntityPhoneEvent(){
    this.deleteEntityPhoneEvent.emit();
  }




}
