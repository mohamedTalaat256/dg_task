import { Component, Input } from '@angular/core';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-side-navbar',
  templateUrl: './side-navbar.component.html',
  styleUrls: ['./side-navbar.component.css']
})
export class SideNavbarComponent {
  public entitiesIsCollapsed = false;
  public directorsIsCollapsed = false;



  @Input()

  isOpen: boolean = true;
}
