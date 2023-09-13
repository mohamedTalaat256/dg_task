import { Component, Input, OnInit } from '@angular/core';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-side-navbar',
  templateUrl: './side-navbar.component.html',
  styleUrls: ['./side-navbar.component.css']
})
export class SideNavbarComponent implements OnInit {
  public entitiesIsCollapsed = false;
  public directorsIsCollapsed = false;
  
  loggedinUser :User;
  constructor( private authService: AuthService){}

  @Input()

  isOpen: boolean = true;





  ngOnInit(): void {
    this.loggedinUser = this.authService.getLogedIngUser();
  }
}
