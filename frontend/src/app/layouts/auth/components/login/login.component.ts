import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/model/login.model';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  isLoading: boolean = false;
  hasError: boolean = false;
  errorMessage: string;



  constructor(private authservise: AuthService, private route: Router) { }
  @ViewChild('loginForm') loginform: NgForm;

  onSubmit() {
    this.isLoading = true;

    this.authservise.login(new Login(this.loginform.value.username, this.loginform.value.password))
      .subscribe(response => {

        this.isLoading = false;

        console.log(response);
        localStorage.setItem('accessToken', response.data.accessToken);
        this.route.navigate(['/user/dashboard']);

      }, error => {
        
        this.isLoading = false;

        this.hasError = true;
        this.errorMessage = error.error.message;
      }
      );

  }
}
