import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Pair } from 'src/app/model/pair.model';
import { Phone } from 'src/app/model/phone.model';
import { SelectOprionService } from 'src/app/service/selectOprion.service';
@Component({
  selector: 'save-phone-modal',
  templateUrl: './save-phone-modal.component.html',
  styleUrls: ['./save-phone-modal.component.css']
})
export class SavePhoneModalComponent implements OnInit{

  constructor(private selectOptionsService: SelectOprionService, private modalService: NgbModal) { }
  
  
  contactTypes: Pair[] = [];
  communicationType: Pair[] = [];

  ngOnInit(): void {
    this.getContactType();
    this.getComunicationTypes();
  }

  getContactType(){
    this.selectOptionsService.getContactType().subscribe(response=>{
      this.contactTypes = response.data;
    })
  }

  getComunicationTypes(){
    this.selectOptionsService.getComunicationTypes().subscribe(response=>{
      this.communicationType = response.data;
    })
  }


  @Output()
  savePhoneToEntityEvent: EventEmitter<Phone> = new EventEmitter<Phone>();


  openSavePhoneModal(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  } 
 
  savePhone(savePhoneForm: NgForm) {

    this.savePhoneToEntityEvent.emit(
      new Phone(
        savePhoneForm.value.tphContactType,
        savePhoneForm.value.tphCommunicationType,
        savePhoneForm.value.tphCountryPrefix,
        savePhoneForm.value.tphNumber,
        savePhoneForm.value.tphExtension,
        savePhoneForm.value.comments,
      ));

      this.modalService.dismissAll();  
  }



}
