import { Component, OnInit } from '@angular/core';
import { Entity } from 'src/app/model/entity.model';
import { EntityService } from 'src/app/service/entity.service';

@Component({
  selector: 'all-entities',
  templateUrl: './all-entities.component.html',
  styleUrls: ['./all-entities.component.css']
})
export class AllEntitiesComponent implements OnInit {

  hasError: boolean = false;
  hasSuucess: boolean = false;
  message: string='';


  constructor(private entityService: EntityService) { }


  pageNum: number = 0;
  pageSize: number = 5;


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


  deleteEntity(entityId: number) {
    
    if(confirm('are you sure to delete this record')){
      this.entityService.delete(entityId).subscribe(
        response => {
          if(response.success){
            this.hasSuucess = true;
            this.message = response.message;
            this.entities = this.entities.filter(entity=> entity.id !== entityId);
          }else{
            this.hasError = true;
            this.message = 'fail to delete';
          }
        
        }
      );
    }
    
  }


  changePageNum(pNum: number) {
    this.pageNum = pNum;
    this.getEntities(pNum, this.pageSize);
  }

  getPage(pNum) {
    if (pNum <= 0) {
      return;
    }

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
