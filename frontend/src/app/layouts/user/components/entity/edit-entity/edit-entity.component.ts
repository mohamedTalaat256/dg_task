import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Entity } from 'src/app/model/entity.model';
import { EntityUpdateRequest } from 'src/app/request/EntityUpdateRequest';
import { EntityService } from 'src/app/service/entity.service';

@Component({
  selector: 'app-edit-entity',
  templateUrl: './edit-entity.component.html',
  styleUrls: ['./edit-entity.component.css']
})
export class EditEntityComponent implements OnInit {
  id: number;
  payload: EntityUpdateRequest;
  isLoading: boolean = false;
  hasError: boolean = false;
  hasSuucess: boolean = false;
  message: string;


  entity: Entity;
  
  constructor(private activatedroute:ActivatedRoute,private entityService: EntityService){}

  ngOnInit(): void {
    this.getEntityDetails(this.activatedroute.snapshot.paramMap.get("id"));
  }

  getEntityDetails(entityId :any){
    
   this.entityService.findById(entityId).subscribe((response)=>{
   
    this.entity = response.data;
   });
    
  }


  @ViewChild('updateEntityForm') updateEntityForm: NgForm;


  onSubmit(){
    this.isLoading = true;


    this.payload = new EntityUpdateRequest(
      this.updateEntityForm.value.name,
      this.updateEntityForm.value.commercialName,
      this.updateEntityForm.value.business
    );





  this.entityService.update(this.entity.id, this.payload)
      .subscribe((response:any) => {
        if(!response.message){
          this.isLoading = false;
          this.hasError = true;
        }else{
          this.hasSuucess = true;
          this.isLoading = false;
        }
        this.message = response.message;
      });
  }
}
