import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {



  isOpen: boolean = true;
  loggedinUser :User;

  constructor(
    private route: Router ,private toastr: ToastrService, private authService: AuthService){}

  
  @Output()
  toggleSideNavEvent: EventEmitter<boolean> = new EventEmitter<boolean>();


  ngOnInit(): void {
    this.loggedinUser = this.authService.getLogedIngUser();
  }



  toggleSideNav(){
    this.isOpen = !this.isOpen;
    this.toggleSideNavEvent.emit();
  }



  logOut(){
    this.authService.logOut().subscribe(
      {
        next:(response)=>{
          localStorage.removeItem('accessToken');
          localStorage.removeItem('refreshToken');
          localStorage.removeItem('user');


          this.route.navigate(['/auth/login']);
          this.toastr.success(response.message);

        },
        error:(error)=>{
          this.toastr.error('fail to logout','', {
            closeButton: true
          });
        }
      }
    );
    
  }
}
