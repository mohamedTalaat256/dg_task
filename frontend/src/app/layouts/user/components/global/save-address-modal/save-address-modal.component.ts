import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Address } from 'src/app/model/address.model';
import { Pair } from 'src/app/model/pair.model';
import { SelectOprionService } from 'src/app/service/selectOprion.service';

@Component({
  selector: 'save-address-modal',
  templateUrl: './save-address-modal.component.html',
  styleUrls: ['./save-address-modal.component.css']
})
export class SaveAddressModalComponent implements OnInit {

  constructor(private selectOptionsService: SelectOprionService, private modalService: NgbModal) { }
  
  
  countriesCodes: Pair[] = [];
  communicationType: Pair[] = [];

  ngOnInit(): void {
    this.getCountriesCodes();
    this.getComunicationTypes();
  }
  

  getComunicationTypes(){
    this.selectOptionsService.getComunicationTypes().subscribe(response=>{
      this.communicationType = response.data;
    })
  }
  getCountriesCodes(){
    this.selectOptionsService.getCountriesCodes().subscribe(response=>{
      this.countriesCodes = response.data;
    })
  }


  @Output()
  saveAddressToEntityEvent: EventEmitter<Address> = new EventEmitter<Address>();


  openSaveAddressModal(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }




  saveAddress(saveAddressForm: NgForm) {

    this.saveAddressToEntityEvent.emit(
      new Address(
        saveAddressForm.value.addressType,
        saveAddressForm.value.address,
        saveAddressForm.value.town,
        saveAddressForm.value.city,
        saveAddressForm.value.zip,
        saveAddressForm.value.countryCode,
        saveAddressForm.value.state,
        saveAddressForm.value.comments
  
      ));

      this.modalService.dismissAll();  
  }
}