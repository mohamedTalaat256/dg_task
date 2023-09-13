import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Register } from 'src/app/model/register.model';
import { AuthService } from 'src/app/service/auth.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  isLoading: boolean = false;
  roles: string[] = [];



  constructor(private toastr: ToastrService ,private authservise: AuthService, private route: Router) { }

  @ViewChild('registerForm') registerForm: NgForm;

  onSubmit() {
    this.isLoading = true;
    

    this.authservise.register(new Register(
      this.registerForm.value.email,
      this.registerForm.value.username,
      this.registerForm.value.fullName,
      this.registerForm.value.password,
      this.roles
    ))
      .subscribe(
        {
          next: (response) => {

           
            this.isLoading = false;
            this.toastr.success(response.message);
          }, error: (error) => {

            this.isLoading = false;
            this.toastr.error(error.error.message);

            
          }
        }
      );

  }




  handleRoleChange(event) {
    // if not found add it
    // if found remove it

    const role: string = event.target.name;
    if (!this.roles.includes(role)) {
      this.roles.push(role);
    } else {
      const index = this.roles.indexOf(role);
      if (index !== -1) {
        this.roles.splice(index, 1);
      }
    }

  }
}
