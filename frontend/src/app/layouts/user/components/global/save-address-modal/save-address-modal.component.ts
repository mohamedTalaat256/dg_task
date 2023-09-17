import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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
  address: Address;

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


  @Input()
  modelName: string='';


  @Output()
  saveAddressToDirectorEvent: EventEmitter<Address> = new EventEmitter<Address>();

  @Output()
  saveAddressToEntityEvent: EventEmitter<Address> = new EventEmitter<Address>();


  openSaveAddressModal(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }




  saveAddress(saveAddressForm: NgForm) {

    this.address = new Address(
      saveAddressForm.value.addressType,
      saveAddressForm.value.address,
      saveAddressForm.value.town,
      saveAddressForm.value.city,
      saveAddressForm.value.zip,
      saveAddressForm.value.countryCode,
      saveAddressForm.value.state,
      saveAddressForm.value.comments
    );










    
    if(this.modelName === 'entity'){

      this.saveAddressToEntityEvent.emit(this.address);

    }else if(this.modelName === 'director'){
      this.saveAddressToDirectorEvent.emit(this.address);
    }

    

      this.modalService.dismissAll();  
  }
}