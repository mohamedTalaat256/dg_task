import { Component, OnInit } from '@angular/core';
import { Entity } from 'src/app/model/entity.model';
import { EntityService } from 'src/app/service/entity.service';
import { ToastrService } from 'ngx-toastr';
import { AppResponse } from 'src/app/utils/appResponse';
import { DirectorService } from 'src/app/service/director.service';
import { Person } from 'src/app/model/person.model';

@Component({
  selector: 'all-directors',
  templateUrl: './all-directors.component.html',
  styleUrls: ['./all-directors.component.css']
})
export class AllDirectorsComponent implements OnInit {

  searchText: string;

  searchName: string='';
  searchCommercialName: string='';
  searchPhone: string;


  constructor(
    private toastr: ToastrService,
    private directorService: DirectorService,
    
    ) { }
    
  entities: Entity[];
  directors: Person[];
  
  ngOnInit(): void {
    this.getDirectors();
  }

  getDirectors() {
    this.directorService.getAll().subscribe({
      next:(response: AppResponse)=>{
        console.log(response);
        this.directors = response.data;
        
      },
      error:(error: Error)=>{
        this.toastr.error(error.message);
      }
    }
    );
  }




}
