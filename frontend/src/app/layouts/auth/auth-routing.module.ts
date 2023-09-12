import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthComponent } from './auth.component';
import { PageNotFoundComponent } from 'src/app/page-not-found/page-not-found.component';
const routes: Routes = [

  {
    path:'auth',
    component: AuthComponent,
    children:[
      {path:'login', component: LoginComponent},
      {path:'register', component: RegisterComponent},



      { path: '404',  component: PageNotFoundComponent },
  { path: '**',  redirectTo: '/404' },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule { }
