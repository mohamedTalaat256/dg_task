import { Component, EventEmitter, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Address } from 'src/app/model/address.model';
import { Phone } from 'src/app/model/phone.model';
import { EntityService } from 'src/app/service/entity.service';
import { SelectOprionService } from 'src/app/service/selectOprion.service';
import { Pair } from 'src/app/model/pair.model';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Params } from '@angular/router';
import { AppResponse } from 'src/app/utils/appResponse';
import { Subscription } from 'rxjs';
import { Person } from 'src/app/model/person.model';
import { DirectorService } from 'src/app/service/director.service';
import { DirectorSaveRequest } from 'src/app/request/DirectorSaveRequest';
import { PassportNumber } from 'src/app/model/passportNumber.model';
import { Email } from 'src/app/model/email.model';
import { Entity } from 'src/app/model/entity.model';

@Component({
  selector: 'save-director',
  templateUrl: './save-director.component.html',
  styleUrls: ['./save-director.component.css']
})
export class SaveDirectorComponent implements OnInit, OnDestroy {
  [x: string]: any;


  title: string;
  componentMode: string;
  directorId: number;
  director: Person = new Person(null, null, null, null, null, null, new PassportNumber(null, null), new Email(null) , [], [],null);
  paramSubscription: Subscription;
  payload: DirectorSaveRequest;
  isLoading: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private selectOptionsService: SelectOprionService,
    private directorService: DirectorService,
    private entityService: EntityService
    ) { }

  @ViewChild('saveDirectorForm') saveDirectorForm: NgForm;

  genders: Pair[] = [];
  countriesCodes: Pair[] = [];
  entities: Entity[];

  ngOnInit(): void {

    this.paramSubscription = this.route.queryParams.subscribe(
      (qParams: Params) => {
        if (qParams['mode'] === 'create') {
          this.componentMode = 'create';
          this.title = 'Create New Director';
          
        } else if( qParams['mode'] === 'edit') {

          this.componentMode = 'edit';
          this.title = 'Update Director Entity';
          this.directorId = qParams['directorId'];


          this.directorService.findById(this.directorId).subscribe({
            next: (response: AppResponse) => {
              this.director = response.data;
            }
          });
        }
      }
    );

    this.getGenders();
    this.getCountriesCodes();
    this.getEntities();
  }


  getGenders() {
    this.selectOptionsService.getGenders().subscribe(response => {
      this.genders = response.data;
    })
  }

  getCountriesCodes() {
    this.selectOptionsService.getCountriesCodes().subscribe(response => {
      this.countriesCodes = response.data;
    })
  }

  getEntities(){
    this.entityService.selectNameFromEntity().subscribe(response => {
      this.entities = response.data;
    })
  }

  onSubmit() {
    this.isLoading = true;

    //console.log(this.director);

    this.payload = new DirectorSaveRequest(
      this.director.gender,
      this.director.title,
      this.director.firstName,
      this.director.lastName,
      this.director.ssn,
      this.director.passportNumber,
      this.director.email,
      this.director.phones,
      this.director.addresses,
      this.director.entityId
    );


    if (this.componentMode === 'create') {

      this.saveDirector(this.payload);
    } else {
      this.updateDirector(this.payload);

    }
  }

  updateDirector(request: DirectorSaveRequest) {


    this.directorService.update(this.director.id, request).subscribe(
      {
        next: (response: AppResponse) => {
          this.isLoading = false;
          this.toastr.success(response.message);
        },
        error: (error: Error) => {
          this.toastr.error(error.message);

        }
      });
  }

  saveDirector(request: DirectorSaveRequest) {
    this.directorService.save(request)
      .subscribe(
        {
          next: (response: AppResponse) => {
            this.isLoading = false;
            this.toastr.success(response.message);
          },
          error: (error: Error) => {
            this.toastr.error(error.message);

          }
        });
  }

  savePhone(phone: Phone) {

    this.director.phones.push(phone);
  }

  saveAddress(address: Address) {
    this.director.addresses.push(address);
  }

  ngOnDestroy(): void {
    this.paramSubscription.unsubscribe();
  }

  deletePhone(phoneNumber: any) {
    this.director.phones = this.director.phones.filter(phone => phone.tphNumber !== phoneNumber);
  }

  deleteAddress(address: string, city: string) {
    this.director.addresses = this.director.addresses.filter(i => i.address !== address && i.city !== city);
  }

}
