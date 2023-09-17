import { Component, OnInit } from '@angular/core';
import { Entity } from 'src/app/model/entity.model';
import { EntityService } from 'src/app/service/entity.service';
import { ToastrService } from 'ngx-toastr';
import { AppResponse } from 'src/app/utils/appResponse';

@Component({
  selector: 'all-entities',
  templateUrl: './all-entities.component.html',
  styleUrls: ['./all-entities.component.css']
})
export class AllEntitiesComponent implements OnInit {

  searchText: string;

  searchName: string='';
  searchCommercialName: string='';
  searchPhone: string;


  constructor(
    private toastr: ToastrService,
    private entityService: EntityService) { }


  pageNum: number = 0;
  pageSize: number = 4;


  totalPages: number = 0;

  entities: Entity[];
  filterdEntities: Entity[];
  
  ngOnInit(): void {
    this.getEntities(this.pageNum, this.pageSize);
  }

  getEntities(pNum: number, pSize: number) {
    this.entityService.getAll(pNum, pSize).subscribe(
      response => {
        this.entities = response.content;
        this.filterdEntities = response.content;
        this.totalPages = response.totalPages;
      }
    );
  }

  search(){
    this.entityService.searchEntity(
      this.searchName,
      this.searchCommercialName
      
      ).subscribe({
        next:(response: AppResponse)=>{
          this.filterdEntities = response.data;
        },
        error:(error: Error)=>{
          this.toastr.error(error.message);
        }
      }
     
    );
  }


  searchInResultEntities() {
    this.filterdEntities = this.filterdEntities.filter(
      entity=> entity.name.includes(this.searchText));

      if(this.searchText==''){
        this.filterdEntities = this.entities;
      }

      console.log(this.entities);
    /* 
    
    this.entityService.searchEntity(this.searchText).subscribe(
      response => {
        this.entities = response.data;
      }
    ); */
  }


  deleteEntity(entityId: number) {
    if(confirm('are you sure to delete this record')){
      this.entityService.delete(entityId).subscribe(
        response => {
          if(response.success){
            this.toastr.success(response.message);
            this.entities = this.entities.filter(entity=> entity.id !== entityId);
          }else{
            this.toastr.error('fail to delete');
          }
        }
      );
    }
  }

  changePageNum(pNum: number) {
    this.pageNum = pNum;
    this.getEntities(pNum, this.pageSize);
  }
  
  changePageSize(pSize: number) {
    this.pageSize = pSize;
    this.getEntities(this.pageNum, pSize);
  }

  getPage(pNum) {
    if (pNum > this.totalPages) {
      return;
    }
    this.pageNum = pNum;
    this.entityService.getAll(pNum, this.pageSize).subscribe(
      response => {

        this.entities = response.content;
        this.totalPages = response.totalPages;
        console.log('totalPages: ' + this.totalPages);
      }

    );
  }
}
