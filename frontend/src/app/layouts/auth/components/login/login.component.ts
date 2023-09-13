import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/model/login.model';
import { AuthService } from 'src/app/service/auth.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  isLoading: boolean = false;
  errorMessage: string;



  constructor(private toastr: ToastrService ,private authservise: AuthService, private route: Router) { }
  @ViewChild('loginForm') loginform: NgForm;

  onSubmit() {
    this.isLoading = true;

    this.authservise.login(new Login(this.loginform.value.username, this.loginform.value.password))
      .subscribe( {
        next:(response)=>{
          this.isLoading = false;
          this.toastr.success('Welcome '+response.data.userData.fullName);

          
          localStorage.setItem('accessToken', response.data.accessToken);
          localStorage.setItem('refreshToken', response.data.refreshToken);
          localStorage.setItem('user',JSON.stringify(response.data.userData, null, 2) );
          
          this.route.navigate(['/user/dashboard']);
        },
        error:(error)=>{
          this.isLoading = false;

          this.toastr.error(error.error.message);
        }
      }
      );

  }
}
