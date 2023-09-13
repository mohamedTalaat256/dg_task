import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Address } from 'src/app/model/address.model';
import { Phone } from 'src/app/model/phone.model';
import { EntitySaveRequest } from 'src/app/request/EntitySaveRequest';
import { EntityService } from 'src/app/service/entity.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SelectOprionService } from 'src/app/service/selectOprion.service';
import { Pair } from 'src/app/model/pair.model';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-add-entity',
  templateUrl: './add-entity.component.html',
  styleUrls: ['./add-entity.component.css']
})
export class AddEntityComponent implements OnInit {
  [x: string]: any;


  payload: EntitySaveRequest;
  isLoading: boolean = false;

  constructor(
    private toastr: ToastrService ,
    private selectOptionsService :SelectOprionService,
    private entityService: EntityService, 
    private modalService: NgbModal) { }

  @ViewChild('createEntityForm') createEntityForm: NgForm;


  addresses: Address[] = [];
  phones: Phone[] = [];
  contactTypes: Pair[] = [];
  communicationType: Pair[] = [];
  genders: Pair[] = [];
  countriesCodes: Pair[] = [];



  ngOnInit(): void {
    this.getComunicationTypes();
    this.getGenders();
    this.getCountriesCodes();
    this.getContactType();
  }


  getComunicationTypes(){
    this.selectOptionsService.getComunicationTypes().subscribe(response=>{
      this.communicationType = response.data;
    })
  }

  getGenders(){
    this.selectOptionsService.getGenders().subscribe(response=>{
      this.genders = response.data;
    })
  }

  getCountriesCodes(){
    this.selectOptionsService.getCountriesCodes().subscribe(response=>{
      this.countriesCodes = response.data;
    })
  }

  getContactType(){
    this.selectOptionsService.getContactType().subscribe(response=>{
      this.contactTypes = response.data;
    })
  }

  onSubmit() {
    this.isLoading = true;


    this.payload = new EntitySaveRequest(
      this.createEntityForm.value.name,
      this.createEntityForm.value.commercialName,
      this.createEntityForm.value.incorporationNumber,
      this.createEntityForm.value.business,
      this.phones,
      this.addresses
    );





  this.entityService.save(this.payload)
      .subscribe(
        {
          next:(response)=>{
            this.isLoading = false;
            this.toastr.success(response.message);

          },error:(error)=>{
            this.isLoading = false;
            this.toastr.error(error.error.message);

          }
      });

  }
  

  addPhone(addPhoneForm: NgForm ){
    this.phones.push(new Phone(
      addPhoneForm.value.tphContactType,
      addPhoneForm.value.tphCommunicationType,
      addPhoneForm.value.tphCountryPrefix,
      addPhoneForm.value.tphNumber,
      addPhoneForm.value.tphExtension,
      addPhoneForm.value.comments,
    ));
  }



  addAddress(addAddressForm: NgForm ){
    this.addresses.push(new Address(
      addAddressForm.value.addressType,
      addAddressForm.value.address,
      addAddressForm.value.town,
      addAddressForm.value.city,
      addAddressForm.value.zip,
      addAddressForm.value.countryCode,
      addAddressForm.value.state,
      addAddressForm.value.comments
    ));
  }

  
  deletePhone(phoneNumber: any){
    this.phones = this.phones.filter(phone=> phone.tphNumber !== phoneNumber);
  }

  deleteAddress(address: string, city: string){
    this.addresses = this.addresses.filter(i=> i.address !== address && i.city !== city);
  }

  openModalAddPhone(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }
  openModalAddAddress(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }
}
