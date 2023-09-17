import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Address } from 'src/app/model/address.model';

@Component({
  selector: 'app-address-card',
  templateUrl: './address-card.component.html',
  styleUrls: ['./address-card.component.css']
})
export class AddressCardComponent {



  @Input()
  address: Address;


  @Output()
  deleteEntityAddressEvent: EventEmitter<string> = new EventEmitter<string>();



  fireDeleteEntityAddressEvent(){
    this.deleteEntityAddressEvent.emit();
  }
}
