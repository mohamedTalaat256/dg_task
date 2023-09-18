import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Register } from 'src/app/model/register.model';
import { AuthService } from 'src/app/service/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Login } from 'src/app/model/login.model';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.css'],
  animations: [

    trigger('heightState', [
      state('login', style({
        overflow: 'hidden',
        height: '*',
      })),
      state('signup', style({
        overflow: 'hidden',
        height: '300px',
      })),
      transition('login => signup', animate('400ms ease-in-out')),
      transition('signup => login', animate('400ms ease-in-out'))
    ]),



    trigger('formState', [

      state('signup', style({
        opacity: 1,
        transform: 'translateX(0)'
      })),
      transition('void => *', [
        style({
          opacity: 0,
          transform: 'translateX(-400px)'
        }),
        animate(500)
      ]),

      transition('* => void', [
        animate(500, style({
          opacity: 1,
          transform: 'translateX(400px)'
        }))
      ]),


    ]),

  ]
})
export class AuthFormComponent {

  animationState: string = 'login';
  heightState: string = 'short';

  isLoading: boolean = false;
  roles: string[] = [];

  constructor(private toastr: ToastrService, private authservise: AuthService, private route: Router) { }


  loginMode: boolean = true;

  @ViewChild('authForm') authForm: NgForm;

  onSubmit() {

    if (this.loginMode) {
      this.login(
        this.authForm.value.username,
        this.authForm.value.password);

    } else {

      this.register(new Register(
        this.authForm.value.email,
        this.authForm.value.username,
        this.authForm.value.fullName,
        this.authForm.value.password,
        this.roles));
    }
  }

  login(username: string, password: string) {
    this.isLoading = true;

    this.authservise.login(new Login(username, password))
      .subscribe({
        next: (response) => {
          this.isLoading = false;
          this.toastr.success('Welcome ' + response.data.userData.fullName);

          localStorage.setItem('accessToken', response.data.accessToken);
          localStorage.setItem('refreshToken', response.data.refreshToken);
          localStorage.setItem('user', JSON.stringify(response.data.userData, null, 2));

          this.route.navigate(['/user/dashboard']);
        },
        error: (error) => {
          this.isLoading = false;

          this.toastr.error(error.error.message);
        }
      }
      );

  }

  register(registerRequest: Register) {
    this.isLoading = true;


    this.authservise.register(registerRequest)
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

  handleRoleChange(event: any) {

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


  toggleMode() {

    this.loginMode = !this.loginMode;


    if (this.loginMode) {
      this.animationState = 'signup';
      this.heightState = 'signup';

    } else {
      this.animationState = 'login';
      this.heightState = 'login';

    }
  }
}
