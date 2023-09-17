import { Component, EventEmitter, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Address } from 'src/app/model/address.model';
import { Phone } from 'src/app/model/phone.model';
import { EntitySaveRequest } from 'src/app/request/EntitySaveRequest';
import { EntityService } from 'src/app/service/entity.service';
import { SelectOprionService } from 'src/app/service/selectOprion.service';
import { Pair } from 'src/app/model/pair.model';
import { ToastrService } from 'ngx-toastr';
import { Entity } from 'src/app/model/entity.model';
import { ActivatedRoute, Params } from '@angular/router';
import { AppResponse } from 'src/app/utils/appResponse';
import { Subscription } from 'rxjs';

@Component({
  selector: 'save-entity',
  templateUrl: './save-entity.component.html',
  styleUrls: ['./save-entity.component.css']
})
export class SaveEntityComponent implements OnInit , OnDestroy {
  [x: string]: any;


  title: string;
  componentMode: string;
  entityId: number;
  entity: Entity;
  paramSubscription: Subscription;
  payload: EntitySaveRequest;
  isLoading: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService ,
    private selectOptionsService :SelectOprionService,
    private entityService: EntityService) { }

  @ViewChild('createEntityForm') createEntityForm: NgForm;

  genders: Pair[] = [];


  ngOnInit(): void {

    this.paramSubscription = this.route.queryParams.subscribe(
      (qParams: Params)=>{
        if(qParams['mode'] === 'create'){
          this.componentMode = 'create';
          this.title = 'Create New Entity';
          this.entity = new Entity(null, '','','',[],[],[]);
        }else{

          this.componentMode = 'edit';
          this.title = 'Update Entity';
          this.entityId = qParams['entityId'];


          this.entityService.findById(this.entityId).subscribe({
            next:(response: AppResponse)=>{
              this.entity = response.data;
            }
          });
        }
      }
    );

    this.getGenders();
  }


  getGenders(){
    this.selectOptionsService.getGenders().subscribe(response=>{
      this.genders = response.data;
    })
  }
  onSubmit() {
    this.isLoading = true;
    this.payload = new EntitySaveRequest(
      this.entity.name,
      this.entity.commercialName,
      this.entity.business,
      this.entity.phones,
      this.entity.addresses
    );

    
    if(this.componentMode === 'create'){

      this.saveEntity(this.payload);
    }else{
      this.updateEntity(this.payload);

    }
  }




  updateEntity(request: EntitySaveRequest){
   

  this.entityService.update(this.entity.id, request)
      .subscribe(
        {
          next:(response: AppResponse)=>{
            this.isLoading = false;
            this.toastr.success(response.message);
          },
          error:(error: Error)=>{
            this.toastr.error(error.message);

          }
        });
  }

  saveEntity(request: EntitySaveRequest){
    this.entityService.save(request)
    .subscribe(
      {
        next:(response: AppResponse)=>{
          this.isLoading = false;
          this.toastr.success(response.message);
        },
        error:(error: Error)=>{
          this.toastr.error(error.message);

        }
      });
  }
  

  savePhone(phone: Phone){

    console.log
    this.entity.phones.push(phone);
  }

  saveAddress(address: Address){
    this.entity.addresses.push(address);
  }

  





  ngOnDestroy(): void {
    this.paramSubscription.unsubscribe();
  }



  deletePhone(phoneNumber: any){
    this.entity.phones = this.entity.phones.filter(phone=> phone.tphNumber !== phoneNumber);
  }


  deleteAddress(address: string, city: string){
    this.entity.addresses = this.entity.addresses.filter(i=> i.address !== address && i.city !== city);
  }

}
