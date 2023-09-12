import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Register } from 'src/app/model/register.model';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  isLoading: boolean = false;
  hasError: boolean = false;
  hasSuucess: boolean = false;
  message: string;



  constructor(private authservise: AuthService, private route: Router) { }

  @ViewChild('registerForm') registerForm: NgForm;

  onSubmit(){
    this.isLoading = true;

    this.authservise.register(new Register(
      this.registerForm.value.email,
      this.registerForm.value.username,
      this.registerForm.value.fullName,
      this.registerForm.value.password,
      ["ADMIN"]
    ))
    .subscribe(response => {
    
      this.hasSuucess = true;
      this.isLoading = false;
      this.message = response.message;
    }, error => {
      
      this.isLoading = false;

      this.hasError = true;
      this.message = error.error.message;
    }
    );

  }
}
