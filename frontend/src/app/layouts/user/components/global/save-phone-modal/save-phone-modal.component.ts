import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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
export class SavePhoneModalComponent implements OnInit {

  constructor(private selectOptionsService: SelectOprionService, private modalService: NgbModal) { }


  contactTypes: Pair[] = [];
  communicationType: Pair[] = [];


  phone: Phone;

  ngOnInit(): void {
    this.getContactType();
    this.getComunicationTypes();
  }

  getContactType() {

    if (localStorage.getItem('CONTACT_TYPE') != null) {
      this.contactTypes = JSON.parse(localStorage.getItem('CONTACT_TYPE'));
    } else {
      this.selectOptionsService.getContactType().subscribe(response => {
        this.contactTypes = response.data;
        localStorage.setItem('CONTACT_TYPE', JSON.stringify(this.contactTypes));
      })
    }



  }

  getComunicationTypes() {


    if (localStorage.getItem('COMMUNICATION_TYPE') != null) {
      this.contactTypes = JSON.parse(localStorage.getItem('COMMUNICATION_TYPE'));
    } else {
      this.selectOptionsService.getComunicationTypes().subscribe(response => {
        this.communicationType = response.data;
        localStorage.setItem('COMMUNICATION_TYPE', JSON.stringify(this.communicationType));
  
      })
    }


    
  }



  @Input()
  modelName: string = '';





  @Output()
  savePhoneToEntityEvent: EventEmitter<Phone> = new EventEmitter<Phone>();


  @Output()
  savePhoneToDirectorEvent: EventEmitter<Phone> = new EventEmitter<Phone>();


  openSavePhoneModal(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }

  savePhone(savePhoneForm: NgForm) {


    this.phone = new Phone(
      savePhoneForm.value.tphContactType,
      savePhoneForm.value.tphCommunicationType,
      savePhoneForm.value.tphCountryPrefix,
      savePhoneForm.value.tphNumber,
      savePhoneForm.value.tphExtension,
      savePhoneForm.value.comments,
    )


    if (this.modelName === 'entity') {
      this.savePhoneToEntityEvent.emit(this.phone);
    } else if (this.modelName === 'director') {
      this.savePhoneToDirectorEvent.emit(this.phone);
    }

    this.modalService.dismissAll();
  }
}
