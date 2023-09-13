import { Component, OnInit } from '@angular/core';
import { Entity } from 'src/app/model/entity.model';
import { EntityService } from 'src/app/service/entity.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'all-entities',
  templateUrl: './all-entities.component.html',
  styleUrls: ['./all-entities.component.css']
})
export class AllEntitiesComponent implements OnInit {

  searchText: string;


  constructor(
    private toastr: ToastrService,
    private entityService: EntityService) { }


  pageNum: number = 0;
  pageSize: number = 1;


  totalPages: number = 0;

  entities: Entity[];
  ngOnInit(): void {
    console.log('ngOnInit');
    this.getEntities(this.pageNum, this.pageSize);
  }

  getEntities(pNum: number, pSize: number) {
    this.entityService.getAll(pNum, pSize).subscribe(
      response => {
        this.entities = response.content;
        this.totalPages = response.totalPages;
      }
    );
  }


  searchEntities() {
    if(this.searchText==''){
      this.getEntities(this.pageNum, this.pageSize);
    }
    this.entityService.searchEntity(this.searchText).subscribe(
      response => {
        this.entities = response.data;
      }
    );
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
